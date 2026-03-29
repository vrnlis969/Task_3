package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[text()='Личный Кабинет']")
    private WebElement personalAccountButton;

    @FindBy(xpath = "//a[@href='/']")
    private WebElement logo;

    @FindBy(xpath = "//span[text()='Булки']")
    private WebElement bunsTab;

    @FindBy(xpath = "//span[text()='Соусы']")
    private WebElement saucesTab;

    @FindBy(xpath = "//span[text()='Начинки']")
    private WebElement fillingsTab;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab_type_current')]")
    private WebElement activeTab;

    // ДОБАВЛЕНО: локатор модального окна (overlay)
    @FindBy(xpath = "//div[contains(@class, 'Modal_modal_overlay')]")
    private WebElement modalOverlay;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Нажать кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }

    @Step("Нажать на логотип Stellar Burgers")
    public void clickLogo() {
        logo.click();
    }

    @Step("Перейти в раздел 'Булки'")
    public void clickBunsTab() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bunsTab);
    }

    @Step("Перейти в раздел 'Соусы'")
    public void clickSaucesTab() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saucesTab);
    }

    @Step("Перейти в раздел 'Начинки'")
    public void clickFillingsTab() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fillingsTab);
    }

    @Step("Получить текст активного таба")
    public String getActiveTabText() {
        return activeTab.getText();
    }

    public boolean isPersonalAccountButtonDisplayed() {
        try {
            return personalAccountButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getPersonalAccountButton() {
        return personalAccountButton;
    }

    // ДОБАВЛЕНО: метод для закрытия модального окна, если оно есть
    @Step("Закрыть модальное окно, если оно присутствует")
    public void closeModalIfPresent() {
        try {
            if (modalOverlay.isDisplayed()) {
                modalOverlay.click();
            }
        } catch (Exception e) {
            // Модальное окно не найдено или не отображается – ничего не делаем
        }
    }
}