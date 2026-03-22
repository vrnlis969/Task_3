package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    // Локаторы элементов
    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement loginButton; // Кнопка "Войти в аккаунт"

    @FindBy(xpath = "//p[text()='Личный Кабинет']")
    private WebElement personalAccountButton; // Кнопка "Личный кабинет"

    @FindBy(xpath = "//div[@class='AppHeader_header__logo__...']/a") // Уточните точный класс, или используйте другой локатор
    private WebElement logo; // Логотип Stellar Burgers

    // Разделы конструктора
    @FindBy(xpath = "//span[text()='Булки']")
    private WebElement bunsTab;

    @FindBy(xpath = "//span[text()='Соусы']")
    private WebElement saucesTab;

    @FindBy(xpath = "//span[text()='Начинки']")
    private WebElement fillingsTab;

    // Активный таб (для проверки)
    @FindBy(xpath = "//div[contains(@class, 'tab_tab_type_current')]")
    private WebElement activeTab;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }

    public void clickLogo() {
        logo.click();
    }

    public void clickBunsTab() {
        bunsTab.click();
    }

    public void clickSaucesTab() {
        saucesTab.click();
    }

    public void clickFillingsTab() {
        fillingsTab.click();
    }

    public String getActiveTabText() {
        return activeTab.getText();
    }
}