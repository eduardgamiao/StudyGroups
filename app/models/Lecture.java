package models;

import play.db.ebean.Model;

public class Lecture extends Model{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private String topic;
  private String description;
  private String youtubeURL;
  
  public Lecture(){
  }
  
  public Lecture(String topic, String description, String youtubeURL) {
    this.setTopic(topic);
    this.setDescription(description);
    this.setYoutubeURL(youtubeURL);
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
   * @return the youtubeURL
   */
  public String getYoutubeURL() {
    return youtubeURL;
  }

  /**
   * @param youtubeURL the youtubeURL to set
   */
  public void setYoutubeURL(String youtubeURL) {
    this.youtubeURL = youtubeURL;
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

}
