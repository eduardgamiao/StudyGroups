package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import play.db.ebean.Model;
import com.avaje.ebean.Page;

/**
 * . ClassLevel.java
 * 
 * 
 * @author Alvin Prieto
 *
 */
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

  /**
   * Constructor.
   * 
   * @param course the course
   * @param level the level
   */
  public ClassLevel(String course, int level) {
    this.setCourse(course.toUpperCase());
    this.setLevel(level);
    this.setCourseLevel(course.toUpperCase() + " " + level);
  }

  /**
   * Returns a Finder object for this object.
   * 
   * @return the finder
   */
  public static Finder<String, ClassLevel> find() {
    return new Finder<String, ClassLevel>(String.class, ClassLevel.class);
  }

  /**
   * Returns the course level.
   * 
   * @param courseLevel the course level
   * @return CourseLevel object
   */
  public static ClassLevel getCL(String courseLevel) {
    return find().where().eq("courseLevel", courseLevel).findUnique();
  }

  @Override
  public String toString() {
    return getCourseLevel();
  }

  /**
   * Adds a study group's id this class level's list of study groups.
   * 
   * @param id the id
   */
  public void addStudyGroup(long id) {
    if (!getStudyGroupId().contains(Long.toString(id))) {
      StringBuilder sb = new StringBuilder(getStudyGroupId());
      sb.append(id + "|");
      this.setStudyGroupId(sb.toString());
    }
  }

  /**
   * Returns the study groups string.
   * 
   * @return a string containing all the ids of study groups belonging to this classlevel
   */
  public String getStudyGroups() {
    return getStudyGroupId();
  }

  /**
   * Returns the StudyGroups of this class in a List.
   * 
   * @return List containing all of the studyGroups.
   */
  public List<StudyGroup> getStudyGroupAsList() {

    List<StudyGroup> studyGroups = new ArrayList<>();

    String[] ids = getStudyGroupId().split("\\|");

    for (int x = 0; x < ids.length; x++) {
      StudyGroup sg = StudyGroup.getSG(Long.parseLong(ids[x]));
      studyGroups.add(sg);
    }
    return studyGroups;
  }

  /**
   * Returns a page of the study Groups.
   * 
   * @param page current page.
   * @param listSize amount of study groups.
   * @return a page
   */
  public Page<StudyGroup> getStudyGroupPage(int page, int listSize) {
    return StudyGroup.getPage(getCourseLevel(), page, listSize);
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
   * @return the studyGroupId
   */
  public String getStudyGroupId() {
    return studyGroupId;
  }

  /**
   * @param studyGroupId the studyGroupId to set
   */
  public void setStudyGroupId(String studyGroupId) {
    this.studyGroupId = studyGroupId;
  }

}
