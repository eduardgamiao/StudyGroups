package models;

public class Misc {

  public static String slugify(String url) {
    return url.replace(' ', '-').toLowerCase();
  }
  
  public static String unSlugify(String url) {
    return url.replace('-', ' ').toUpperCase();
  }

}
