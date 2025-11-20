package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;

public abstract class BasePage {

    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по элементу: {locator}")
    protected void click(By locator) {
        WebElement element = driver.findElement(locator);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    @Step("Ввести текст '{text}' в элемент: {locator}")
    protected void type(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }
}
