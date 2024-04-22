import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Test;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;

public class CreatePostTest extends BaseTest {

    @Test
    public void successPostCreation() {
        registerNewUser();
        homePage.clickOnCreatePostButton();
        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        createPost.enterTitle(title);
        createPost.enterDescription(description);
        createPost.enterContent(content);
        createPost.uploadImage("/Users/annganzha/Desktop/foto/cat.jpeg");
        createPost.clickSubmitButton();
        refresh();
        homePage.clickOnMyPostsButton();
        myPostsPage.getFirstPostTitle().shouldHave(text(title));
    }

    @Test
    public void failedPostCreationWithEmptyFields() {
        registerNewUser();
        homePage.clickOnCreatePostButton();
        createPost.clickSubmitButton();
        createPost.getTitleField().shouldBe(Condition.visible);
        createPost.getDescriptionField().shouldBe(Condition.visible);
        createPost.getContentField().shouldBe(Condition.visible);
        createPost.getErrorMessage().shouldHave(text("Please fill the field"));
    }

    @Test
    public void failedPostCreationWithTitleLongerThan40Symbols() {
        registerNewUser();
        String title = faker.lorem().characters(45);
        String description = generateRandomDescription();
        String content = generateRandomContent();
        homePage.clickOnCreatePostButton();
        createPost.enterTitle(title);
        createPost.enterDescription(description);
        createPost.enterContent(content);
        createPost.clickSubmitButton();
        createPost.getContentField().shouldBe(Condition.visible);
        createPost.getErrorMessage().shouldHave(text("40 symbols max"));
    }

    @Test
    public void failedPostCreationWithDescriptionLongerThan100Symbols() {
        registerNewUser();
        String title = generateRandomTitle();
        String description = faker.lorem().characters(101);
        String content = generateRandomContent();
        homePage.clickOnCreatePostButton();
        createPost.enterTitle(title);
        createPost.enterDescription(description);
        createPost.enterContent(content);
        createPost.clickSubmitButton();
        createPost.getContentField().shouldBe(Condition.visible);
        createPost.getErrorMessage().shouldHave(text("100 symbols max"));
    }

    @Test
    public void failedPostCreationWithImageInWrongFormat() {
        registerNewUser();
        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        homePage.clickOnCreatePostButton();
        createPost.enterTitle(title);
        createPost.enterDescription(description);
        createPost.enterContent(content);
        createPost.uploadImage("/Users/annganzha/Desktop/foto/Red and Blue Simple Vintage Guess The Song Music Quiz Game Presentation.gif");
        createPost.getImageError().shouldHave(text("Only jpg, jpeg, png"));
        createPost.clickSubmitButton();
    }

    @Test
    public void failedPostCreationWithImageMoreThan2MB() {
        registerNewUser();
        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        homePage.clickOnCreatePostButton();
        createPost.enterTitle(title);
        createPost.enterDescription(description);
        createPost.enterContent(content);
        createPost.uploadImage("/Users/annganzha/Desktop/foto/high-angle-of-chocolate-desserts-with-coconut-flakes-and-chocolate-chips.jpeg");
        createPost.getImageError().shouldHave(text("File size exceeds the 2MB limit"));
        createPost.clickSubmitButton();
        createPost.getContentField().shouldBe(Condition.visible);
    }

    @Test
    public void successSavingPostAsDraft() {
        registerNewUser();
        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        homePage.clickOnCreatePostButton();
        createPost.enterTitle(title);
        createPost.enterDescription(description);
        createPost.enterContent(content);
        createPost.clickDraftButton();
        createPost.clickSubmitButton();
        homePage.clickOnMyDraftsButton();
        myDraftsPage.getFirstPostTitle().shouldHave(text(title));
    }

    @Test
    public void postAsDraftIsNotSavedInPostList() {
        registerNewUser();
        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        homePage.clickOnCreatePostButton();
        createPost.enterTitle(title);
        createPost.enterDescription(description);
        createPost.enterContent(content);
        createPost.clickDraftButton();
        createPost.clickSubmitButton();
        homePage.clickOnMyPostsButton();
        ElementsCollection filteredTitles = myPostsPage.getPostTitles().filterBy(text(title));
        filteredTitles.shouldHave(size(0));
    }

    @Test
    public void failedPostCreationIfDelayWithDateInThePast() {
        registerNewUser();
        String title = generateRandomTitle();
        String description = generateRandomDescription();
        String content = generateRandomContent();
        homePage.clickOnCreatePostButton();
        createPost.enterTitle(title);
        createPost.enterDescription(description);
        createPost.enterContent(content);
        createPost.enterPublishDate("04-05-2023");
        createPost.getErrorMessage().shouldHave(text("Date can’t be earlier then today"));
        createPost.clickSubmitButton();
        homePage.clickOnMyPostsButton();
        ElementsCollection filteredTitles = myPostsPage.getPostTitles().filterBy(text(title));
        filteredTitles.shouldHave(size(0));
    }
}