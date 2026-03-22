package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserSetup {

    public static WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(options);
            case "yandex":
                // Указываем версию браузера (144), чтобы WebDriverManager скачал совместимый драйвер
                WebDriverManager.chromedriver().browserVersion("144").setup();
                String yandexPath = "C:\\Users\\vrnli\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.setBinary(yandexPath);
                yandexOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(yandexOptions);
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }
}