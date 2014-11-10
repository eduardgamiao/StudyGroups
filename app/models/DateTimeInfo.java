package models;

import org.joda.time.DateTime;

/**
 * A class to return the information for the current day.
 * 
 * @author Alvin Prieto
 *
 */
public class DateTimeInfo {

  private static DateTime today = new DateTime();

  /**
   * Returns the current month.
   * 
   * @return month
   */
  public static int getMonth() {
    return today.monthOfYear().get();
  }

  /**
   * Returns the current number day.
   * 
   * @return day number
   */
  public static int getDay() {
    return today.dayOfMonth().get();
  }

  /**
   * Returns the current year.
   * 
   * @return current year
   */
  public static int getYear() {
    return today.year().get();
  }

  /**
   * Returns the current hour.
   * 
   * @return current hour
   */
  public static int getHour() {
    return today.hourOfDay().get();
  }

  /**
   * Returns the current minute.
   * 
   * @return current minute
   */
  public static int getMinute() {
    return today.minuteOfHour().get();
  }

}