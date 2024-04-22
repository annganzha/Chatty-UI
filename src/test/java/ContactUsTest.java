import com.codeborne.selenide.Condition;
import org.junit.Test;

import static com.codeborne.selenide.Condition.text;

public class ContactUsTest extends BaseTest{

    @Test
    public void successFeedbackWithValidData() {
        registerNewUser();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String message = generateRandomContent();
        homePage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldHave(Condition.text("Feedback submitted successfully!"));
    }

    @Test
    public void failedFeedbackWithEmptyName() {
        registerNewUser();
        String email = faker.internet().emailAddress();
        String message = generateRandomContent();
        homePage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
    }

    @Test
    public void failedFeedbackWithEmptyEmail() {
        registerNewUser();
        String name = faker.name().fullName();
        String message = generateRandomContent();
        homePage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
    }

    @Test
    public void failedFeedbackWithEmptyContent() {
        registerNewUser();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        homePage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);
        contactPage.enterEmail(email);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
    }

    @Test
    public void failedFeedbackWithNameContainingLessThanTwoSymbols() {
        registerNewUser();
        String name = faker.lorem().characters(1);
        String email = faker.internet().emailAddress();
        String message = generateRandomContent();
        homePage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
    }

    @Test
    public void failedFeedbackWithNameLongerThanThirtySymbols() {
        registerNewUser();
        String name = faker.lorem().characters(35);
        String email = faker.internet().emailAddress();
        String message = generateRandomContent();
        homePage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
    }

    @Test
    public void failedFeedbackWithEmailWithoutAt() {
        registerNewUser();
        String name = faker.name().fullName();
        String email = faker.name().username();
        String message = generateRandomContent();
        homePage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
        contactPage.getErrorMessage().shouldHave(text("Invalid email format"));
    }

    @Test
    public void failedFeedbackWithContentLessThanTwoSymbols() {
        registerNewUser();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String message = faker.lorem().characters(1);
        homePage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
    }

    @Test
    public void failedFeedbackWithContentLongerThan1000Symbols() {
        registerNewUser();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String message = faker.lorem().characters(1500);
        homePage.clickOnContactLink();
        contactPage.getFormTitle().shouldHave(Condition.text("Contact Us"));
        contactPage.enterName(name);
        contactPage.enterEmail(email);
        contactPage.enterMessage(message);
        contactPage.clickSendMessageButton();
        contactPage.getSuccessMessage().shouldNot(Condition.exist);
    }
}