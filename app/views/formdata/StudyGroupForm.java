package views.formdata;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import models.Course;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

public class StudyGroupForm {

  @Id
  @GeneratedValue
  public long id;

  @Constraints.Required(message = "A course level is required.")
  public String courseLevel;

  @Constraints.Required(message = "A location is required.")
  public String location;

  @Constraints.Required(message = "A month is required.")
  public String month;

  @Constraints.Required(message = "A day is required.")
  public String day;

  public int year = Calendar.getInstance().get(Calendar.YEAR);

  @Constraints.Required(message = "An hour is required.")
  public String hour;

  @Constraints.Required(message = "Minutes are required.")
  public String min;

  public String topics;

  public int intHours;
  public int intMinutes;
  public int intMonth;
  public int intDay;

  public long getId() {
    return id;
  }

  public List<ValidationError> validate() {

    List<ValidationError> errors = new ArrayList<>();

    int index = 0;

    while (!Character.isDigit(courseLevel.charAt(index)) && (index < (courseLevel.length() - 1))) {
      index++;
    }

    String subCourse = courseLevel.substring(0, index).trim();

    if (!Course.isCourse(subCourse)) {
      errors.add(new ValidationError("courseLevel", "Not a valid course"));
    }

    if (index == courseLevel.length() - 1) {
      errors.add(new ValidationError("courseLevel", "Please include a course level."));
    }

    int year = Calendar.getInstance().get(Calendar.YEAR);
    int mon = -1;
    int numDay = -1;

    int hours = -1;
    int minutes = -1;

    try {
      mon = Integer.parseInt(month);
    }
    catch (NumberFormatException nfe) {
      errors.add(new ValidationError("month", "NaN"));
    }

    try {
      numDay = Integer.parseInt(day);
    }
    catch (NumberFormatException nfe) {
      errors.add(new ValidationError("day", "NaN"));
    }

    if (mon > 12 || mon < 1) {
      errors.add(new ValidationError("month", "Not a valid month"));
    }
    else {
      this.intMonth = mon;
    }

    if (numDay < 1) {
      errors.add(new ValidationError("day", "Not a valid day."));
    }
    else {
      this.intDay = numDay;
    }

    if ((mon == 4 || mon == 6 || mon == 9 || mon == 11) && (numDay >= 31)) {
      errors.add(new ValidationError("day", "Cannot have more than 30 numDays in this mon"));
    }
    else {
      this.intMonth = mon;
      this.intDay = numDay;
    }

    if ((mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) && (numDay >= 32)) {
      errors.add(new ValidationError("day", "Cannot have more than 31 numDays in this mon"));
    }
    else {
      this.intMonth = mon;
      this.intDay = numDay;
    }

    if (isLeapYear(year) && mon == 2 && numDay >= 30) {
      errors.add(new ValidationError("day", "Cannot have more than 29 numDays in Feb"));
    }
    else {
      this.intMonth = mon;
      this.intDay = numDay;
    }

    if (isLeapYear(year) && mon == 2 && numDay >= 29) {
      errors.add(new ValidationError("day", "Cannot have more than 28 numDays in Feb"));
    }
    else {
      this.intMonth = mon;
      this.intDay = numDay;
    }

    try {
      hours = Integer.parseInt(hour);
    }
    catch (NumberFormatException nfe) {
      errors.add(new ValidationError("hour", "NaN"));
    }

    if (hours > 24 || hours < 0) {
      errors.add(new ValidationError("hour", "Not a valid hour."));
    }
    else {
      this.intHours = hours;
    }

    try {
      minutes = Integer.parseInt(min);
    }
    catch (NumberFormatException nfe) {
      errors.add(new ValidationError("min", "NaN"));
    }

    if (minutes > 59 || minutes < 0) {
      errors.add(new ValidationError("min", "Not a valid minute"));
    }
    else {
      this.intMinutes = minutes;
    }

    return errors.isEmpty() ? null : errors;

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
