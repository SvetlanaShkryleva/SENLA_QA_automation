package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ServiceChoice extends BasePage {

    private static final String SERVICE_BUTTON_TEMPLATE =
            "//button[contains(text(), '%s')]";

    private final By nextButton =
            By.cssSelector("button:nth-child(2)");

    public ServiceChoice(WebDriver driver) {
        super(driver);
    }

    public ServiceChoice chooseService(String serviceName) {
        By serviceButton = By.xpath(String.format(SERVICE_BUTTON_TEMPLATE, serviceName));
        click(serviceButton);
        return this;
    }

    public ServiceChoice clickNext() {
        click(nextButton);
        return this;
    }
}