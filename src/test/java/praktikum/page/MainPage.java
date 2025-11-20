package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    // Кнопка "Войти в аккаунт" на главной
    private final By loginAccountButton = By.xpath("//button[text()='Войти в аккаунт']");

    // Кнопка "Личный Кабинет" в шапке
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");

    // Кнопка "Конструктор" в шапке
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");

    // Логотип Stellar Burgers
    private final By logoButton = By.xpath("//div[contains(@class,'AppHeader_header__logo')]");

    // Вкладки конструктора
    private final By bunsTab = By.xpath("//span[text()='Булки']/..");
    private final By saucesTab = By.xpath("//span[text()='Соусы']/..");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']/..");

    // Заголовки секций
    private final By bunsHeader = By.xpath("//h2[text()='Булки']");
    private final By saucesHeader = By.xpath("//h2[text()='Соусы']");
    private final By fillingsHeader = By.xpath("//h2[text()='Начинки']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать кнопку 'Войти в аккаунт' на главной странице")
    public void clickLoginAccount() {
        click(loginAccountButton);
    }

    @Step("Нажать кнопку 'Личный Кабинет'")
    public void clickPersonalAccount() {
        click(personalAccountButton);
    }

    @Step("Нажать кнопку 'Конструктор'")
    public void clickConstructor() {
        click(constructorButton);
    }

    @Step("Нажать на логотип Stellar Burgers")
    public void clickLogo() {
        click(logoButton);
    }

    @Step("Перейти к разделу 'Булки'")
    public void openBunsSection() {
        click(bunsTab);
    }

    @Step("Перейти к разделу 'Соусы'")
    public void openSaucesSection() {
        click(saucesTab);
    }

    @Step("Перейти к разделу 'Начинки'")
    public void openFillingsSection() {
        click(fillingsTab);
    }

    public boolean isBunsSectionVisible() {
        return find(bunsHeader).isDisplayed();
    }

    public boolean isSaucesSectionVisible() {
        return find(saucesHeader).isDisplayed();
    }

    public boolean isFillingsSectionVisible() {
        return find(fillingsHeader).isDisplayed();
    }
}
