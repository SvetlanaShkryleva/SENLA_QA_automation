package tests;

import config.ConfigReader;
import config.Credentials;
import factory.BrowserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected WebDriver driver = BrowserFactory.getDriver();

    @BeforeEach
    public void openApp() {
        Credentials creds = ConfigReader.readCredentials();

        String selenoidUrl = System.getProperty("selenoid.url",
                System.getenv().getOrDefault("SELENOID_URL", ""));
        boolean isRemote = selenoidUrl != null && !selenoidUrl.isEmpty();

        String url;
        if (isRemote) {
            url = "https://" + creds.getUsername() + ":" + creds.getPassword() + "@"
                    + creds.getBaseUrl().replaceFirst("^https?://", "");
            logger.info("Открываем приложение (remote, creds in URL): {}", creds.getBaseUrl());
        } else {
            if (driver instanceof HasAuthentication hasAuth) {
                hasAuth.register(UsernameAndPassword.of(creds.getUsername(), creds.getPassword()));
            }
            url = creds.getBaseUrl();
            logger.info("Открываем приложение (local, HasAuthentication): {}", creds.getBaseUrl());
        }

        driver.get(url);
    }

    @AfterEach
    public void tearDown() {
        BrowserFactory.quitDriver();
    }
}