package tests;

import config.BrowserSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = BrowserSetup.getDriver(browser);
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}