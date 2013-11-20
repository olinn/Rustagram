package is.ru.honn.rustagram.data;

import is.ru.honn.rustagram.domain.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CommentRowMapper  implements RowMapper
{
  @Override
  public Object mapRow(ResultSet resultSet, int i) throws SQLException
  {
    Comment comment = new Comment(resultSet.getInt(1), // id
        resultSet.getString(2),                        // creatorUsername
        new Date(resultSet.getDate(3).getTime()),      // created
        resultSet.getInt(4),                           // imageId
        resultSet.getString(5));                       // comment
    return comment;
  }
}
