package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.StudyGroupForm;
import views.html.CreateStudyGroup;

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
      return redirect(routes.Application.index());
    }
  }

}
