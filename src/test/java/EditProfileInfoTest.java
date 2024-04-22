import com.codeborne.selenide.*;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertNotEquals;

public class EditProfileInfoTest extends BaseTest {

    @Test
    public void successNameChange() {
        registerNewUser();
        String firstName = faker.name().firstName();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterName(firstName);
        profilePage.clickOnSaveButton();
        homePage.clickOnHomeButton();
        refresh();
        header.getHeaderUserText().shouldHave(text(firstName));
    }

    @Test
    public void successSurnameChange() {
        registerNewUser();
        String lastName = faker.name().lastName();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterSurname(lastName);
        profilePage.clickOnSaveButton();
        profilePage.getSurname().shouldHave(attribute("value", lastName));
    }

    @Test
    public void successPhoneChange() {
        registerNewUser();
        String phone = faker.number().digits(11);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterPhoneNumber(phone);
        profilePage.clickOnSaveButton();
        profilePage.getPhone().shouldHave(attribute("value", "+" + phone));
    }

    @Test
    public void successSavingGender() {
        registerNewUser();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.selectGender("MALE");
        profilePage.clickOnSaveButton();
        profilePage.getGenderOption().shouldHave(text("MALE"));
    }

    @Test
    public void successAvatarEditing() {
        registerNewUser();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        String avatarSrcBefore = $("img[data-test='uploaded-image']").getAttribute("src");
        profilePage.uploadImage("/Users/annganzha/Desktop/foto/cat.jpeg");
        profilePage.clickOnSaveButton();
        String avatarSrcAfter = $("img[data-test='uploaded-image']").getAttribute("src");
        assertNotEquals(avatarSrcBefore, avatarSrcAfter);
    }

    @Test
    public void failedSavingWithEmptyName() {
        registerNewUser();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterName("");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This field cannot be empty"));
    }

    @Test
    public void failedSavingWithNameLessThanThreeSymbols() {
        registerNewUser();
        String name = faker.lorem().characters(2, true, false);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterName(name);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("From 3 to 20 symbols"));
    }

    @Test
    public void failedSavingWithNameContainingNumbers() {
        registerNewUser();
        String name = faker.lorem().characters(10, true, true);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterName(name);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Only latin letters allowed"));
    }

    @Test
    public void failedSavingWithNameLongerThanTwentySymbols() {
        registerNewUser();
        String name = faker.lorem().characters(22, true, false);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterName(name);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("From 3 to 20 symbols"));
    }

    @Test
    public void failedSavingWithEmptySurname() {
        registerNewUser();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterSurname("");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This field cannot be empty"));
    }

    @Test
    public void failedSavingWithSurnameLessThanThreeSymbols() {
        registerNewUser();
        String surname = faker.lorem().characters(2, true, false);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterSurname(surname);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("From 3 to 20 symbols"));
    }

    @Test
    public void failedSavingWithSurnameLongerThanTwentySymbols() {
        registerNewUser();
        String surname = faker.lorem().characters(22, true, false);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterSurname(surname);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("From 3 to 20 symbols"));
    }

    @Test
    public void failedSavingWithSurnameContainingNumbers() {
        registerNewUser();
        String surname = faker.lorem().characters(10, true, true);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterSurname(surname);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Only latin letters allowed"));
    }

    @Test
    public void failedSavingWithSurnameContainingSpecialCharacters() {
        registerNewUser();
        String surname = faker.lorem().characters(10, true, true) + "@";
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterSurname(surname);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Only latin letters allowed"));
    }

    @Test
    public void failedSavingWithCurrentDateAsBirthdate() {
        registerNewUser();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        LocalDate currentDate = LocalDate.now();
        String currentDateStr = DateTimeFormatter.ofPattern("ddMMyyyy").format(currentDate);
        profilePage.enterBirthDate(currentDateStr);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Incorrect birthdate"));
    }

    @Test
    public void failedSavingWithDateInTheFutureAsBirthdate() {
        registerNewUser();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterBirthDate("11.11.2040");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Incorrect birthdate"));
    }

    @Test
    public void failedSavingBirthdateIfAgeGreaterThan100() {
        registerNewUser();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterBirthDate("11.11.1911");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Incorrect birthdate"));
    }

    @Test
    public void failedSavingBirthdateIfAgeUnder7() {
        registerNewUser();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        String birthdate = helper.generateRandomBirthDateUnder7Years();
        profilePage.enterBirthDate(birthdate);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("Incorrect birthdate"));
    }

    @Test
    public void failedSavingIfBirthdateIsEmpty() {
        registerNewUser();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterBirthDate("");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This field can not be empty"));
    }

    @Test
    public void failedSavingWithEmptyPhone() {
        registerNewUser();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterPhoneNumber("");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This field can not be empty"));
    }

    @Test
    public void failedSavingWithShortPhone() {
        registerNewUser();
        String phone = faker.number().digits(8);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterPhoneNumber(phone);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This value is not valid"));
    }

    @Test
    public void failedSavingWithLongPhone() {
        registerNewUser();
        String phone = faker.number().digits(21);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterPhoneNumber(phone);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This value is not valid"));
    }

    @Test
    public void failedSavingWithInvalidPhoneFormat() {
        registerNewUser();
        String phone = faker.lorem().characters(11, false, true);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterPhoneNumber(phone);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This value is not valid"));
    }

    @Test
    public void failedSavingWithNotUniquePhone() {
        registerNewUser();
        String phone = faker.number().digits(11);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterPhoneNumber(phone);
        profilePage.clickOnSaveButton();
        profilePage.getPhone().shouldHave(value("+" + phone));
        closeWebDriver();
        open("http://chatty.telran-edu.de:8089/login");
        registerNewUser();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.enterPhoneNumber(phone);
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("is not unique"));
    }

    @Test
    public void failedSavingIfGenderIsNotSet() {
        registerNewUser();
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnEditButton();
        profilePage.selectGender("Select");
        profilePage.clickOnSaveButton();
        profilePage.getErrorMessage().shouldHave(text("This field can not be empty"));
    }
}