package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.avaje.ebean.Page;
import play.db.ebean.Model;

/**
 * A StudyGroup model.
 * 
 * @author Alvin Prieto
 *
 */
@Entity
public class StudyGroup extends Model {

  /**
   * 
   */
  private static final long serialVersionUID = 2412755286124065186L;

  @Id
  private long id;

  private DateTime meetTime;

  private String course;
  private int level;
  private String location;
  private String topics;

  private String courseLevel;

  /**
   * Constructs a study group object.
   * 
   * @param id the id
   * @param course the course
   * @param level the level
   * @param location the location
   * @param month the month
   * @param day the day
   * @param year the year
   * @param hour the hour
   * @param min the minute
   * @param topics topics to be covered.
   */
  public StudyGroup(long id, String course, int level, String location, int month, int day, int year, int hour,
      int min, String topics) {
    this.setId(id);
    this.setCourse(course.toUpperCase());
    this.setLevel(level);
    this.setLocation(location);
    this.meetTime = new DateTime(year, month, day, hour, min);
    this.setTopics(topics);
    this.setCourseLevel(course.toUpperCase() + " " + level);
  }

  /**
   * Finder object for StudyGroup.
   * 
   * @return a finder
   */
  public static Finder<Long, StudyGroup> find() {
    return new Finder<Long, StudyGroup>(Long.class, StudyGroup.class);
  }

  /**
   * Used for pagination.
   * 
   * @param courseLevel the class level for which the study groups should be found.
   * @param page the current page
   * @param listSize how many entries to return per page
   * @return a page
   */
  public static Page<StudyGroup> getPage(String courseLevel, int page, int listSize) {
    return find().where().contains("courseLevel", courseLevel).orderBy("meetTime asc").findPagingList(listSize)
        .setFetchAhead(false).getPage(page);
  }

  /**
   * Returns a list of study groups belonging to the given class level.
   * 
   * @param cl the class level
   * @return study groups belonging to cl
   */
  public static List<StudyGroup> getStudyGroupsForClass(ClassLevel cl) {
    return find().where().contains("courseLevel", cl.toString()).findList();
  }

  /**
   * Returns the a StudyGroup.
   * 
   * @param id the id of the study group
   * @return StudyGroup
   */
  public static StudyGroup getSG(long id) {
    return find().where().eq("id", id).findUnique();
  }

  /**
   * Returns the day that the study group will be held.
   * 
   * @return a day
   */
  public String getDay() {
    String day = meetTime.dayOfWeek().getAsText();
    return day;
  }

  /**
   * Sets a new meet time for the study group.
   * 
   * @param month the month
   * @param day the day
   * @param year the year
   * @param hour the hour
   * @param min the minutes
   */
  public void setMeetTime(int month, int day, int year, int hour, int min) {
    this.meetTime = new DateTime(year, month, day, hour, min);
  }

  /**
   * Returns the date formatted. ex. 11-9-2014
   * 
   * @return the date in a formatted string.
   */
  public String getDateString() {
    String pattern = "MM-dd-yy";
    DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
    String dateString = fmt.print(meetTime);
    return dateString;
  }

  /**
   * Returns the time in a formatted string.
   * 
   * @return time in the format of hh: mm aa;
   */
  public String getTimeString() {
    String pattern = "hh : mm aa";
    DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
    String timeString = fmt.print(meetTime);
    return timeString;
  }

  /**
   * Returns the list of lectures related to the topics.
   * 
   * @return List of lectures.
   */
  public List<Lecture> getRelatedLectures() {
    Set<Lecture> lectures = new HashSet<>();
    String[] topics = this.topics.split(",");
    for (String topic : topics) {
      topic = "%" + topic + "%";
      List<Lecture> lecture = Lecture.find().where().ilike("topic", topic).findList();
      lectures.addAll(lecture);
    }
    return new ArrayList<Lecture>(lectures);
  }

  /**
   * @return the course
   */
  public String getCourse() {
    return course;
  }

  /**
   * @param course the course to set
   */
  public void setCourse(String course) {
    this.course = course;
  }

  /**
   * @return the level
   */
  public int getLevel() {
    return level;
  }

  /**
   * @param level the level to set
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * @param location the location to set
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * @return the topics
   */
  public String getTopics() {
    return topics;
  }

  /**
   * @param topics the topics to set
   */
  public void setTopics(String topics) {
    this.topics = topics;
  }

  /**
   * @return the courseLevel
   */
  public String getCourseLevel() {
    return courseLevel;
  }

  /**
   * @param courseLevel the courseLevel to set
   */
  public void setCourseLevel(String courseLevel) {
    this.courseLevel = courseLevel;
  }

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

}
