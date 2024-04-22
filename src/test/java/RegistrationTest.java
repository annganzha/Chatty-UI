import com.codeborne.selenide.Condition;
import org.junit.Test;

import static com.codeborne.selenide.Condition.*;

public class RegistrationTest extends BaseTest {

    @Test
    public void successRegistration() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 100);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.clickOnRegistrationButton();
        String username = getUsernameFromEmail(email);
        header.getHeaderUserText().shouldHave(Condition.text(username));
    }

    @Test
    public void failedRegistrationWithEmptyEmail() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String password = faker.internet().password(8, 100);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.getErrorMessage().shouldHave(text("Email cannot be empty"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));
    }

    @Test
    public void failedRegistrationWithEmailWithoutAt() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        registrationPage.enterEmail("user1gmail.com");
        String password = faker.internet().password(8, 100);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.getErrorMessage().shouldHave(text("Incorrect email format"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));
    }

    @Test
    public void failedRegistrationWithEmptyPassword() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        registrationPage.enterEmail(email);
        registrationPage.getErrorMessage().shouldHave(text("Password cannot be empty"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));
    }

    @Test
    public void failedRegistrationWithExistingEmail() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        registrationPage.enterEmail("admin.admin@gmail.com");
        String password = faker.internet().password(8, 100);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.clickOnRegistrationButton();
        registrationPage.getErrorMessage().shouldHave(text("Email already exists!"));
    }

    @Test
    public void failedRegistrationWithInvalidShortPassword() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(1, 7);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.getErrorMessage().shouldHave(text("Password must be 8-100 characters and include at least one letter and one digit"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));
    }

    @Test
    public void failedRegistrationWithInvalidLongPassword() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(101, 110);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.getErrorMessage().shouldHave(text("Password must be 8-100 characters and include at least one letter and one digit"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));
    }

    @Test
    public void failedRegistrationWithPasswordWithoutAnyLetters() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        String password = faker.number().digits(10);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.getErrorMessage().shouldHave(text("Password must be 8-100 characters and include at least one letter and one digit"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));
    }

    @Test
    public void failedRegistrationWithPasswordWithoutAnyDigits() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        String password = faker.lorem().characters(10, true, false);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.getErrorMessage().shouldHave(text("Password must be 8-100 characters and include at least one letter and one digit"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));
    }

    @Test
    public void failedRegistrationWithEmptyConfirmPassword() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 100);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.getErrorMessage().shouldHave(text("Confirm password cannot be empty"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));
    }

    @Test
    public void failedRegistrationIfConfirmPasswordDiffersFromPassword() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 100);
        String confirmPassword = faker.internet().password(8, 100);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(confirmPassword);
        registrationPage.getErrorMessage().shouldHave(text("Passwords do not match"));
        registrationPage.getRegistrationButton().shouldHave(attribute("disabled"));
    }

    @Test
    public void allFieldsForRegistrationAreVisible() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        registrationPage.getEmailInputField().shouldBe(visible);
        registrationPage.getNameInputField().shouldBe(visible);
        registrationPage.getUsernameInputField().shouldBe(visible);
        registrationPage.getSurnameInputField().shouldBe(visible);
        registrationPage.getPasswordInputField().shouldBe(visible);
        registrationPage.getConfirmPasswordInputField().shouldBe(visible);
        registrationPage.getBirthDateInputField().shouldBe(visible);
        registrationPage.getPhoneInputField().shouldBe(visible);
        registrationPage.getGenderOption().shouldBe(visible);
    }

    @Test
    public void successRegistrationWithAllFields() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 100);
        String username = faker.name().username();
        String name = faker.name().firstName();
        String surname = faker.name().lastName();
        String phone = faker.number().digits(11);
        String birthDate = helper.generateRandomBirthDate();
        String usernameFromEmail = getUsernameFromEmail(email);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.enterUsername(username);
        registrationPage.enterName(name);
        registrationPage.enterSurname(surname);
        registrationPage.enterBirthDate(birthDate);
        registrationPage.selectGender("Male");
        registrationPage.enterPhone(phone);
        registrationPage.clickOnRegistrationButton();
        header.getHeaderUserText().shouldHave(Condition.text(usernameFromEmail));
    }
}