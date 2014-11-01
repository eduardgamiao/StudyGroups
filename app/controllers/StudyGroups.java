package controllers;

import models.StudyGroup;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.StudyGroupForm;
import views.html.studyGroup.CreateStudyGroup;
import views.html.studyGroup.ViewStudyGroup;

public class StudyGroups extends Controller {

  public static Result createStudyGroup() {
    StudyGroupForm sgf = new StudyGroupForm();
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
          new StudyGroup(form.id, form.courseLevel, form.location, form.intMonth, form.intDay, form.intHours,
              form.intMinutes, form.topics);

      sg.save();

      System.out.println("\nFORM ID: " + sg.getId() + "\n");
      return redirect(routes.StudyGroups.viewStudyGroup(sg.getId()));
    }
  }

  public static Result viewStudyGroup(long id) {
    StudyGroup sg = StudyGroup.find().where().eq("id", id).findUnique();

    System.out.println("\nPRINTING " + sg + "\n");
    return ok(ViewStudyGroup.render(sg.getCourseLevel(), sg));
  }

}
