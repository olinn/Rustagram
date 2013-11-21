package is.ru.honn.rustagram.service;

import is.ru.honn.rustagram.domain.Comment;
import is.ru.honn.rustagram.domain.Gender;
import is.ru.honn.rustagram.domain.Image;
import is.ru.honn.rustagram.domain.User;

import java.util.List;

/**
 * The service layer interface for Rustagram.
 */
public interface RustagramService {

  /**
   * Signup a new user.
   *
   * @param username    the requested username.
   * @param password    the requested password.
   * @param displayName the display name.
   * @param email       the provided email address.
   * @param gender      the gender
   *
   * @return  the User instance that was created.
   *
   * @throws UsernameExistsException  if the requested username already exists.
   */
  public User userSignup(String username, String password, String displayName,
                         String email, Gender gender)
      throws UsernameExistsException;

  /**
   * Retrieve a user with the specified username.
   *
   * @param username the username of the user to be retrieved.
   *
   * @return  the retrieved user.
   *
   * @throws UserNotFoundException  if no user with the specified username was found.
   */


  public User userLogin(String username, String password)
      throws UserNotFoundException;

  public User getUser(String username) throws UserNotFoundException;

  /**
   * Add a new image for the user with a given description.
   *
   * @param username    the user adding the image.
   * @param url         the url of the image to add.
   * @param description a text description for the image.
   *
   * @return  the Image instance that was created.
   *
   * @throws UserNotFoundException  if no user with the specified username was found.
   */
  public Image createImage(String username, String url,
                           String description) throws UserNotFoundException;

  /**
   * Retrieve a list of all images for a specified user.
   *
   * @param username  the user for which to retrieve the list of images.
   *
   * @return  a list of images for the specified user.
   *
   * @throws UserNotFoundException  if no user with the specified username was found.
   */
  public List<Image> getImages(String username) throws UserNotFoundException;

  /**
   * Retrieve an image with a given ID.
   *
   * @param id  the ID of the image to retrieve.
   *
   * @return  the Image instance for the image with the specified ID.
   *
   * @throws ImageNotFoundException if no image with the specified ID was found.
   */


  //Get all images

  public List<Image> getAllImages() throws ImageNotFoundException;


  public Image getImage(int id) throws ImageNotFoundException;

  /**
   * Adds a comment on an image.
   *
   * @param username  the user adding the comment.
   * @param imageId   the ID of the image the comment is being added to.
   * @param comment   the comment text.
   *
   * @return  the Comment instance that was created.
   *
   * @throws UserNotFoundException  if no user with the specified username was found.
   * @throws ImageNotFoundException if no image with the specified ID was found.
   */
  public Comment addCommentOnImage(String username, int imageId, String comment) throws UserNotFoundException, ImageNotFoundException;

  /**
   * Retrieve a list of all comments on a specified image.
   *
   * @param imageId  the ID of the image for which to retrieve the comments.
   *
   * @return  a list of comments for the specified image.
   *
   * @throws ImageNotFoundException if no image with the specified ID was found.
   */
  public List<Comment> getCommentsOnImage(int imageId) throws ImageNotFoundException;
}


