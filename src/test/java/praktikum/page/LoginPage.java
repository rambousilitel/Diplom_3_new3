package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath("//button[text()='Войти']");

    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    private final By headerLogin = By.xpath("//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Авторизоваться пользователем {email}")
    public void login(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        click(loginButton);
    }

    @Step("Перейти на страницу регистрации из формы входа")
    public void goToRegister() {
        click(registerLink);
    }

    @Step("Перейти на страницу восстановления пароля")
    public void goToForgotPassword() {
        click(forgotPasswordLink);
    }

    public boolean isLoginHeaderVisible() {
        return find(headerLogin).isDisplayed();
    }
}
