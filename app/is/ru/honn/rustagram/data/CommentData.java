package is.ru.honn.rustagram.data;

import is.ru.honn.rustagram.domain.Comment;
import is.ru.honn.rustagram.service.ImageNotFoundException;
import is.ru.honn.rustagram.service.UserNotFoundException;
import is.ruframework.data.RuData;
import is.ruframework.data.RuDuplicateDataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentData extends RuData implements CommentDataGateway{

  @Override
  public int addComment(Comment comment) {
    SimpleJdbcInsert insert =
        new SimpleJdbcInsert(getDataSource())
            .withTableName("ru_comments")
            .usingGeneratedKeyColumns("id");

    Map<String, Object> parameters = new HashMap<String, Object>(7);
    parameters.put("creator_username", comment.getCreatorUsername());
    parameters.put("created", comment.getCreated());
    parameters.put("image_id", comment.getImageId());
    parameters.put("comment", comment.getComment());

    int returnKey;

    try
    {
      returnKey = insert.executeAndReturnKey(parameters).intValue();
    }
    catch(DataIntegrityViolationException divex)
    {
      // This means that we're either referencing a non existent image_id or username.

      // ... and now for a butt-ugly hack to figure out which one it is...
      if( divex.getMessage().contains("table \"hshstefan.ru_users\", column 'username'") ){
        throw new UserNotFoundException("No user found with username: " + comment.getCreatorUsername(), divex);
      }
      else if( divex.getMessage().contains("table \"hshstefan.ru_images\", column 'id'") ){
        throw new ImageNotFoundException("No image found with id: " + comment.getImageId(), divex);
      }

      throw new RuDuplicateDataException("A duplicate image ID was found. This is weird and should never happen.", divex);
    }

    comment.setId(returnKey);
    return returnKey;
  }

  @Override
  public List<Comment> getCommentsOnImage(int imageId) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    List<Comment> comments = (List<Comment>)jdbcTemplate.query(
        "select * from ru_comments where image_id=?", new CommentRowMapper(), imageId);
    return comments;
  }

  /*
  @Override
  public List<Comment> getCommentsForUsername(String username) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    List<Comment> comments = (List<Comment>)jdbcTemplate.query(
        "select * from ru_comments where creator_username=?", new CommentRowMapper(), username);
    return comments;
  }
  */
}
