import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

public class Header {
    private SelenideElement headerUserText = $(byClassName("header__user"));
    private SelenideElement logoutButton = $("[href='/login']");
    private SelenideElement headerDropdownMenu = $(By.className("dropdown-menu"));
    public SelenideElement getHeaderUserText() {
        return headerUserText;
    }
    public void clickOnLogoutButton(){
        headerUserText.click();
        logoutButton.click();
    }
    public SelenideElement getHeaderDropdownMenu() {
        return headerDropdownMenu;
    }
    public void selectYourProfileOption() {
        headerDropdownMenu.$(By.xpath(".//a[contains(text(), 'Your Profile')]")).click();
    }
    public void clickOnHeaderUserMenu(){
        headerUserText.click();
    }
}
