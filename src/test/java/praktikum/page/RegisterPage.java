package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//label[text()='Пароль']/following-sibling::input");

    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final By passwordError = By.xpath("//p[contains(@class,'input__error') and text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Заполнить форму регистрации: {name}, {email}")
    public void fillForm(String name, String email, String password) {
        type(nameInput, name);
        type(emailInput, email);
        type(passwordInput, password);
    }

    @Step("Отправить форму регистрации")
    public void submit() {
        click(registerButton);
    }

    @Step("Зарегистрироваться пользователем {email}")
    public void register(String name, String email, String password) {
        fillForm(name, email, password);
        submit();
    }

    public boolean isPasswordErrorVisible() {
        return !driver.findElements(passwordError).isEmpty();
    }

    @Step("Перейти на страницу входа из формы регистрации")
    public void clickLoginLink() {
        click(loginLink);
    }
}
