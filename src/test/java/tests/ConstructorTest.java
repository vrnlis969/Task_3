package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Story("Конструктор бургеров")
public class ConstructorTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("Переход к разделу 'Булки'")
    public void switchToBunsTab(String browser) {
        initDriver(browser);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickBunsTab();  // теперь клик через JavaScript
        assertEquals("Булки", mainPage.getActiveTabText(), "Активный таб не 'Булки'");
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("Переход к разделу 'Соусы'")
    public void switchToSaucesTab(String browser) {
        initDriver(browser);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickSaucesTab();
        assertEquals("Соусы", mainPage.getActiveTabText(), "Активный таб не 'Соусы'");
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("Переход к разделу 'Начинки'")
    public void switchToFillingsTab(String browser) {
        initDriver(browser);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickFillingsTab();
        assertEquals("Начинки", mainPage.getActiveTabText(), "Активный таб не 'Начинки'");
    }
}