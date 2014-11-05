package models;

import java.util.List;
import views.formdata.LectureForm;

/**
 * Database for lectures.
 * 
 * These methods have not been tested yet.
 * 
 * @author Alvin
 *
 */
public class LectureDB {

  /**
   * Add lecture to lecture database.
   * 
   * @param data Lecture data
   */
  public static void addLecture(LectureForm data) {
    Lecture lecture = new Lecture(data.course, data.level, data.topic, data.description, data.videoId);
    lecture.save();
  }
  
  /**
   * Delete a lecture from database with matching course, level, and videoId.
   * 
   * @param course course, e.g. ICS
   * @param level level, e.g. 311
   * @param videoId YouTube video ID
   */
  public static void deleteLecture(String course, String level, String videoId) {
    Lecture.find().where().eq("course", course).eq("level", level).eq("videoId", videoId).findUnique().delete();
  }
  
  /**
   * Returns a list of lectures.
   * 
   * @return List of lectures.
   */
  public static List<Lecture> getLectures() {
    return Lecture.find().findList();
  }
  
  /**
   * Checks if video already exists in given course and level.
   * 
   * @param course course, e.g. ICS
   * @param level level, e.g. 311
   * @param videoId YouTube video ID
   * 
   * @return true if it already exist, false otherwise.
   */
  public static boolean isRepeatVideo(String course, String level, String videoId) {
    return Lecture.find().where().eq("course", course).eq("level", level).eq("videoId", videoId).findUnique() != null;
  }
}
