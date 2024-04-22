import com.codeborne.selenide.Condition;
import org.junit.Test;

public class LogoutTest extends BaseTest{
    @Test
    public void successLogout() {
        String email = "user.test@gmail.com";
        String username = getUsernameFromEmail(email);
        loginPage.enterEmail(email);
        loginPage.enterPassword("User1234");
        loginPage.clickOnLoginButton();
        header.clickOnHeaderUserMenu();
        header.clickOnLogoutButton();
        loginPage.getFormTitle().shouldHave(Condition.text("Login Form"));
    }
}
