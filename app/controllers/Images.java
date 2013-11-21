package controllers;

import is.ru.honn.rustagram.domain.Image;
import is.ru.honn.rustagram.domain.Comment;
import is.ru.honn.rustagram.domain.User;

import is.ru.honn.rustagram.domain.User;
import is.ru.honn.rustagram.domain.ImageRegistration;
import is.ru.honn.rustagram.service.RustagramService;

import is.ru.honn.rustagram.service.UserNotFoundException;
import is.ru.honn.rustagram.service.UsernameExistsException;
import play.cache.Cache;
import play.data.*;
import play.mvc.*;



import views.html.index;
import views.html.add_image;
/*  Images controller
*   Takes care of displaying adding image page, and processing the form submittion
*
*
*
*
 */


import java.util.List;

import static play.data.Form.form;

public class Images extends AbstractRustagramController {

    final static Form<ImageRegistration> addImageForm = form(ImageRegistration.class);

    public static Result showAddImageForm()
    {

        return ok(add_image.render(addImageForm));

    }

    public static Result addImage()
    {
        Form<ImageRegistration> filledForm = addImageForm.bindFromRequest();

        RustagramService service = (RustagramService) ctx.getBean("service");

        if(filledForm.field("url").value().length() < 8)
            filledForm.reject("url", "Url must be at least 8 letters");

        if(filledForm.field("description").value().length() < 5)
            filledForm.reject("description", "Description must be at least 5 letters");


        if(filledForm.hasErrors())
            return badRequest(add_image.render(filledForm));

        else
        {
            ImageRegistration created = filledForm.get();

            service.createImage(session().get("username"),created.getUrl(), created.getDescription());

            System.out.println(created.toString());
            List<Image> imageList = service.getAllImages();

            return ok(index.render(imageList));
        }
        }

    }


