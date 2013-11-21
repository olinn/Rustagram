package controllers;

import is.ru.honn.rustagram.domain.Image;
import is.ru.honn.rustagram.domain.Comment;
import is.ru.honn.rustagram.domain.User;

import is.ru.honn.rustagram.domain.User;
import is.ru.honn.rustagram.domain.UserRegistration;
import is.ru.honn.rustagram.service.RustagramService;

import is.ru.honn.rustagram.service.UserNotFoundException;
import is.ru.honn.rustagram.service.UsernameExistsException;
import play.cache.Cache;
import play.data.*;
import play.mvc.*;



import views.html.index;
import views.html.add_image;

import static play.data.Form.form;

public class Images extends AbstractRustagramController {

    final static Form<Image> addImageForm = form(Image.class);

    public static Result showAddImageForm()
    {

        return ok(add_image.render(addImageForm));

    }

    public static Result addImage()
    {

        return ok(index.render());

    }


}
