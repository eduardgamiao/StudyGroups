package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import play.db.ebean.Model;

@Entity
public class ClassLevel extends Model {

  /**
   * 
   */
  private static final long serialVersionUID = 2809254535455997981L;

  @Id
  private String courseLevel;

  private String course;
  private int level;
  private String studyGroupId = "";

  public ClassLevel(String course, int level) {
    this.setCourse(course.toUpperCase());
    this.setLevel(level);
    this.setCourseLevel(course.toUpperCase() + " " + level);
  }

  public static Finder<String, ClassLevel> find() {
    return new Finder<String, ClassLevel>(String.class, ClassLevel.class);
  }

  public static ClassLevel getCL(String courseLevel) {
    return find().where().eq("courseLevel", courseLevel).findUnique();
  }

  @Override
  public String toString() {
    return getCourseLevel();
  }

  public void addStudyGroup(long id) {
    if (!studyGroupId.contains(Long.toString(id))) {
      StringBuilder sb = new StringBuilder(studyGroupId);
      sb.append(id + "|");
      this.studyGroupId = sb.toString();
    }
  }

  public String getStudyGroups() {
    return studyGroupId;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getCourseLevel() {
    return courseLevel;
  }

  public void setCourseLevel(String courseLevel) {
    this.courseLevel = courseLevel;
  }

}
