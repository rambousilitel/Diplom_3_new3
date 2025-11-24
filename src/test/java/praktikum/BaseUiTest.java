package praktikum;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class BaseUiTest {

    protected WebDriver driver;
    protected final Browser browser;

    protected static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    public BaseUiTest(Browser browser) {
        this.browser = browser;
    }

    @Before
    @Step("Открыть браузер {browser} и перейти на главную страницу")
    public void setUp() {
        driver = WebDriverFactory.create(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(BASE_URL);
    }

    @After
    @Step("Закрыть браузер")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
