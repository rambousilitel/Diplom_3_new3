package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.page.ForgotPasswordPage;
import praktikum.page.LoginPage;
import praktikum.page.MainPage;
import praktikum.page.RegisterPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginTest extends BaseUiTest {

    private final UserApiClient userApiClient = new UserApiClient();
    private String email;
    private final String password = "password123";
    private String accessToken;

    @Parameterized.Parameters(name = "browser = {0}")
    public static Object[] browsers() {
        return new Object[]{Browser.CHROME, Browser.YANDEX};
    }

    public LoginTest(Browser browser) {
        super(browser);
    }

    @Before
    public void createUserViaApi() {
        email = "user" + System.currentTimeMillis() + "@example.com";
        UserApi user = new UserApi(email, password, "Vasya");
        accessToken = userApiClient.createUser(user)
                .extract().path("accessToken");
    }

    @After
    public void deleteUserViaApi() {
        if (accessToken != null) {
            userApiClient.deleteUser(accessToken);
        }
    }

    private void assertLoggedIn(WebDriver d) {
        MainPage main = new MainPage(d);
        assertTrue("Ожидалась главная страница конструктора после логина",
                main.isConstructorOpened());
    }


    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    @Description("Авторизация через кнопку 'Войти в аккаунт' на главной странице")
    public void loginFromMainPageButton() {
        WebDriver d = driver;
        MainPage main = new MainPage(d);
        main.clickLoginAccount();

        LoginPage login = new LoginPage(d);
        login.login(email, password);

        assertLoggedIn(driver);
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void loginFromPersonalAccountButton() {
        WebDriver d = driver;
        MainPage main = new MainPage(d);
        main.clickPersonalAccount();

        LoginPage login = new LoginPage(d);
        login.login(email, password);

        assertLoggedIn(driver);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginFromRegistrationForm() {
        WebDriver d = driver;
        MainPage main = new MainPage(d);
        main.clickLoginAccount();

        LoginPage login = new LoginPage(d);
        login.goToRegister();

        RegisterPage register = new RegisterPage(d);
        register.clickLoginLink();

        login.login(email, password);

        assertLoggedIn(driver);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginFromForgotPasswordForm() {
        WebDriver d = driver;
        MainPage main = new MainPage(d);
        main.clickLoginAccount();

        LoginPage login = new LoginPage(d);
        login.goToForgotPassword();

        ForgotPasswordPage forgot = new ForgotPasswordPage(d);
        forgot.goToLogin();

        login.login(email, password);

        assertLoggedIn(driver);
    }

    @Test
    @DisplayName("Логин с неверным логином и паролем")
    @Description("При вводе неверных учетных данных вход в систему невозможен")
    public void loginWithWrongCredentials() {
        WebDriver d = driver;

        // 1. Открываем главную и жмём "Войти в аккаунт"
        MainPage main = new MainPage(d);
        main.clickLoginAccount();

        // 2. Переходим на форму логина
        LoginPage login = new LoginPage(d);

        // 3. Вводим заведомо неверные данные
        String wrongEmail = "wrong" + System.currentTimeMillis() + "@example.com";
        String wrongPassword = "wrongPass";
        login.login(wrongEmail, wrongPassword);

        // 4. Проверяем, что мы всё ещё на странице входа

        assertTrue("Ожидалась страница входа при неверных учетных данных",
                login.isLoginHeaderVisible());
    }


}


