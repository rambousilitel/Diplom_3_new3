package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {

    private final By profileHeader = By.xpath("//a[contains(@href,'account/profile') or contains(text(),'Профиль')]");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By logoButton = By.xpath("//div[contains(@class,'AppHeader_header__logo')]");
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean isProfilePageOpened() {
        return !driver.findElements(profileHeader).isEmpty();
    }

    @Step("Нажать кнопку 'Конструктор' из личного кабинета")
    public void clickConstructor() {
        click(constructorButton);
    }

    @Step("Нажать логотип из личного кабинета")
    public void clickLogo() {
        click(logoButton);
    }

    @Step("Выйти из аккаунта")
    public void logout() {
        click(logoutButton);
    }
}
