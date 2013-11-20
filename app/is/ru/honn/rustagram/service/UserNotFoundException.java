package is.ru.honn.rustagram.service;

/**
 * A user not found exception.
 */
public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
  }

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public UserNotFoundException(Throwable cause) {
    super(cause);
  }
}
