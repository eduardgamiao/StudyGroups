package models;

public class Misc {

  public static String slugify(String url) {
    return url.replace(' ', '-').toLowerCase();
  }

}
