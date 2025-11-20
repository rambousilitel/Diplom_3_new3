package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.page.LoginPage;
import praktikum.page.MainPage;
import praktikum.page.ProfilePage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ProfileTest extends BaseUiTest {

    private final UserApiClient userApiClient = new UserApiClient();
    private String email;
    private final String password = "password123";
    private String accessToken;

    @Parameterized.Parameters(name = "browser = {0}")
    public static Object[] browsers() {
        return new Object[]{Browser.CHROME, Browser.YANDEX};
    }

    public ProfileTest(Browser browser) {
        super(browser);
    }

    @Before
    public void createAndLoginUser() {
        WebDriver d = driver;

        email = "user" + System.currentTimeMillis() + "@example.com";
        UserApi user = new UserApi(email, password, "Vasya");
        accessToken = userApiClient.createUser(user)
                .extract().path("accessToken");

        MainPage main = new MainPage(d);
        main.clickLoginAccount();

        LoginPage login = new LoginPage(d);
        login.login(email, password);
    }

    @After
    public void deleteUserViaApi() {
        if (accessToken != null) {
            userApiClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на 'Личный кабинет'")
    @Description("Авторизованный пользователь может открыть личный кабинет")
    public void openProfileFromHeader() {
        WebDriver d = driver;
        MainPage main = new MainPage(d);
        main.clickPersonalAccount();

        ProfilePage profile = new ProfilePage(d);
        assertTrue("Ожидалась страница профиля", profile.isProfilePageOpened());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по кнопке 'Конструктор'")
    public void goFromProfileToConstructorByButton() {
        WebDriver d = driver;
        MainPage main = new MainPage(d);
        main.clickPersonalAccount();

        ProfilePage profile = new ProfilePage(d);
        profile.clickConstructor();

        assertTrue("Ожидалась главная страница конструктора",
                d.getCurrentUrl().endsWith("/"));
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по логотипу")
    public void goFromProfileToConstructorByLogo() {
        WebDriver d = driver;
        MainPage main = new MainPage(d);
        main.clickPersonalAccount();

        ProfilePage profile = new ProfilePage(d);
        profile.clickLogo();

        assertTrue(d.getCurrentUrl().endsWith("/"));
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке 'Выйти'")
    public void logoutFromProfile() {
        WebDriver d = driver;
        MainPage main = new MainPage(d);
        main.clickPersonalAccount();

        ProfilePage profile = new ProfilePage(d);
        profile.logout();

        assertTrue("Ожидалась страница входа после выхода",
                new LoginPage(d).isLoginHeaderVisible());
    }
}
