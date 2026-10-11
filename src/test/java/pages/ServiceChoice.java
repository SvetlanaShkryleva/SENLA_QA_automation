package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceChoice extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ServiceChoice.class);

    private static final String SERVICE_BUTTON_TEMPLATE =
            "//button[contains(text(), '%s')]";

    private final By nextButton =
            By.cssSelector("button:nth-child(2)");

    public ServiceChoice(WebDriver driver) {
        super(driver);
    }

    @Step("Выбираем услугу: {serviceName}")
    public ServiceChoice chooseService(String serviceName) {
        logger.info("Выбираем услугу: {}", serviceName);
        By serviceButton = By.xpath(String.format(SERVICE_BUTTON_TEMPLATE, serviceName));
        click(serviceButton);
        return this;
    }

    @Step("Нажимаем 'Далее' на странице выбора услуги")
    public ServiceChoice clickNext() {
        logger.info("Нажимаем 'Далее' на странице выбора услуги");
        click(nextButton);
        return this;
    }
}