package is.ru.honn.rustagram.data;

import is.ru.honn.rustagram.domain.Image;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageRowMapper implements RowMapper
{
  @Override
  public Object mapRow(ResultSet resultSet, int i) throws SQLException
  {
    Image image = new Image(resultSet.getInt(1), // id
        resultSet.getString(2),     // creatorUsername
        resultSet.getDate(3),       // created
        resultSet.getString(4),     // url
        resultSet.getString(5));    // description
    return image;
  }
}
