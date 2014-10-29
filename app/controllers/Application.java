package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.Index;

public class Application extends Controller {
  public static String homePageString = "This page has been retrieved ";

  public static Result Index() {
    return ok(Index.render("Hello World"));
  }

}
