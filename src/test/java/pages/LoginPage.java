package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    private final By loginAsUserButton =
            By.xpath("//button[contains(text(), 'Войти как пользователь')]");

    private final By loginAsAdminButton =
            By.xpath("//button[contains(text(), 'Войти как администратор')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажимаем кнопку 'Войти как пользователь'")
    public LoginPage clickLoginAsUser() {
        logger.info("Нажимаем 'Войти как пользователь'");
        WebElement button = waitForClickable(loginAsUserButton);
        button.click();
        return this;
    }

    @Step("Нажимаем кнопку 'Войти как администратор'")
    public LoginPage clickLoginAsAdmin() {
        logger.info("Нажимаем 'Войти как администратор'");
        WebElement button = waitForClickable(loginAsAdminButton);
        button.click();
        return this;
    }
}