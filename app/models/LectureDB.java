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
    if (!isRepeatVideo(data.uniqueId)) {
      Lecture lecture = new Lecture(data.course.toUpperCase(), data.level, data.topic, data.description, data.videoId);
      lecture.save();
    }
    else {
      Lecture lecture = getLecture(data.uniqueId);
      lecture.setCourse(data.course.toUpperCase());
      lecture.setLevel(data.level);
      lecture.setTopic(data.topic);
      lecture.setDescription(data.description);
      lecture.setVideoId(data.videoId);
      lecture.setUniqueId(data.uniqueId);
      lecture.save();
    }
  }
  
  /**
   * Delete a lecture from database with matching course, level, and videoId.
   * 
   * @param course course, e.g. ICS
   * @param level level, e.g. 311
   * @param videoId YouTube video ID
   */
  public static void deleteLecture(Lecture lecture) {
    Lecture.find().where().eq("uniqueId", lecture.getUniqueId()).findUnique().delete();
  }
  
  /**
   * Return a lecture video associated with given course, level, and videoId.
   * 
   * @param course course, e.g. ICS
   * @param level level, e.g. 311
   * @param videoId YouTube video ID
   * 
   * @return The retrieved lecture.
   */
  public static Lecture getLecture(String uniqueId) {
    return Lecture.find().where().eq("uniqueId", uniqueId).findUnique();
  }
  
  /**
   * Returns a list of lectures.
   * 
   * @return List of lectures.
   */
  public static List<Lecture> getLectures(String course) {
    return Lecture.find().where().eq("course", course).findList();
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
  public static boolean isRepeatVideo(String uniqueId) {
    return Lecture.find().where().eq("uniqueId", uniqueId).findUnique() != null;
  }
}
