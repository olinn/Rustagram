package is.ru.honn.rustagram.data;

import is.ru.honn.rustagram.domain.Image;
import is.ruframework.data.RuDataAccess;

import java.util.List;

public interface ImageDataGateway extends RuDataAccess {
  public int addImage(Image image);
  public Image getImageById(int id);
  public List<Image> getImagesByUsername(String username);
  public List<Image> getAllImages();
}
