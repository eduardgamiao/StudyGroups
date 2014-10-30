package models;

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
  public String id;
  public String courseName;

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

}
