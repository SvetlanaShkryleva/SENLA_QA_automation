package tests;

import config.ConfigReader;
import config.Credentials;
import factory.DriverSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverSingleton.getDriver();
        openAppWithAuth();
    }

    @AfterEach
    public void tearDown() {
        DriverSingleton.quitDriver();
    }

    protected void openAppWithAuth() {
        Credentials creds = ConfigReader.readCredentials();
        String url = "https://" + creds.getUsername() + ":" + creds.getPassword()
                + "@" + creds.getBaseUrl().replace("https://", "");
        driver.get(url);
    }
}