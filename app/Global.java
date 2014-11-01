import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import models.Course;
import play.Application;
import play.GlobalSettings;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.SimpleResult;
import views.html.InvalidUrl;
import static play.mvc.Results.notFound;
import static play.mvc.Results.badRequest;

public class Global extends GlobalSettings {

  File courses = new File("public/files/Courses.txt");
  BufferedReader br = null;

  public void onStart(Application app) {

    if (Course.find().all().size() == 0) {
      loadCourses(courses);
    }

  }

  public Promise<SimpleResult> onHandlerNotFound(RequestHeader request) {
    return Promise.<SimpleResult> pure(notFound(InvalidUrl.render("Error 404")));
  }

  public Promise<SimpleResult> onBadRequest(RequestHeader request, String error) {
    return Promise.<SimpleResult> pure(badRequest("Don't try to hack the URL!"));
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

        Course course = new Course(split[1].trim(), split[0].trim());

        course.save();
      }
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
