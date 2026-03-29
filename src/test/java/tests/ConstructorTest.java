package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Story("Конструктор бургеров")
public class ConstructorTest extends BaseTest {

    @Test
    @Description("Переход к разделу 'Булки'")
    public void switchToBunsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickBunsTab();
        assertEquals("Булки", mainPage.getActiveTabText(), "Активный таб не 'Булки'");
    }

    @Test
    @Description("Переход к разделу 'Соусы'")
    public void switchToSaucesTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickSaucesTab();
        assertEquals("Соусы", mainPage.getActiveTabText(), "Активный таб не 'Соусы'");
    }

    @Test
    @Description("Переход к разделу 'Начинки'")
    public void switchToFillingsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickFillingsTab();
        assertEquals("Начинки", mainPage.getActiveTabText(), "Активный таб не 'Начинки'");
    }
}