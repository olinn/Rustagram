package controllers;

import is.ru.honn.rustagram.service.RustagramService;
import is.ru.honn.rustagram.domain.Image;
import play.*;
import play.mvc.*;



import views.html.index;


import java.util.List;
//import scala.collection.immutable.List;

public class Application extends AbstractRustagramController {

    public static Result index() {

        RustagramService service = (RustagramService) ctx.getBean("service");

        List<Image> imageList = service.getAllImages();

        return ok(index.render(imageList));


    }

}
