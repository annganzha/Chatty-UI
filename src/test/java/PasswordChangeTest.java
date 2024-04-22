import com.codeborne.selenide.Condition;
import org.junit.Test;

public class PasswordChangeTest extends BaseTest {

    @Test
    public void successPasswordChangeWithValidData() {
        registerNewUser();
        String oldPassword = password;
        String newPassword = faker.internet().password(8, 100);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.confirmPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
    }

    @Test
    public void failedPasswordChangeWithEmptyOldPassword() {
        registerNewUser();
        String newPassword = faker.internet().password(8, 100);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.confirmPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));
    }

    @Test
    public void failedPasswordChangeWithInvalidOldPassword() {
        registerNewUser();
        String oldPassword = faker.internet().password(8, 100);
        String newPassword = faker.internet().password(8, 100);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.confirmPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));
    }

    @Test
    public void failedPasswordChangeWithEmptyNewPassword() {
        registerNewUser();
        String oldPassword = password;
        String newPassword = faker.internet().password(8, 100);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.confirmPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("The new passwords do not match."));
    }

    @Test
    public void failedPasswordChangeWithEmptyConfirmPassword() {
        registerNewUser();
        String oldPassword = password;
        String newPassword = faker.internet().password(8, 100);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("The new passwords do not match."));
    }

    @Test
    public void failedPasswordChangeIfNewPasswordNotMatchConfirmPassword() {
        registerNewUser();
        String oldPassword = password;
        String newPassword = faker.internet().password(8, 100);
        String confirmPassword = faker.internet().password(8, 100);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.confirmPassword(confirmPassword);
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("The new passwords do not match."));
    }

    @Test
    public void failedPasswordChangeIfNewPasswordHasLessThanEightSymbols() {
        registerNewUser();
        String oldPassword = password;
        String newPassword = faker.internet().password(1, 7);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.confirmPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));
    }

    @Test
    public void failedPasswordChangeIfNewPasswordLongerThanHundredSymbols() {
        registerNewUser();
        String oldPassword = password;
        String newPassword = faker.internet().password(101, 120);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.confirmPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));
    }

    @Test
    public void failedPasswordChangeIfNewPasswordHasNoLetters() {
        registerNewUser();
        String oldPassword = password;
        String newPassword = faker.number().digits(10);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.confirmPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));
    }

    @Test
    public void failedPasswordChangeIfNewPasswordHasNoNumbers() {
        registerNewUser();
        String oldPassword = password;
        String newPassword = faker.lorem().characters(10, true, false);
        header.clickOnHeaderUserMenu();
        header.selectYourProfileOption();
        profilePage.getProfileFormTitle().shouldHave(Condition.text("Personal information"));
        profilePage.clickOnPasswordChangeButton();
        passwordChangeForm.getFormTitle().shouldHave(Condition.text("Password"));
        passwordChangeForm.enterOldPassword(oldPassword);
        passwordChangeForm.enterNewPassword(newPassword);
        passwordChangeForm.confirmPassword(newPassword);
        passwordChangeForm.clickOnSaveButton();
        passwordChangeForm.getErrorMessage().shouldHave(Condition.text("Failed to update password."));
    }
}