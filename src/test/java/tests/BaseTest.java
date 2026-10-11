package tests;

import config.ConfigReader;
import config.Credentials;
import factory.DriverSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        logger.info("=== Установка драйвера ===");
        driver = DriverSingleton.getDriver();
        openApp();
        logger.info("Драйвер готов, приложение открыто");
    }

    @AfterEach
    public void tearDown() {
        logger.info("=== Закрытие драйвера ===");
        DriverSingleton.quitDriver();
        logger.info("Тест завершён");
    }

    /**
     * Открывает приложение с Basic Auth.
     */
    protected void openApp() {
        Credentials creds = ConfigReader.readCredentials();
        String url = "https://" + creds.getUsername() + ":" + creds.getPassword()
                + "@" + creds.getBaseUrl().replace("https://", "");
        logger.info("Открываем приложение: {}", creds.getBaseUrl());
        driver.get(url);
    }
}