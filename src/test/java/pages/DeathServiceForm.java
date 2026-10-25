package pages;

import io.qameta.allure.Step;
import model.ui.DeathApplicationDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeathServiceForm extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DeathServiceForm.class);

    @FindBy(xpath = "//label[contains(text(), 'Дата смерти')]/following::input[1]")
    private WebElement deathDateField;

    @FindBy(xpath = "//label[contains(text(), 'Место смерти')]/following::input[1]")
    private WebElement placeOfDeathField;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div/div[3]/button[3]")
    private WebElement submitButton;

    public DeathServiceForm(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем данные услуги 'Смерть'")
    public DeathServiceForm fill(DeathApplicationDTO data) {
        logger.info("Заполняем данные услуги 'Смерть': дата смерти={}, место смерти={}",
                data.getDeathDate(),
                data.getPlaceOfDeath());

        deathDateField.sendKeys(data.getDeathDate());
        placeOfDeathField.sendKeys(data.getPlaceOfDeath());

        return this;
    }

    @Step("Отправляем форму услуги 'Смерть'")
    public DeathServiceForm submit() {
        logger.info("Нажимаем кнопку отправки формы услуги 'Смерть'");
        submitButton.click();
        return this;
    }
}