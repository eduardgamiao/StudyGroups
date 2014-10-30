import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import models.Course;
import play.*;

public class Global extends GlobalSettings {

  File courses = new File("public/files/Courses.txt");
  BufferedReader br = null;

  public void onStart(Application app) {

    if (Course.find().all().size() == 0) {
      loadCourses(courses);
    }

  }

  private void loadCourses(File file) {

    String line = "";

    try {
      br = new BufferedReader(new FileReader(file));
    }
    catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      while ((line = br.readLine()) != null) {
        String[] split = line.split("[()]");

        Course course = new Course(split[0].trim(), split[1].trim());

        course.save();
      }
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
