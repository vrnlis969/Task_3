package tests;

import config.BrowserSetup;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ИЗМЕНЕНО: добавлен вызов driver.get(BASE_URL)
    protected void initDriver(String browser) {
        driver = BrowserSetup.getDriver(browser);
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }
}