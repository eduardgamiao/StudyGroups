package models;

import play.db.ebean.Model;

public class Lecture extends Model{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private String description;
  private String youtubeURL;
  
  public Lecture(){
  }
  
  public Lecture(String description, String youtubeURL) {
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

}
