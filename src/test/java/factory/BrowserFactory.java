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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserFactory {

    private static final Logger logger = LoggerFactory.getLogger(BrowserFactory.class);
    private static WebDriver driver;

    private BrowserFactory() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static WebDriver createDriver() {
        BrowserConfig config = ConfigReader.read();
        String browser = config.getBrowser().toLowerCase();

        // headless включается через:
        //   - переменную окружения HEADLESS=true  (используется в CI)
        //   - системное свойство -Dheadless=true  (для локальных запусков)
        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless",
                        System.getenv().getOrDefault("HEADLESS", "false"))
        );

        logger.info("Запускаем браузер: {} (headless={})", browser, headless);

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOpts = new ChromeOptions();
                chromeOpts.addArguments("--disable-ipv6");
                chromeOpts.setPageLoadStrategy(PageLoadStrategy.EAGER);
                if (headless) {
                    chromeOpts.addArguments(
                            "--headless=new",
                            "--no-sandbox",
                            "--disable-dev-shm-usage",
                            "--disable-gpu",
                            "--window-size=1920,1080"
                    );
                }
                return new ChromeDriver(chromeOpts);

            case "firefox":
                FirefoxOptions firefoxOpts = new FirefoxOptions();
                firefoxOpts.setPageLoadStrategy(PageLoadStrategy.EAGER);
                if (headless) {
                    firefoxOpts.addArguments("-headless");
                }
                return new FirefoxDriver(firefoxOpts);

            case "edge":
                EdgeOptions edgeOpts = new EdgeOptions();
                edgeOpts.setPageLoadStrategy(PageLoadStrategy.EAGER);
                if (headless) {
                    edgeOpts.addArguments("--headless=new");
                }
                return new EdgeDriver(edgeOpts);

            default:
                throw new IllegalArgumentException(
                        "Неизвестный браузер: " + browser + ". Ожидалось: chrome, firefox, edge");
        }
    }
}