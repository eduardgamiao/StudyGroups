package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.LoginForm;
import views.html.Index;

public class Users extends Controller{

  public static Result postLogin() {
    Form<LoginForm> loginData = Form.form(LoginForm.class).bindFromRequest();
    if (loginData.hasErrors()) {
      return badRequest(Index.render("Login Error"));
    }
    else {
      session().clear();
      session("email", loginData.get().email);
      return redirect(routes.Application.index());
    }
  }
  
  public static Result logout() {
    session().clear();
    return redirect(routes.Application.index());
  }
}
