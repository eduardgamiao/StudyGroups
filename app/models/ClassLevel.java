package models;

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
   * Returns the StudyGroups of this class in a List.
   * 
   * @return List containing all of the studyGroups.
   */
  public List<StudyGroup> getStudyGroupAsList() {
    return StudyGroup.getStudyGroupsForClass(this);
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
   * Returns a list of the different classes for the course.
   * 
   * @param id the id of the course
   * @return list of different classes
   */
  public static List<ClassLevel> getClassesForCourse(String id) {
    return find().where().eq("course", id).findList();
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

}
