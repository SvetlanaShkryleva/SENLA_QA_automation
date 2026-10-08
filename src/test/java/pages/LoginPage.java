package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private final By loginAsUserButton =
            By.xpath("//button[contains(text(), 'Войти как пользователь')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLoginAsUser() {
        // Ждём, пока кнопка станет видимой и кликабельной
        WebElement button = waitForClickable(loginAsUserButton);
        button.click();
        return this;
    }
}