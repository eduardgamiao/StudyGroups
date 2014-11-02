package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Course;
import models.Misc;
import models.StudyGroup;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.StudyGroupForm;
import views.html.InvalidUrl;
import views.html.studyGroup.CreateStudyGroup;
import views.html.studyGroup.ListStudyGroup;
import views.html.studyGroup.ViewStudyGroup;

public class StudyGroups extends Controller {

  public static Result listStudyGroups(String course) {
    Course courseName = Course.find().where().eq("id", course).findUnique();

    List<String> classes = new ArrayList<>();
    classes.add("SDLKFJLKDSFJ");
    classes.add("SLKDFJLKDSFJl");
    return ok(ListStudyGroup.render(course + "- list of Study Groups", courseName, classes));
  }

  public static Result createStudyGroup() {
    StudyGroupForm sgf = new StudyGroupForm();
    Form<StudyGroupForm> sgForm = Form.form(StudyGroupForm.class).fill(sgf);
    return ok(CreateStudyGroup.render("Create Study Group", sgForm));
  }

  public static Result createSgForCourse(String courseName) {
    StudyGroupForm sgf = new StudyGroupForm(courseName);
    Form<StudyGroupForm> sgForm = Form.form(StudyGroupForm.class).fill(sgf);
    return ok(CreateStudyGroup.render("Create Study Group", sgForm));
  }

  public static Result addStudyGroup() {

    Form<StudyGroupForm> sgf = Form.form(StudyGroupForm.class).bindFromRequest();

    if (sgf.hasErrors()) {
      return badRequest(CreateStudyGroup.render("CreateStudyGroup", sgf));
    }
    else {

      System.out.println("CRASHING");
      StudyGroupForm form = sgf.get();

      StudyGroup sg =
          new StudyGroup(form.id, form.course, form.intLevel, form.location, form.intMonth, form.intDay, form.year,
              form.intHours, form.intMinutes, form.topics);

      sg.save();

      return redirect(routes.StudyGroups.viewStudyGroup(sg.getId(), Misc.slugify(sg.classToString())));
    }
  }

  public static Result viewStudyGroup(long id, String courseId) {

    StudyGroup sg = StudyGroup.find().where().eq("id", id).findUnique();

    if (sg == null) {
      return ok(InvalidUrl.render("Error 404"));
    }

    System.out.println(sg.classToString());
    return ok(ViewStudyGroup.render(sg.classToString(), sg));
  }

}
