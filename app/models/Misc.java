package models;

/**
 * Used to slugify and unslugify urls.
 * 
 * @author Alvin Prieto
 *
 */
public class Misc {

  /**
   * Slugifies a string.
   * 
   * ex. ICS 311; normal string -> ICS%311, slugified -> ics-311
   * 
   * @param url string to be slugified.
   * @return slugified url
   */
  public static String slugify(String url) {
    return url.replace(' ', '-').toLowerCase();
  }

  /**
   * Unslugifies a string.
   * 
   * ex. ICS 311, slugified -> ics-311, unslugified -> ICS 311
   * 
   * @param url string to be unslugified
   * @return unslugified string.
   */
  public static String unSlugify(String url) {
    return url.replace('-', ' ').toUpperCase();
  }

}
