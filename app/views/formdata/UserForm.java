package views.formdata;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;
import models.UserInfoDB;
import play.data.Form;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

/**
 * 
 * @author Alvin Prieto
 *
 */
public class UserForm {

  @Id
  private long id;

  @Constraints.Required(message = "A first name is required.")
  private String firstName;

  @Constraints.Required(message = "A last name is required.")
  private String lastName;

  @Constraints.Required(message = "An email is required.")
  private String email;

  @Constraints.Required(message = "A password is required.")
  private String password;

  /**
   * Constructor.
   */
  public UserForm() {
  }

  /**
   * Validation Method.
   * 
   * @return a list of errors
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();

    if (UserInfoDB.isUser(email)) {
      errors.add(new ValidationError("email", "Email already exists."));
    }

    if (!email.contains("@") || !email.endsWith(".com")) {
      errors.add(new ValidationError("email", "Not a valid Email adress."));
    }

    // Password must be between 4 and 8 characters long
    // Password must contain at least one number
    if (!password.matches("^(?=.*\\d).{4,8}$")) {
      errors.add(new ValidationError("password",
          "Password must be 4 to 8 characters long and contain a number."));
    }

    return errors.isEmpty() ? null : errors;
  }

  /**
   * Returns an empty user form.
   * 
   * @return an empty user form.
   */
  public static Form<UserForm> getForm() {
    UserForm uf = new UserForm();
    Form<UserForm> userForm = Form.form(UserForm.class).fill(uf);
    return userForm;
  }

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

}
