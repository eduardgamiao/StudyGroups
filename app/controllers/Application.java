package controllers;

import java.util.List;
import models.Course;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.Index;
import views.html.course.ListOfCourses;

/**
 * The main controller for this application.
 * 
 * @author Alvin Prieto
 *
 */
public class Application extends Controller {

  /**
   * Returns the index page.
   * 
   * @return the index page.
   */
  public static Result index() {
    return ok(Index.render("Welcome"));
  }

  /**
   * Returns the page containing the list of courses.
   * 
   * @return page containing list of courses
   */
  public static Result listOfCourses() {
    List<Course> courses = Course.find().all();
    return ok(ListOfCourses.render("List of Courses", courses));
  }

}
