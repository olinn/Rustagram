package controllers;

import is.ru.honn.rustagram.domain.User;
import is.ru.honn.rustagram.domain.UserRegistration;
import is.ru.honn.rustagram.service.RustagramService;

import is.ru.honn.rustagram.service.UserNotFoundException;
import is.ru.honn.rustagram.service.UsernameExistsException;
import play.cache.Cache;
import play.data.*;
import play.mvc.*;

import static play.data.Form.form;

import views.html.index;
import views.html.login;
import views.html.signup;
import views.html.signup_success;

public class Users extends AbstractRustagramController {

    final static Form<User> loginForm = form(User.class);
    final static Form<UserRegistration> signupForm = form(UserRegistration.class);


    public static Result showSignupForm(){
        return ok(signup.render());
    }

    public static Result processSignupForm(){
	return ok(index.render());
    }

    public static Result showLoginForm(){
        return ok(login.render(loginForm));
    }

    public static Result processLoginForm(){
        Form<User> filledForm = loginForm.bindFromRequest();

        // We get the context from AbstractRustagramController
        RustagramService service = (RustagramService) ctx.getBean("service");

        try{
            User user = service.getUser(filledForm.field("username").value());
            if(!user.getPassword().equals(filledForm.field("password").value())){
                // Let's throw this exception here to use the same logic for
                // unsuccessful login (both username not found and incorrect
                // password.
                throw new UserNotFoundException();
            }

            // User was found and correct password entered.
            session("username", user.getUsername());
            session("displayName", user.getDisplayName());

        }
        catch(UserNotFoundException unfe){
            filledForm.reject("password", "User not found or incorrect password entered.");
            return badRequest(login.render(filledForm));
        }

        return ok(index.render());
    }

    public static Result logout(){
        session().clear();
        return ok(index.render());
    }
}
