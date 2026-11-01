package tests;

import config.ConfigReader;
import config.Credentials;
import factory.BrowserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected WebDriver driver = BrowserFactory.getDriver();

    @BeforeEach
    public void openApp() {
        Credentials creds = ConfigReader.readCredentials();
        String url = "https://" + creds.getUsername() + ":" + creds.getPassword()
                + "@" + creds.getBaseUrl().replace("https://", "");
        logger.info("Открываем приложение: {}", creds.getBaseUrl());
        driver.get(url);
    }

    @AfterEach
    public void tearDown() {
        BrowserFactory.quitDriver();
    }
}