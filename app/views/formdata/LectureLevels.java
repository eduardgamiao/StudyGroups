package views.formdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Lecture;
import models.LectureDB;

public class LectureLevels {

  public static Map<String, Boolean> getLectureLevels(String course) {
    Map<String, Boolean> levelMap = new HashMap<>();
    List<Lecture> lectureList = LectureDB.getLectures(course);
    for (Lecture lecture : lectureList) {
      if (!levelMap.containsKey(lecture.getLevel())) {
        levelMap.put(lecture.getLevel(), false);
      }
    }
    return levelMap;
  }
}
