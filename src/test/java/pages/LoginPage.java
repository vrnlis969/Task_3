package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    // ИЗМЕНЕНО: поле email имеет атрибут name="name"
    @FindBy(name = "name")
    private WebElement emailField;

    // ИЗМЕНЕНО: поле пароля имеет атрибут name="Пароль"
    @FindBy(name = "Пароль")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement loginButton;

    @FindBy(linkText = "Зарегистрироваться")
    private WebElement registerLink;

    @FindBy(linkText = "Восстановить пароль")
    private WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Нажать ссылку 'Зарегистрироваться'")
    public void clickRegisterLink() {
        registerLink.click();
    }

    @Step("Нажать ссылку 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
    }

    @Step("Выполнить вход: {email} / {password}")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    // ДОБАВЛЕНО: метод для получения кнопки "Войти" (для проверок в тестах)
    public WebElement getLoginButton() {
        return loginButton;
    }
}