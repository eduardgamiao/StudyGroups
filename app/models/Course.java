package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import play.db.ebean.Model;

@Entity
public class Course extends Model {

  /**
   * 
   */
  private static final long serialVersionUID = -5763824393646461390L;
  @Id
  private String id;
  private String courseName;

  public Course() {
  }

  public Course(String id, String courseName) {
    this.id = id;
    this.courseName = courseName;
  }

  public String getId() {
    return this.id;
  }

  public String getCourseName() {
    return this.courseName;
  }

  public static Finder<String, Course> find() {
    return new Finder<String, Course>(String.class, Course.class);
  }

  public static boolean isCourse(String course) {
    List<Course> byId = find().where().contains("id", course.toUpperCase()).findList();
    List<Course> byName = find().where().eq("courseName", course).findList();

    if ((byId.size() == 0) && (byName.size() == 0)) {
      return false;
    }
    return true;
  }

}
