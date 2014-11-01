package views.formdata;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Id;
import play.data.validation.Constraints;
import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.Min;
import play.data.validation.ValidationError;

public class StudyGroupForm {

  @Id
  public long id;
  
  @Constraints.Required(message = "A course level is required.")
  public String courseLevel;

  @Constraints.Required(message = "A location is required.")
  public String location;

  @Constraints.Required(message = "A month is required.")
  @Min(1)
  @Max(12)
  public int month;

  @Constraints.Required(message = "A Day is required.")
  public int day;

  @Constraints.Required(message = "An hour is required.")
  @Min(0)
  @Max(24)
  public int hour;

  @Constraints.Required(message = "Minutes are required.")
  @Min(0)
  @Max(59)
  public int min;

  public String topics;

  public List<ValidationError> validate() {

    int year = Calendar.getInstance().get(Calendar.YEAR);

    List<ValidationError> errors = new ArrayList<>();

    if ((month == 4 || month == 6 || month == 9 || month == 11) && (day >= 31)) {
      errors.add(new ValidationError(Integer.toString(day), "Cannot have more than 30 days in this month"));
    }

    if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        && (day >= 32)) {
      errors.add(new ValidationError(Integer.toString(day), "Cannot have more than 31 days in this month"));
    }

    if (isLeapYear(year) && month == 2 && day >= 30) {
      errors.add(new ValidationError(Integer.toString(day), "Cannot have more than 29 days in Feb"));
    }

    if (isLeapYear(year) && month == 2 && day >= 29) {
      errors.add(new ValidationError(Integer.toString(day), "Cannot have more than 28 days in Feb"));
    }

    return errors.isEmpty() ? errors : null;

  }

  private static boolean isLeapYear(int year) {

    if (year % 4 == 0) {
      if (year % 100 == 0) {
        if (year % 400 == 0) {
          return true;
        }
        return false;
      }
      return true;
    }
    return true;
  }
}
