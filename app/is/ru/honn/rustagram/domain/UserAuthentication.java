package is.ru.honn.rustagram.domain;


public class UserAuthentication{

    protected String username;
    protected String password;

    public UserAuthentication()
    {}

    public UserAuthentication(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
