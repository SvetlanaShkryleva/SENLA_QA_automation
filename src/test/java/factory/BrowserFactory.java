
package factory;

import config.BrowserConfig;
import config.ConfigReader;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

    private static final Logger logger =
            LoggerFactory.getLogger(BrowserFactory.class);

    private static final ThreadLocal<WebDriver> driverThreadLocal =
            new ThreadLocal<>();
    private static final ThreadLocal<String> videoNameThreadLocal =
            new ThreadLocal<>();

    private BrowserFactory() {}

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            WebDriver driver = createDriver();

            driver.manage().window().maximize();
            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(10));

            driverThreadLocal.set(driver);
        }

        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();

        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            driverThreadLocal.remove();
            videoNameThreadLocal.remove();
        }
    }

    private static WebDriver createDriver() {
        BrowserConfig config = ConfigReader.read();
        String browser = config.getBrowser().toLowerCase();

        boolean headless = Boolean.parseBoolean(
                System.getProperty(
                        "headless",
                        System.getenv().getOrDefault("HEADLESS", "false")
                )
        );

        String selenoidUrl = System.getProperty(
                "selenoid.url",
                System.getenv().getOrDefault("SELENOID_URL", "")
        );

        boolean useRemote = selenoidUrl != null
                && !selenoidUrl.isEmpty();

        logger.info(
                "Запускаем браузер: {} (headless={}, remote={}, url={})",
                browser,
                headless,
                useRemote,
                useRemote ? selenoidUrl : "local"
        );

        switch (browser) {
            case "chrome": {
                ChromeOptions options =
                        buildChromeOptions(headless, useRemote);

                return useRemote
                        ? createRemote(selenoidUrl, options)
                        : new ChromeDriver(options);
            }

            case "firefox": {
                FirefoxOptions options =
                        buildFirefoxOptions(headless, useRemote);

                return useRemote
                        ? createRemote(selenoidUrl, options)
                        : new FirefoxDriver(options);
            }

            case "edge": {
                EdgeOptions options =
                        buildEdgeOptions(headless, useRemote);

                return useRemote
                        ? createRemote(selenoidUrl, options)
                        : new EdgeDriver(options);
            }

            default:
                throw new IllegalArgumentException(
                        "Неизвестный браузер: " + browser
                                + ". Ожидалось: chrome, firefox, edge"
                );
        }
    }

    private static ChromeOptions buildChromeOptions(
            boolean headless, boolean remote) {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-ipv6");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        if (headless && !remote) {
            options.addArguments(
                    "--headless=new",
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--disable-gpu",
                    "--window-size=1920,1080"
            );
        }

        if (remote) {
            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("enableVideo", true);
            selenoidOptions.put("enableLog", true);
            selenoidOptions.put("screenResolution", "1920x1080x24");
            String videoName = videoNameThreadLocal.get();

            if (videoName != null) {
                selenoidOptions.put("videoName", videoName);
            }

            options.setCapability("selenoid:options", selenoidOptions);
        }

        return options;
    }

    private static FirefoxOptions buildFirefoxOptions(
            boolean headless, boolean remote) {

        FirefoxOptions options = new FirefoxOptions();

        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        if (headless && !remote) {
            options.addArguments("-headless");
        }

        if (remote) {
            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("enableVideo", true);
            selenoidOptions.put("screenResolution", "1920x1080x24");

            options.setCapability("selenoid:options", selenoidOptions);
        }

        return options;
    }

    private static EdgeOptions buildEdgeOptions(
            boolean headless, boolean remote) {

        EdgeOptions options = new EdgeOptions();

        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        if (headless && !remote) {
            options.addArguments("--headless=new");
        }

        if (remote) {
            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("enableVideo", true);
            selenoidOptions.put("screenResolution", "1920x1080x24");

            options.setCapability("selenoid:options", selenoidOptions);
        }

        return options;
    }

    public static void setVideoName(String testName) {
        String safeName = testName.replaceAll("[^a-zA-Z0-9_-]", "_");
        videoNameThreadLocal.set(safeName + ".mp4");
    }

    private static WebDriver createRemote(
            String url, Capabilities capabilities) {

        try {
            logger.info("Подключаемся к Selenoid: {}", url);

            return new RemoteWebDriver(
                    new URL(url),
                    capabilities
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Не удалось подключиться к Selenoid: " + url,
                    e
            );
        }
    }
}
