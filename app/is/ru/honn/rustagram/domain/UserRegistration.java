package is.ru.honn.rustagram.domain;

import java.util.Date;

public class UserRegistration extends User {

    protected String repeatPassword;

    public UserRegistration()
    {
    }

    public UserRegistration(int id, String username, String password, String displayName, String email, Gender gender, Date registered)
    {
        super(id, username, password, displayName, email, gender, registered);
        this.repeatPassword = repeatPassword;
    }

    public UserRegistration(String username, String password, String displayName, String email, Gender gender)
    {
        super(username, password, displayName, email, gender);
        this.repeatPassword = repeatPassword;
    }
    public String getRepeatPassword()
    {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword)
    {
        this.repeatPassword = repeatPassword;
    }
}

