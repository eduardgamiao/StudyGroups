package controllers;

import models.Course;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.lecture.ViewLecture;

public class Lectures extends Controller{
  
  public static Result viewLecture(String id) {
    Course course = Course.find().byId(id);
    return ok(ViewLecture.render(course.getId(), course));
  }
}
