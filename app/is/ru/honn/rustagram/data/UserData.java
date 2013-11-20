package is.ru.honn.rustagram.data;

import is.ru.honn.rustagram.domain.User;
import is.ru.honn.rustagram.service.UserNotFoundException;
import is.ru.honn.rustagram.service.UsernameExistsException;
import is.ruframework.data.RuData;
import is.ruframework.data.RuDuplicateDataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserData extends RuData implements UserDataGateway
{
  public int addUser(User user)
  {
    SimpleJdbcInsert insert =
        new SimpleJdbcInsert(getDataSource())
            .withTableName("ru_users")
            .usingGeneratedKeyColumns("id");

    Map<String, Object> parameters = new HashMap<String, Object>(7);
    parameters.put("username", user.getUsername());
    parameters.put("password", user.getPassword());
    parameters.put("displayName", user.getDisplayName());
    parameters.put("email", user.getEmail());
    parameters.put("gender", user.getGender());
    parameters.put("registered", user.getRegistered());

    int returnKey;

    try
    {
      returnKey = insert.executeAndReturnKey(parameters).intValue();
    }
    catch(DataIntegrityViolationException divex)
    {
      throw new UsernameExistsException("User " + user.getUsername() + " already exits", divex);
    }

    user.setId(returnKey);
    return returnKey;
  }

  public User getUserByUsername(String username)
  {
    Collection<String> users;
    JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

    User user;
    try
    {
      user = (User)jdbcTemplate.queryForObject(
          "select * from ru_users where username = '" + username + "'", new UserRowMapper());
    }
    catch (EmptyResultDataAccessException erdaex)
    {
      throw new UserNotFoundException("No user found with username: " + username);
    }
    return user;
  }
}
