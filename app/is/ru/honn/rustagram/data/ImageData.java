package is.ru.honn.rustagram.data;

import is.ru.honn.rustagram.domain.Image;
import is.ru.honn.rustagram.domain.User;
import is.ru.honn.rustagram.service.ImageNotFoundException;
import is.ru.honn.rustagram.service.UserNotFoundException;
import is.ruframework.data.RuData;
import is.ruframework.data.RuDuplicateDataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageData extends RuData implements ImageDataGateway
{
  @Override
  public int addImage(Image image)
  {
    SimpleJdbcInsert insert =
        new SimpleJdbcInsert(getDataSource())
            .withTableName("ru_images")
            .usingGeneratedKeyColumns("id");

    Map<String, Object> parameters = new HashMap<String, Object>(7);
    parameters.put("creator_username", image.getCreatorUsername());
    parameters.put("created", image.getCreated());
    parameters.put("url", image.getUrl());
    parameters.put("description", image.getDescription());

    int returnKey;

    try
    {
      returnKey = insert.executeAndReturnKey(parameters).intValue();
    }
    catch(DataIntegrityViolationException divex)
    {
      // This means that the username associated with the image did not exist (foreign key violation).
      throw new UserNotFoundException("Unable to link image to user: " + image.getCreatorUsername());
    }

    image.setId(returnKey);
    return returnKey;
  }


  @Override
  public Image getImageById(int id) {
    try{
      JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
      Image image = (Image)jdbcTemplate.queryForObject(
          "select * from ru_images where id=?",
          new ImageRowMapper(), id);
      return image;
    }
    catch(EmptyResultDataAccessException erdae){
      throw new ImageNotFoundException("Could not find image with id: " + id);
    }
  }

  @Override
  public List<Image> getImagesByUsername(String username) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    List<Image> images = (List<Image>)jdbcTemplate.query(
        "select * from ru_images where creator_username=?", new ImageRowMapper(), username);
    return images;
  }
   //New function to fetch list of all images in the system
    @Override
    public List<Image> getAllImages() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        List<Image> images = (List<Image>)jdbcTemplate.query(
                "select * from ru_images order by id desc", new ImageRowMapper());
        return images;
    }
}
