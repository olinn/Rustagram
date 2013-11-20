package is.ru.honn.rustagram.domain;

import java.util.Date;

/**
 * A comment.
 */
public class Comment extends RustagramObject {
  private int id;
  private int imageId;
  private String comment;

  public Comment() {}

  public Comment(String creator, int imageId, String comment) {
    super(creator);
    this.imageId = imageId;
    this.comment = comment;
  }

  public Comment(int id, String creator, Date created, int imageId, String comment) {
    super(creator, created);
    this.id = id;
    this.imageId = imageId;
    this.comment = comment;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getImageId() {
    return imageId;
  }

  public void setImageId(int imageId) {
    this.imageId = imageId;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Comment comment1 = (Comment) o;

    if (id != comment1.id) return false;
    if (imageId != comment1.imageId) return false;
    if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + imageId;
    result = 31 * result + (comment != null ? comment.hashCode() : 0);
    return result;
  }
}
