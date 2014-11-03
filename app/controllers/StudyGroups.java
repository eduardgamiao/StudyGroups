package controllers;

import java.util.ArrayList;
import java.util.List;
import models.ClassLevel;
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
import views.html.studyGroup.StudyGroupsForClass;
import views.html.studyGroup.ViewStudyGroup;

public class StudyGroups extends Controller {

  public static Result listStudyGroups(String course) {

    Course courseName = Course.getCourse(course);
    String[] courses = courseName.getClasses().split("\\|");
    List<ClassLevel> classes = new ArrayList<>();

    for (int x = 0; x < courses.length; x++) {
      ClassLevel cl = ClassLevel.getCL(courses[x]);
      classes.add(cl);
    }

    return ok(ListStudyGroup.render(course + "- list of Study Groups", courseName, classes));
  }

  public static Result viewClassStudyGroup(String classLevel) {
    ClassLevel cl = ClassLevel.getCL(Misc.unSlugify(classLevel));
    String[] studyGroups = cl.getStudyGroups().split("\\|");
    List<StudyGroup> sg = new ArrayList<>();

    for (int x = 0; x < studyGroups.length; x++) {
      StudyGroup group = StudyGroup.getSG(Long.parseLong(studyGroups[x]));
      sg.add(group);
    }
    return ok(StudyGroupsForClass.render(Misc.unSlugify(classLevel), cl, sg));
  }

  public static Result createStudyGroup() {
    StudyGroupForm sgf = new StudyGroupForm();
    Form<StudyGroupForm> sgForm = Form.form(StudyGroupForm.class).fill(sgf);
    return ok(CreateStudyGroup.render("Create Study Group", sgForm));
  }

  public static Result createSgForCourse(String courseName) {
    StudyGroupForm sgf = new StudyGroupForm(Misc.unSlugify(courseName));
    Form<StudyGroupForm> sgForm = Form.form(StudyGroupForm.class).fill(sgf);
    return ok(CreateStudyGroup.render("Create Study Group", sgForm));
  }

  public static Result createSgForClass(String classLevel) {
    ClassLevel cl = ClassLevel.getCL(Misc.unSlugify(classLevel));
    StudyGroupForm sgf = new StudyGroupForm(cl.getCourse(), Integer.toString(cl.getLevel()));
    Form<StudyGroupForm> sgForm = Form.form(StudyGroupForm.class).fill(sgf);
    return ok(CreateStudyGroup.render("Create Study Group", sgForm));
  }

  public static Result addStudyGroup() {

    Form<StudyGroupForm> sgf = Form.form(StudyGroupForm.class).bindFromRequest();

    if (sgf.hasErrors()) {
      return badRequest(CreateStudyGroup.render("CreateStudyGroup", sgf));
    }
    else {

      StudyGroupForm form = sgf.get();

      StudyGroup sg =
          new StudyGroup(form.id, form.course, form.intLevel, form.location, form.intMonth, form.intDay, form.year,
              form.intHours, form.intMinutes, form.topics);

      sg.save();

      ClassLevel cl = new ClassLevel(form.course, form.intLevel);
      cl.addStudyGroup(sg.getId());
      cl.save();

      Course course = Course.getCourse(form.course);
      course.addClass(cl);
      course.save();

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
