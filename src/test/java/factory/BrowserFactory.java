package factory;

import config.BrowserConfig;
import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    public static WebDriver createDriver() {
        BrowserConfig config = ConfigReader.read();
        String browser = config.getBrowser().toLowerCase();

        System.out.println("Запускаем браузер: " + browser);

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOpts = new ChromeOptions();
                chromeOpts.addArguments("--disable-ipv6");
                return new ChromeDriver(chromeOpts);

            case "firefox":
                return new FirefoxDriver(new FirefoxOptions());

            case "edge":
                return new EdgeDriver(new EdgeOptions());

            default:
                throw new IllegalArgumentException(
                        "Неизвестный браузер: " + browser + ". Ожидалось: chrome, firefox, edge");
        }
    }
}