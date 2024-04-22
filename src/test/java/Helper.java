import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Random;

import static com.codeborne.selenide.Condition.text;

public class Helper {
    LoginPage loginPage = new LoginPage();
    Header header = new Header();
    HomePage homePage = new HomePage();
    RegistrationPage registrationPage = new RegistrationPage();
    CreatePost createPost = new CreatePost();
    MyDraftsPage myDraftsPage = new MyDraftsPage();
    MyPostsPage myPostsPage = new MyPostsPage();
    ProfilePage profilePage = new ProfilePage();
    PasswordChangeForm passwordChangeForm = new PasswordChangeForm();
    ContactPage contactPage = new ContactPage();
    Faker faker = new Faker();

    public String getUsernameFromEmail(String email) {
        String[] parts = email.split("@");
        return StringUtils.left(parts[0], 12);
    }

    protected String password;
    public void registerNewUser() {
        loginPage.clickOnRegistrationLink();
        registrationPage.getFormTitle().shouldHave(text("Create Account"));
        String email = faker.internet().emailAddress();
        String registeredUsername = getUsernameFromEmail(email);
        String password = faker.internet().password(8, 100);
        registrationPage.enterEmail(email);
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
        registrationPage.clickOnRegistrationButton();
        header.getHeaderUserText().shouldHave(Condition.text(registeredUsername));
    }

    protected String generateRandomTitle() {
        String title = faker.lorem().sentence(5);
        return title.substring(0, Math.min(title.length(), 40));
    }
    protected String generateRandomDescription() {
        String description = faker.lorem().sentence(10);
        return description.substring(0, Math.min(description.length(), 100));
    }
    protected String generateRandomContent() {
        String content = faker.lorem().paragraph(20);
        return content.substring(0, Math.min(content.length(), 1000));
    }
    public String generateRandomBirthDate() {
        Random random = new Random();
        LocalDate minDate = LocalDate.now().minusYears(100);
        LocalDate maxDate = LocalDate.now().minusYears(7);
        long minDay = minDate.toEpochDay();
        long maxDay = maxDate.toEpochDay();
        long randomDay = minDay + random.nextInt((int) (maxDay - minDay));
        return LocalDate.ofEpochDay(randomDay).toString();
    }
    public String generateRandomBirthDateUnder7Years() {
        Random random = new Random();
        LocalDate minDate = LocalDate.now().minusYears(7);
        LocalDate maxDate = LocalDate.now();
        long minDay = minDate.toEpochDay();
        long maxDay = maxDate.toEpochDay();
        long randomDay = minDay + random.nextInt((int) (maxDay - minDay));
        return LocalDate.ofEpochDay(randomDay).toString();
    }
}
