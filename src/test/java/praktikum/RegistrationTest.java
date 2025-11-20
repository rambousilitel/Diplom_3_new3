package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.page.LoginPage;
import praktikum.page.MainPage;
import praktikum.page.RegisterPage;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
public class RegistrationTest extends BaseUiTest {

    private final UserApiClient userApiClient = new UserApiClient();
    private String accessToken;

    @Parameterized.Parameters(name = "browser = {0}")
    public static Object[] browsers() {
        return new Object[]{Browser.CHROME, Browser.YANDEX};
    }

    public RegistrationTest(Browser browser) {
        super(browser);
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            userApiClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Пользователь с валидными данными может зарегистрироваться")
    public void successfulRegistration() {
        WebDriver d = driver;
        MainPage main = new MainPage(d);
        main.clickLoginAccount();           // "Войти в аккаунт"

        LoginPage login = new LoginPage(d);
        login.goToRegister();               // "Зарегистрироваться"

        RegisterPage register = new RegisterPage(d);
        String email = "user" + System.currentTimeMillis() + "@example.com";
        String password = "password123";
        register.register("Vasya", email, password);

        // после успешной регистрации нас перекидывает на страницу входа
        assertTrue("Ожидалась страница входа после регистрации",
                new LoginPage(d).isLoginHeaderVisible());

        // через API залогинимся, чтобы получить токен для удаления пользователя
        UserApi user = new UserApi(email, password, "Vasya");
        accessToken = userApiClient.createUser(user)
                .extract().path("accessToken");
    }

    @Test
    @DisplayName("Ошибка при некорректном пароле")
    @Description("Некорректный пароль (меньше 6 символов) должен давать ошибку")
    public void errorOnInvalidPassword() {
        WebDriver d = driver;
        MainPage main = new MainPage(d);
        main.clickLoginAccount();

        LoginPage login = new LoginPage(d);
        login.goToRegister();

        RegisterPage register = new RegisterPage(d);
        String email = "user" + System.currentTimeMillis() + "@example.com";
        String shortPassword = "12345"; // меньше 6

        register.register("Vasya", email, shortPassword);

        assertTrue("Ожидалось сообщение об ошибке 'Некорректный пароль'",
                register.isPasswordErrorVisible());

        // пользователя не создаём => accessToken не будет
    }
}
