package is.ru.honn.rustagram.data;

import is.ru.honn.rustagram.domain.Gender;
import is.ru.honn.rustagram.domain.User;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements ParameterizedRowMapper<User>
{
  public User mapRow(ResultSet rs, int rowNum) throws SQLException
  {
    return  new User(rs.getInt(1),  // id
        rs.getString(2),        // username
        rs.getString(3),        // password
        rs.getString(4),        // displayName
        rs.getString(5),        // email
        Gender.fromString(rs.getString(6)), // gender
        rs.getDate(7)           // registered
        );
  }
}
