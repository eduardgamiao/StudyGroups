package models;

import org.joda.time.DateTime;

public class DateTimeInfo {

  private static DateTime today = new DateTime();

  public static int getMonth() {
    return today.monthOfYear().get();
  }

  public static int getDay() {
    return today.dayOfMonth().get();
  }

  public static int getYear() {
    return today.year().get();
  }

  public static int getHour() {
    return today.hourOfDay().get();
  }

  public static int getMinute() {
    return today.minuteOfHour().get();
  }

}