package is.ru.honn.rustagram.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * An image.
 */
public class Image extends RustagramObject {
  private int id;
  private String url;
  private String description;

  public Image() {}

  public Image(int id, String creator, String url, String description) {
    super(creator);
    this.id = id;
    this.url = url;
    this.description = description;
  }

  public Image(String creator, String url, String description) {
    super(creator);
    this.url = url;
    this.description = description;
  }

  public Image(int id, String creator, Date created, String url, String description) {
    super(creator, created);
    this.id = id;
    this.url = url;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Image image = (Image) o;

    if (id != image.id) return false;
    if (description != null ? !description.equals(image.description) : image.description != null) return false;
    if (url != null ? !url.equals(image.url) : image.url != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (url != null ? url.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    return result;
  }
}
