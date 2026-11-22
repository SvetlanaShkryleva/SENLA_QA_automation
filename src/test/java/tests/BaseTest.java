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

        // Регистрируем Basic Auth без передачи кредов в URL
        if (driver instanceof HasAuthentication hasAuth) {
            hasAuth.register(UsernameAndPassword.of(creds.getUsername(), creds.getPassword()));
        }

        logger.info("Открываем приложение: {}", creds.getBaseUrl());
        driver.get(creds.getBaseUrl());
    }

    @AfterEach
    public void tearDown() {
        BrowserFactory.quitDriver();
    }
}