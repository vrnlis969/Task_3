package tests;

import api.UserClient;
import api.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import utils.RandomDataGenerator;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@Story("Вход в аккаунт")
public class LoginTest extends BaseTest {

    private UserClient userClient;
    private UserData user;
    private String accessToken;

    @BeforeEach
    public void setUp() {
        userClient = new UserClient();
        user = UserData.builder()
                .email(RandomDataGenerator.generateEmail())
                .password(RandomDataGenerator.generatePassword(6))
                .name(RandomDataGenerator.generateName())
                .build();
        var response = userClient.register(user);
        assertEquals(200, response.statusCode());
        accessToken = response.jsonPath().getString("accessToken");
    }

    @AfterEach
    public void cleanUp() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("Вход через кнопку 'Войти в аккаунт' на главной")
    public void loginViaMainPageButton(String browser) {
        initDriver(browser);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoaded();
        loginPage.login(user.getEmail(), user.getPassword());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(mainPage.getPersonalAccountButton()));
        assertTrue(mainPage.isPersonalAccountButtonDisplayed(), "Кнопка 'Личный кабинет' не отображается");
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("Вход через кнопку 'Личный кабинет'")
    public void loginViaPersonalAccountButton(String browser) {
        initDriver(browser);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoaded();
        loginPage.login(user.getEmail(), user.getPassword());

        // ИЗМЕНЕНО: после входа проверяем, что на главной появилась кнопка "Личный кабинет"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(mainPage.getPersonalAccountButton()));
        assertTrue(mainPage.isPersonalAccountButtonDisplayed(), "Кнопка 'Личный кабинет' не отображается");
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("Вход через кнопку в форме регистрации")
    public void loginViaRegisterPage(String browser) {
        initDriver(browser);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoaded();
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForPageLoaded();
        registerPage.clickLoginLink();

        loginPage.waitForPageLoaded();
        loginPage.login(user.getEmail(), user.getPassword());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(mainPage.getPersonalAccountButton()));
        assertTrue(mainPage.isPersonalAccountButtonDisplayed(), "Кнопка 'Личный кабинет' не отображается");
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("Вход через кнопку в форме восстановления пароля")
    public void loginViaForgotPasswordPage(String browser) {
        initDriver(browser);
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoaded();
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.waitForPageLoaded();
        forgotPage.clickLoginLink();

        loginPage.waitForPageLoaded();
        loginPage.login(user.getEmail(), user.getPassword());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(mainPage.getPersonalAccountButton()));
        assertTrue(mainPage.isPersonalAccountButtonDisplayed(), "Кнопка 'Личный кабинет' не отображается");
    }
}