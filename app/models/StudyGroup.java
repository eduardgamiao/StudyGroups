package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.joda.time.DateTime;
import play.db.ebean.Model;

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

  public StudyGroup(long id, String course, int level, String location, int month, int day, int year, int hour,
      int min, String topics) {
    this.id = id;
    this.setCourse(course);
    this.level = level;
    this.location = location;
    this.meetTime = new DateTime(year, month, day, hour, min);
    this.topics = topics;
  }

  public String classToString() {
    return getCourse() + " " + level;
  }

  public static Finder<Long, StudyGroup> find() {
    return new Finder<Long, StudyGroup>(Long.class, StudyGroup.class);
  }

  public static StudyGroup getSG(long id) {
    return find().where().eq("id", id).findUnique();
  }

  public DateTime getMeetTime() {
    return meetTime;
  }

  public String getDay() {
    String day = meetTime.dayOfWeek().getAsText();
    return day;
  }

  public void setMeetTime(int month, int day, int year, int hour, int min) {
    this.meetTime = new DateTime(year, month, day, hour, min);
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getTopics() {
    return topics;
  }

  public void setTopics(String topics) {
    this.topics = topics;
  }

  public long getId() {
    return id;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

}
