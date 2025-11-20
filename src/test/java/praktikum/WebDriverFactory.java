package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    @Step("Инициализировать WebDriver для браузера {browser}")
    public static WebDriver create(Browser browser) {
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case YANDEX:

                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();

п                options.setBinary("C:\\Users\\jops1\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");

                System.setProperty("webdriver.chrome.driver", "D:\\yandexdriver.exe");

                return new ChromeDriver(options);

            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
    }
}
