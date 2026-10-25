package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceChoicePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ServiceChoicePage.class);

    private static final String SERVICE_BUTTON_TEMPLATE =
            "//button[contains(text(), '%s')]";

    @FindBy(css = "button:nth-child(2)")
    private WebElement nextButton;

    public ServiceChoicePage(WebDriver driver) {
        super(driver);
    }

    @Step("Выбираем услугу: {serviceName}")
    public ServiceChoicePage chooseService(String serviceName) {
        logger.info("Выбираем услугу: {}", serviceName);
        // Селектор динамический — @FindBy не подойдёт
        By serviceButton = By.xpath(String.format(SERVICE_BUTTON_TEMPLATE, serviceName));
        driver.findElement(serviceButton).click();
        return this;
    }

    @Step("Нажимаем 'Далее' на странице выбора услуги")
    public ServiceChoicePage clickNext() {
        logger.info("Нажимаем 'Далее' на странице выбора услуги");
        nextButton.click();
        return this;
    }
}