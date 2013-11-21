package is.ru.honn.rustagram.domain;


import java.util.Date;

public class ImageRegistration extends Image{


    public ImageRegistration()
    {
    }

    public ImageRegistration(int id, String username, String url, String description)
    {
        super(id, username, url, description);
    }

    public ImageRegistration( String username, String url, String description)
    {
        super( username, url, description);
    }
}










