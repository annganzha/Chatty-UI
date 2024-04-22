import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.After;
import org.junit.Before;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest extends Helper {

    final String BASE_URL = "http://chatty.telran-edu.de:8089/login";
    Helper helper = new Helper();

    @Before
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.timeout = 4000;
        open(BASE_URL);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}