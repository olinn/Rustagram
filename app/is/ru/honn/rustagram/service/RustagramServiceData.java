package is.ru.honn.rustagram.service;

import is.ru.honn.rustagram.data.CommentDataGateway;
import is.ru.honn.rustagram.data.ImageDataGateway;
import is.ru.honn.rustagram.data.UserDataGateway;
import is.ru.honn.rustagram.domain.Comment;
import is.ru.honn.rustagram.domain.Gender;
import is.ru.honn.rustagram.domain.Image;
import is.ru.honn.rustagram.domain.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RustagramServiceData implements RustagramService {

  private Logger log = Logger.getLogger(RustagramServiceData.class.getName());

  private UserDataGateway userDataGateway;
  private ImageDataGateway imageDataGateway;
  private CommentDataGateway commentDataGateway;

  public RustagramServiceData(UserDataGateway userDataGateway, ImageDataGateway imageDataGateway, CommentDataGateway commentDataGateway) {
    this.userDataGateway = userDataGateway;
    this.imageDataGateway = imageDataGateway;
    this.commentDataGateway = commentDataGateway;
  }

  /**
   * Construct a service stub with a single initial user defined. This user will be added
   * at the beginning and any error experienced will be ignored.
   */
  public RustagramServiceData(UserDataGateway userDataGateway, ImageDataGateway imageDataGateway, CommentDataGateway commentDataGateway, User user) {
    this(userDataGateway, imageDataGateway, commentDataGateway);

    try{
      userSignup(user.getUsername(), user.getPassword(), user.getDisplayName(), user.getEmail(), user.getGender());
    }
    catch(Throwable t){
      // The user may have ben registered before so we ignore this error, but it's good practice to print out some message.
      log.log(Level.INFO, "Unable to register initial user. This may be normal if the user has been registered before.");
    }
  }

  @Override
  public User userSignup(String username, String password, String displayName, String email, Gender gender) throws UsernameExistsException {
    User user = new User(username, password, displayName, email, gender);
    int id = userDataGateway.addUser(user);
    user.setId(id);
    return user;
  }


  //Tries to find user by username, if the user is found and password matches, returns the user, else null.
  @Override
  public User userLogin(String username, String password)
  {
      User user = userDataGateway.getUserByUsername(username);
      if(user == null)
          return null;

      if(!user.getPassword().equals(password))
          return null;

      return user;
  }

  @Override
  public User getUser(String username) throws UserNotFoundException {
    User user = userDataGateway.getUserByUsername(username);
    if( user == null ){
      throw new UserNotFoundException("User not found: " + username);
    }
    return user;
  }

  @Override
  public Image createImage(String username, String url, String description) throws UserNotFoundException {
    Image image = new Image(username, url, description);
    int id = imageDataGateway.addImage(image); // This can throw a UserNotFoundException
    image.setId(id);
    return image;
  }

  @Override
  public List<Image> getImages(String username) throws UserNotFoundException {
    // Make sure that this user exists (this will throw a UserNotFoundException if he doesn't).
    User user = userDataGateway.getUserByUsername(username);
    return imageDataGateway.getImagesByUsername(user.getUsername());
  }

  @Override
  public Image getImage(int id) throws ImageNotFoundException {
    return imageDataGateway.getImageById(id);
  }
    @Override
    public List<Image> getAllImages() throws ImageNotFoundException {
        return imageDataGateway.getAllImages();
    }


  @Override
  public Comment addCommentOnImage(String username, int imageId, String comment) throws UserNotFoundException, ImageNotFoundException {
    Comment cmt = new Comment(username, imageId, comment);
    int id = commentDataGateway.addComment(cmt);
    cmt.setId(id);
    return cmt;
  }

  @Override
  public List<Comment> getCommentsOnImage(int imageId) throws ImageNotFoundException {
    return commentDataGateway.getCommentsOnImage(imageId);
  }
}
