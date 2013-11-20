package is.ru.honn.rustagram.service;

import is.ru.honn.rustagram.domain.Comment;
import is.ru.honn.rustagram.domain.Gender;
import is.ru.honn.rustagram.domain.Image;
import is.ru.honn.rustagram.domain.User;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A service stub implemending the RustagramService interface.
 *
 * This service stub is only meant for testing as it does not persist any user or image information.
 */
public class RustagramServiceStub implements RustagramService {
  private Logger log = Logger.getLogger(RustagramServiceStub.class.getName());

  private Map<String, User> users = new HashMap<String, User>();
  private Map<Integer, Image> images = new HashMap<Integer, Image>();
  private Map<Integer, List<Comment>> comments = new HashMap<Integer, List<Comment>>();

  /**
   * Construct a service stub with no initial user defined.
   */
  public RustagramServiceStub() {
  }

  /**
   * Construct a service stub with a single initial user defined. This user will be added
   * at the beginning and any error experienced will be ignored.
   */
  public RustagramServiceStub(User user) {
    try{
      userSignup(user.getUsername(), user.getPassword(), user.getDisplayName(), user.getEmail(), user.getGender());
    }
    catch(Throwable t){
      // Since we always start with a clean slate in the service stub implementation, we should not get this.
      // So let's log a message out.
      log.log(Level.SEVERE, "Unable to register initial user. This should not happen in the Service Stub implementation.", t);

      // Note that this may well happen if we had a persistant storage such as a database or a file that we
      // registered our users in. So in those cases, we may want to at least make this log message less than
      // "severe".
    }
  }

  @Override
  public User userSignup(String username, String password, String displayName, String email, Gender gender) throws UsernameExistsException {
    if (users.containsKey(username)) {
      throw new UsernameExistsException("A user with this username already exists.");
    }
    User user = new User(username, password, displayName, email, gender);
    user.setId(users.size());
    users.put(username, user);
    return user;
  }

  @Override
  public User getUser(String username) throws UserNotFoundException {
    if (!users.containsKey(username)) {
      throw new UserNotFoundException("No user with that username was found.");
    }
    return users.get(username);
  }

  @Override
  public Image createImage(String username, String url, String description) throws UserNotFoundException {
    // First check if the user exists
    getUser(username);

    // We use the size of the images map as a unique id for new images.
    // There are obvious downsides to this, for example if we were to allow
    // the removal of images later on, but since this is only a service stub
    // we ignore that.
    Image image = new Image(username, url, description);
    image.setId(images.size());
    images.put(image.getId(), image);
    return image;
  }

  @Override
  public List<Image> getImages(String username) throws UserNotFoundException {
    // Make sure the user exists.
    User u = getUser(username);

    // Create a list to hold the results
    List<Image> results = new ArrayList<Image>();

    // Now loop through everything and collect images that match this user.
    // This is a bad implementation, but does the job for a Service Stub.
    for (Image image : images.values()){
      if (username.equals(image.getCreatorUsername())) {
        results.add(image);
      }
    }
    return results;
  }

  @Override
  public Image getImage(int id) throws ImageNotFoundException {
    if (!images.containsKey(id)) {
      throw new ImageNotFoundException("An image with that ID does not exist.");
    }
    return images.get(id);
  }

  @Override
  public Comment addCommentOnImage(String username, int imageId, String comment) throws UserNotFoundException, ImageNotFoundException {
    User user = getUser(username);
    Image image = getImage(imageId);
    Comment c = new Comment(user.getUsername(), image.getId(), comment);

    if(comments.containsKey(imageId)){
      comments.get(imageId).add(c);
    }
    else{
      List<Comment> commentList = new ArrayList<Comment>();
      commentList.add(c);
      comments.put(imageId, commentList);
    }
    return c;
  }

  @Override
  public List<Comment> getCommentsOnImage(int imageId) throws ImageNotFoundException {
    Image image = getImage(imageId); // To verify that the image exists. Will throw exception otherwise.
    return comments.get(image.getId());
  }
}
