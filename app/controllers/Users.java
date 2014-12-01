package controllers;

import models.UserInfoDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.LoginForm;
import views.formdata.UserForm;
import views.html.Index;

public class Users extends Controller {

  /**
   * Creates a user account.
   * 
   * @return a page
   */
  public static Result createAccount() {
    Form<UserForm> userForm = Form.form(UserForm.class).bindFromRequest();

    if (userForm.hasErrors()) {
      return badRequest(Index.render("Welcome", LoginForm.getForm(), false, userForm, true));
    }

    UserForm uf = userForm.get();
    UserInfoDB.addUserInfo(uf.getFirstName(), uf.getLastName(), uf.getEmail(), uf.getPassword());

    // Login user after creating account
    session().clear();
    session("email", uf.getEmail());
    return redirect(routes.Application.index());
  }

  public static Result postLogin(String url) {
    Form<LoginForm> loginData = Form.form(LoginForm.class).bindFromRequest();
    if (loginData.hasErrors()) {
      return badRequest(Index.render("Login Error", loginData, true, UserForm.getForm(), false));
    }
    else {
      session().clear();
      session("email", loginData.get().email);
      if (url.equals("/login")) {
        return redirect(routes.Application.index());
      }
      else {
        return redirect(url);
      }
    }
  }

  public static Result logout() {
    session().clear();
    return redirect(routes.Application.index());
  }
}
