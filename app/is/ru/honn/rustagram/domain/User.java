package is.ru.honn.rustagram.domain;

import java.util.Date;

/**
 * A user.
 */
public class User {
  private int id;
  private String username;
  private String password;
  private String displayName;
  private String email;
  private Gender gender;
  private Date registered;

  public User(){}

  public User(int id, String username, String password, String displayName, String email, Gender gender, Date registered) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.displayName = displayName;
    this.email = email;
    this.gender = gender;
    this.registered = registered;
  }

  public User(String username, String password, String displayName, String email, Gender gender) {
    this.username = username;
    this.password = password;
    this.displayName = displayName;
    this.email = email;
    this.gender = gender;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Date getRegistered() {
    return registered;
  }

  public void setRegistered(Date registered) {
    this.registered = registered;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (id != user.id) return false;
    if (displayName != null ? !displayName.equals(user.displayName) : user.displayName != null) return false;
    if (email != null ? !email.equals(user.email) : user.email != null) return false;
    if (gender != user.gender) return false;
    if (password != null ? !password.equals(user.password) : user.password != null) return false;
    if (registered != null ? !registered.equals(user.registered) : user.registered != null) return false;
    if (!username.equals(user.username)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + username.hashCode();
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (gender != null ? gender.hashCode() : 0);
    result = 31 * result + (registered != null ? registered.hashCode() : 0);
    return result;
  }
}

