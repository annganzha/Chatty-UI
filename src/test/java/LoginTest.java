import com.codeborne.selenide.Condition;
import org.junit.Test;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;

public class LoginTest extends BaseTest {

    @Test
    public void successLogin() {
        String email = "admin.admin@gmail.com";
        String username = getUsernameFromEmail(email);
        loginPage.enterEmail(email);
        loginPage.enterPassword("Admin12345");
        loginPage.clickOnLoginButton();
        header.getHeaderUserText().shouldHave(Condition.text(username));
    }

    @Test
    public void failedLoginIfEmailHasNoAtSign() {
        loginPage.enterEmail("admin.admingmail.com");
        loginPage.enterPassword("Admin12345");
        loginPage.getLoginButton().shouldHave(attribute("disabled"));
    }

    @Test
    public void failedLoginWithInvalidPassword() {
        loginPage.enterEmail("admin.admin@gmail.com");
        loginPage.enterPassword("Admin1234");
        loginPage.clickOnLoginButton();
        loginPage.getFormTitle().shouldHave(text("Login Form"));
        loginPage.getErrorMessage().shouldHave(text("Invalid email or password. Please try again"));
    }

    @Test
    public void failedLoginWithEmptyPassword() {
        loginPage.enterEmail("admin.admin@gmail.com");
        loginPage.clickOnLoginButton();
        loginPage.getFormTitle().shouldHave(text("Login Form"));
        loginPage.getErrorMessage().shouldHave(text("Password can not be empty"));
    }

    @Test
    public void failedLoginWithNotExistingUser() {
        loginPage.enterEmail("admin.admin345@gmail.com");
        loginPage.enterPassword("User12345");
        loginPage.clickOnLoginButton();
        loginPage.getFormTitle().shouldHave(text("Login Form"));
        loginPage.getErrorMessage().shouldHave(text("User not found. Please register."));
    }

    @Test
    public void failedLoginWithEmptyCredentials() {
        loginPage.getLoginButton().shouldHave(attribute("disabled"));
    }
}