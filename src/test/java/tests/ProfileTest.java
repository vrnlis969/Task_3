package tests;

import api.UserClient;
import api.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.LoginPage;
import pages.ProfilePage;
import utils.RandomDataGenerator;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@Story("Личный кабинет")
public class ProfileTest extends BaseTest {

    private UserClient userClient;
    private UserData user;
    private String accessToken;

    @BeforeEach
    public void createUser() {
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

    @Test
    @Description("Переход в личный кабинет по клику на 'Личный кабинет'")
    public void goToProfile() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoaded();
        loginPage.login(user.getEmail(), user.getPassword());

        mainPage.clickPersonalAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForPageLoaded();

        assertTrue(driver.getCurrentUrl().contains("/account/profile"), "Не перешли в личный кабинет");
    }

    @Test
    @Description("Переход из личного кабинета в конструктор по клику на 'Конструктор'")
    public void goToConstructorViaLink() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoaded();
        loginPage.login(user.getEmail(), user.getPassword());

        // Закрываем возможное модальное окно (метод из MainPage)
        mainPage.closeModalIfPresent();

        mainPage.clickPersonalAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForPageLoaded();
        profilePage.clickConstructorLink();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(mainPage.getPersonalAccountButton()));
        assertTrue(mainPage.isPersonalAccountButtonDisplayed(), "Кнопка 'Личный кабинет' не отображается");
    }

    @Test
    @Description("Переход из личного кабинета в конструктор по клику на логотип")
    public void goToConstructorViaLogo() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoaded();
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForPageLoaded();
        profilePage.clickLogo();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(mainPage.getPersonalAccountButton()));
        assertTrue(mainPage.isPersonalAccountButtonDisplayed(), "Кнопка 'Личный кабинет' не отображается");
    }

    @Test
    @Description("Выход из аккаунта по кнопке 'Выйти' в личном кабинете")
    public void logout() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoaded();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoaded();
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForPageLoaded();
        profilePage.clickLogoutButton();

        LoginPage loginPageAfterLogout = new LoginPage(driver);
        loginPageAfterLogout.waitForPageLoaded();
        assertTrue(loginPageAfterLogout.getLoginButton().isDisplayed(), "Кнопка 'Войти' не отображается");
    }
}