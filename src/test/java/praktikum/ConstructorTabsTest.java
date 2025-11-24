package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.page.MainPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ConstructorTabsTest extends BaseUiTest {

    @Parameterized.Parameters(name = "browser = {0}")
    public static Object[] browsers() {
        return new Object[]{Browser.CHROME, Browser.YANDEX};
    }

    public ConstructorTabsTest(Browser browser) {
        super(browser);
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Клик по вкладке 'Соусы' открывает раздел 'Соусы'")
    public void goToSaucesSection() {
        MainPage main = new MainPage(driver);
        main.openSaucesSection();
        assertTrue(main.isSaucesSectionVisible());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void goToFillingsSection() {
        MainPage main = new MainPage(driver);
        main.openFillingsSection();
        assertTrue(main.isFillingsSectionVisible());
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void goToBunsSection() {
        MainPage main = new MainPage(driver);
        main.openBunsSection();
        assertTrue(main.isBunsSectionVisible());
    }
}
