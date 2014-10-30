package controllers;

import java.util.List;
import models.Course;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.Index;
import views.html.ListOfCourses;
import views.html.ViewCourse;

public class Application extends Controller {
  public static String homePageString = "This page has been retrieved ";

  public static Result index() {
    return ok(Index.render("Welcome"));
  }

  public static Result listOfCourses() {
    List<Course> courses = Course.find().all();
    return ok(ListOfCourses.render("List of Courses", courses));
  }

  public static Result viewCourse(String id) {
    Course course = Course.find().byId(id);
    return ok(ViewCourse.render(course.getId(), course));
  }

}
