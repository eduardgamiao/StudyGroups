package controllers;

import models.Course;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.lecture.ListOfLectures;

/**
 * Implements the controller for Lectures.
 * 
 * @author Alvin
 *
 */
public class Lectures extends Controller{
  
  /**
   * Returns the lecture list page.
   * 
   * @param id course id e.g. ICS
   * 
   * @return the lecture list page.
   */
  public static Result viewLecture(String id) {
    Course course = Course.find().byId(id);
    return ok(ListOfLectures.render(course.getId(), course));
  }
}
