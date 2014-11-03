package controllers;

import models.Course;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.course.ViewCourse;

public class Courses extends Controller {

  public static Result viewCourse(String id) {
    Course course = Course.getCourse(id);
    return ok(ViewCourse.render(course.getId(), course));
  }

}
