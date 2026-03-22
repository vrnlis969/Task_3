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

    // ИЗМЕНЕНО: более надёжный локатор логотипа
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

    // ИЗМЕНЕНО: клик через JavaScript, чтобы обойти перекрытие другими элементами
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

    // ДОБАВЛЕНО: метод для проверки видимости кнопки "Личный кабинет" (используется в тестах)
    public boolean isPersonalAccountButtonDisplayed() {
        try {
            return personalAccountButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ДОБАВЛЕНО: метод для получения кнопки "Войти" (если нужна внешняя проверка)
    public WebElement getLoginButton() {
        return loginButton;
    }
    public WebElement getPersonalAccountButton() {
        return personalAccountButton;
    }

}