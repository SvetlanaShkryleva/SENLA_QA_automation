package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceChoicePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ServiceChoicePage.class);

    private static final String SERVICE_BUTTON_TEMPLATE =
            "//button[contains(text(), '%s')]";

    private final By nextButton =
            By.cssSelector("button:nth-child(2)");

    public ServiceChoicePage(WebDriver driver) {
        super(driver);
    }

    @Step("Выбираем услугу: {serviceName}")
    public ServiceChoicePage chooseService(String serviceName) {
        logger.info("Выбираем услугу: {}", serviceName);
        By serviceButton = By.xpath(String.format(SERVICE_BUTTON_TEMPLATE, serviceName));
        click(serviceButton);
        return this;
    }

    @Step("Нажимаем 'Далее' на странице выбора услуги")
    public ServiceChoicePage clickNext() {
        logger.info("Нажимаем 'Далее' на странице выбора услуги");
        click(nextButton);
        return this;
    }
}