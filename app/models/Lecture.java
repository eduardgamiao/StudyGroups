package models;

import javax.persistence.Id;
import play.db.ebean.Model;

public class Lecture extends Model{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  @Id
  private long id;
  
  private String course;
  private String level;
  private String topic;
  private String description;
  private String videoId;
  
  public Lecture(){
  }
  
  public Lecture(String topic, String description, String videoId) {
    this.setTopic(topic);
    this.setDescription(description);
    this.setVideoId(videoId);
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the topic
   */
  public String getTopic() {
    return topic;
  }

  /**
   * @param topic the topic to set
   */
  public void setTopic(String topic) {
    this.topic = topic;
  }

  /**
   * @return the videoId
   */
  public String getVideoId() {
    return videoId;
  }

  /**
   * @param videoId the videoId to set
   */
  public void setVideoId(String videoId) {
    this.videoId = videoId;
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
  public String getLevel() {
    return level;
  }

  /**
   * @param level the level to set
   */
  public void setLevel(String level) {
    this.level = level;
  }

}
