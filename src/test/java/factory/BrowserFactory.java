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

/**
 * Фабрика драйвера + Singleton.
 * Гарантирует, что во время прогона тестов существует ровно один экземпляр WebDriver.
 */
public class BrowserFactory {

    private static WebDriver driver;

    private BrowserFactory() {}

    /**
     * Возвращает единственный экземпляр WebDriver.
     * Создаёт его при первом обращении, при последующих — возвращает существующий.
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * Закрывает браузер и обнуляет ссылку, чтобы следующий тест создал новый драйвер.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Создаёт новый экземпляр WebDriver по настройкам из browser-config.json.
     * Не сохраняет его в статическое поле — этим занимается getDriver().
     */
    private static WebDriver createDriver() {
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