package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//label[text()='Имя']/following-sibling::input")
    private WebElement nameField;

    @FindBy(xpath = "//label[text()='Email']/following-sibling::input")
    private WebElement emailField;

    @FindBy(xpath = "//label[text()='Пароль']/following-sibling::input")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(xpath = "//p[text()='Некорректный пароль']")
    private WebElement errorMessage;

    // ДОБАВЛЕНО: ссылка "Войти" на странице регистрации
    @FindBy(linkText = "Войти")
    private WebElement loginLink;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(registerButton));
    }

    @Step("Ввести имя: {name}")
    public void enterName(String name) {
        nameField.sendKeys(name);
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Проверить наличие сообщения об ошибке пароля")
    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ДОБАВЛЕНО: метод для нажатия на ссылку "Войти"
    @Step("Нажать ссылку 'Войти' на странице регистрации")
    public void clickLoginLink() {
        loginLink.click();
    }

    @Step("Зарегистрировать пользователя: {name}, {email}")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }
}