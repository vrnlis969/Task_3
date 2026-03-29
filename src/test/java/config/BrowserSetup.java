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
                // Получаем путь к Яндекс.Браузеру из системного свойства или переменной окружения
                String yandexPath = System.getProperty("yandex.browser.path");
                if (yandexPath == null) {
                    yandexPath = System.getenv("YANDEX_BROWSER_PATH");
                }
                if (yandexPath == null) {
                    throw new IllegalArgumentException("Путь к Яндекс.Браузеру не задан. Укажите через -Dyandex.browser.path или переменную окружения YANDEX_BROWSER_PATH");
                }
                WebDriverManager.chromedriver().browserVersion("144").setup();
                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.setBinary(yandexPath);
                yandexOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(yandexOptions);
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }
}