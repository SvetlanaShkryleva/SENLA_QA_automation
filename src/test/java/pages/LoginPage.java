package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(xpath = "//button[contains(text(), 'Войти как пользователь')]")
    private WebElement loginAsUserButton;

    @FindBy(xpath = "//button[contains(text(), 'Войти как администратор')]")
    private WebElement loginAsAdminButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажимаем кнопку 'Войти как пользователь'")
    public LoginPage clickLoginAsUser() {
        logger.info("Нажимаем 'Войти как пользователь'");
        loginAsUserButton.click();
        return this;
    }

    @Step("Нажимаем кнопку 'Войти как администратор'")
    public LoginPage clickLoginAsAdmin() {
        logger.info("Нажимаем 'Войти как администратор'");
        loginAsAdminButton.click();
        return this;
    }
}