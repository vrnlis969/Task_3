package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    // ИСПРАВЛЕНО: текст кнопки "Выход" (было "Выйти")
    @FindBy(xpath = "//button[text()='Выход']")
    private WebElement logoutButton;

    @FindBy(xpath = "//p[text()='Конструктор']")
    private WebElement constructorLink;

    @FindBy(xpath = "//a[@href='/']")
    private WebElement logo;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
    }

    @Step("Нажать кнопку 'Выход'")
    public void clickLogoutButton() {
        logoutButton.click();
    }

    @Step("Нажать ссылку 'Конструктор'")
    public void clickConstructorLink() {
        constructorLink.click();
    }

    @Step("Нажать на логотип")
    public void clickLogo() {
        logo.click();
    }
}