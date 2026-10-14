package factory;

import config.BrowserConfig;
import config.ConfigReader;
import org.openqa.selenium.PageLoadStrategy;
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
                chromeOpts.setPageLoadStrategy(PageLoadStrategy.EAGER);
                return new ChromeDriver(chromeOpts);

            case "firefox":
                FirefoxOptions firefoxOpts = new FirefoxOptions();
                firefoxOpts.setPageLoadStrategy(PageLoadStrategy.EAGER);
                return new FirefoxDriver(firefoxOpts);

            case "edge":
                EdgeOptions edgeOpts = new EdgeOptions();
                edgeOpts.setPageLoadStrategy(PageLoadStrategy.EAGER);
                return new EdgeDriver(edgeOpts);

            default:
                throw new IllegalArgumentException(
                        "Неизвестный браузер: " + browser + ". Ожидалось: chrome, firefox, edge");
        }
    }
}