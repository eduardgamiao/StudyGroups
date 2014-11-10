package controllers;

import models.Course;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.course.ViewCourse;

/**
 * The controller for Courses.
 * 
 * @author Alvin Prieto
 *
 */
public class Courses extends Controller {

  /**
   * Returns the page for the specified course.
   * 
   * @param id the id of the course.
   * @return the page belonging to the course.
   */
  public static Result viewCourse(String id) {
    Course course = Course.getCourse(id);
    return ok(ViewCourse.render(course.getId(), course));
  }

}
