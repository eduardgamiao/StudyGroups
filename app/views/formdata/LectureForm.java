package views.formdata;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import models.Lecture;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

public class LectureForm {
  private static final Integer VIDEO_ID_LENGTH = 11;

  @Id
  @GeneratedValue
  public long id;
  
  @Constraints.Required(message = "A course is required.")
  public String course;

  @Constraints.Required(message = "A level is required.")
  public String level;
  
  @Constraints.Required(message = "A topic(title) is requried.")
  public String topic;
  
  @Constraints.Required(message = "A YouTube's 11 character ID is required.")
  public String videoId;
  
  public String description;
  
  
  public LectureForm() {
    
  }
  
  public LectureForm(Lecture lecture) {
    this.course = lecture.getCourse();
    this.level = lecture.getLevel();
    this.topic = lecture.getTopic();
    this.videoId = lecture.getVideoId();
    this.description = lecture.getDescription();
  }
  
  public LectureForm(String course, String level, String topic, String videoId, String description) {
    this.course = course;
    this.level = level;
    this.topic = topic;
    this.videoId = videoId;
    this.description = description;
  }
  
  public long getId() {
    return id;
  }
  
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();
    
    if (topic == null || topic.length() == 0) {
      errors.add(new ValidationError("topic", "A topic(title) is required."));
    }
    
    if (videoId.length() != VIDEO_ID_LENGTH) {
      errors.add(new ValidationError("videoId", "The Video Id needs to be 11 characters long."));
    }
    
    return errors.isEmpty() ? null : errors;
  }
}
