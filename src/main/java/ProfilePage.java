import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage {
    private SelenideElement profileFormTitle = $(byClassName("post-header__feed"));
    private SelenideElement editButton = $(byAttribute("data-test", "post-header__plus"));
    private SelenideElement nameInputField = $(byAttribute("data-test", "profileName"));
    private SelenideElement surnameInputField = $(byAttribute("data-test", "profileSurname"));
    private SelenideElement birthDateInputField = $(byId("birthDate"));
    private SelenideElement genderOption = $(byId("gender"));
    private SelenideElement phoneInputField = $("input[name='phone']");
    private SelenideElement saveButton = $(byClassName("save__btn"));
    private SelenideElement passwordChangeButton = $(byClassName("pass__btn"));
    private SelenideElement errorMessage = $(byClassName("error"));
    private SelenideElement avatarUpload = $("input[type='file'][accept='image/png,.png,image/jpg,.jpg,image/jpeg,.jpeg']");

    public SelenideElement getPhone() {
        return phoneInputField;
    }
    public void uploadImage(String imagePath) {
        avatarUpload.uploadFile(new File(imagePath));
    }
    public SelenideElement getErrorMessage() {
        return errorMessage;
    }
    public SelenideElement getProfileFormTitle() {
        return profileFormTitle;
    }
    public SelenideElement getSurname() {
        return surnameInputField;
    }
    public SelenideElement getGenderOption() {
        return genderOption;
    }
    public void clickOnEditButton() {
        editButton.click();
    }
    public void clickOnSaveButton() {
        saveButton.shouldBe(Condition.visible);
        saveButton.click();
    }
    public void clickOnPasswordChangeButton() {
        passwordChangeButton.click();
    }
    public void enterName(String name) {
        nameInputField.shouldBe(Condition.visible);
        nameInputField.clear();
        nameInputField.setValue(name);
    }
    public void enterSurname(String surname) {
        surnameInputField.shouldBe(Condition.visible);
        surnameInputField.clear();
        surnameInputField.setValue(surname);
    }
    public void enterBirthDate(String birthDate) {
        birthDateInputField.shouldBe(Condition.visible);
        birthDateInputField.click();
        birthDateInputField.sendKeys(birthDate);
    }
    public void selectGender(String gender) {
        genderOption.selectOptionContainingText(gender);
    }
    public void enterPhoneNumber(String phoneNumber) {
        phoneInputField.shouldBe(Condition.visible);
        phoneInputField.clear();
        phoneInputField.setValue(phoneNumber);
    }
}