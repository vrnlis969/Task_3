package tests;

import api.UserClient;
import api.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.RegisterPage;
import pages.LoginPage;
import utils.RandomDataGenerator;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@Story("Регистрация пользователя")
public class RegistrationTest extends BaseTest {

    private UserClient userClient;
    private UserData user;
    private String accessToken;

    @AfterEach
    public void cleanUp() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("Успешная регистрация нового пользователя")
    public void successfulRegistration(String browser) {
        initDriver(browser);
        user = UserData.builder()
                .email(RandomDataGenerator.generateEmail())
                .password(RandomDataGenerator.generatePassword(6))
                .name(RandomDataGenerator.generateName())
                .build();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoaded();
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForPageLoaded();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        // ИЗМЕНЕНО: проверяем, что перешли на страницу входа (кнопка "Войти" видна)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginButton()));
        assertTrue(loginPage.getLoginButton().isDisplayed(), "Кнопка 'Войти' не отображается");

        userClient = new UserClient();
        var response = userClient.login(user);
        assertEquals(200, response.statusCode());
        accessToken = response.jsonPath().getString("accessToken");
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    @Description("Ошибка при регистрации с паролем менее 6 символов")
    public void registrationWithShortPassword(String browser) {
        initDriver(browser);
        user = UserData.builder()
                .email(RandomDataGenerator.generateEmail())
                .password(RandomDataGenerator.generatePassword(5))
                .name(RandomDataGenerator.generateName())
                .build();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoaded();
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForPageLoaded();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue(registerPage.isErrorMessageDisplayed(), "Сообщение об ошибке не отобразилось");
        // ИЗМЕНЕНО: проверяем, что URL остался на странице регистрации (можно оставить)
        assertTrue(driver.getCurrentUrl().contains("/register"), "Произошел переход, хотя ожидалась ошибка");
    }
}