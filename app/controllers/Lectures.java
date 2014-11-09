package controllers;

import java.util.List;
import models.Course;
import models.Lecture;
import models.LectureDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.LectureForm;
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
    Course courseName = Course.find().byId(id);
    List<Lecture> lectures = LectureDB.getLectures(id);
    LectureForm data = new LectureForm();
    Form<LectureForm> formData = Form.form(LectureForm.class).fill(data);
    
    return ok(ListOfLectures.render(courseName.getId(), courseName, lectures, formData, false));
  }
  
 
  /**
   * Add lecture to course.
   * 
   * @param id
   * 
   * @return lecture list page
   */
  public static Result addLecture(String id) {
    Course courseName = Course.find().byId(id);
    List<Lecture> lectures = LectureDB.getLectures(id);
    Form<LectureForm> formData = Form.form(LectureForm.class).bindFromRequest();
    
    if (formData.hasErrors()) {
      flash("error", "Login credentials not valid.");
      return badRequest(ListOfLectures.render(courseName.getId(), courseName, lectures, formData, true));
    }
    else {
      LectureForm data = formData.get();
      LectureDB.addLecture(data); 
      
      return redirect(routes.Lectures.viewLecture(courseName.getId()));
    }
  }

  /**
   * Delete lecture from repository
   * 
   * Issue: Only deletes from the top of the list. -AW
   * 
   * @param course
   * @param level
   * @param videoId
   * 
   * @return the lecture list page.
   */
  public static Result deleteLecture(String id, String uniqueId) {
    Course courseName = Course.find().byId(id);
    Lecture lecture = LectureDB.getLecture(uniqueId);
    LectureDB.deleteLecture(lecture); 
    
    return redirect(routes.Lectures.viewLecture(courseName.getId()));
  }
}
