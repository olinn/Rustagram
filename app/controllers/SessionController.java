package controllers;

import is.ru.honn.rustagram.data.UserDataGateway;
import is.ru.honn.rustagram.domain.User;
import is.ru.honn.rustagram.domain.UserAuthentication;
import play.data.Form;
import play.mvc.Result;
import views.html.login;
   /*
public class SessionController extends AbstractRustagramController {
    final static Form<UserAuthentication> loginForm = form(UserAuthentication.class);

    private static UserDataGateway userDataGateway = (UserDataGateway)ctx.getBean("userDataGateway");

    // A blank login form.
    public static Result loginForm()
    {
        return ok(login.render(loginForm));
    }

    // POST function for the login form
    public static Result login()
    {
        Form<UserAuthentication> filledForm = loginForm.bindFromRequest();

        // Error check the form.
        if (filledForm.field("username").value().length () < 4)
            filledForm.reject("username", "Username must be at least 4 characters");
        if (filledForm.field("password").value().length () < 4)
            filledForm.reject("password", "Password must be at least 4 characters");
        if (filledForm.hasErrors())
        {
            return badRequest(login.render(filledForm));
        }

        UserAuthentication user = filledForm.get();

        // Authenticate the user.
        User currentUser = userDataGateway.authenticate(user.getUsername(), user.getPassword());

        if(currentUser != null)
        {
            String userId = String.valueOf(currentUser.getId());
            String username = currentUser.getUsername();
            session("username", username);
            session("userId", userId);

            return redirect(controllers.routes.Application.index());
        }
        else // If no match is found in the database we kick the user back to the login screen
        {
            return redirect(controllers.routes.SessionController.loginForm());
        }
    }

    public static Result logout() {

        // When the user logs out we clear out the user's session.
        session().clear();
        flash("success", "You've been logged out");
        return redirect(controllers.routes.SessionController.loginForm());
    }
}    */
