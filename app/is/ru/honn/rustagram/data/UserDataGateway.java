package is.ru.honn.rustagram.data;

import is.ru.honn.rustagram.domain.User;
import is.ruframework.data.RuDataAccess;

public interface UserDataGateway extends RuDataAccess
{
    public int addUser(User user);
    public User getUserByUsername(String username);
}