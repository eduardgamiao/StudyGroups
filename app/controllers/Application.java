package controllers;

import java.util.List;
import models.Course;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.Index;
import views.html.course.ListOfCourses;

public class Application extends Controller {

  public static Result index() {
    return ok(Index.render("Welcome"));
  }

  public static Result listOfCourses() {
    List<Course> courses = Course.find().all();
    return ok(ListOfCourses.render("List of Courses", courses));
  }

}
