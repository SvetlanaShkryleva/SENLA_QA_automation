package pages;

import io.qameta.allure.Step;
import model.DeathApplicationDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeathServiceForm extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DeathServiceForm.class);

    private final By deathDateField =
            By.xpath("//label[contains(text(), 'Дата смерти')]/following::input[1]");
    private final By placeOfDeathField =
            By.xpath("//label[contains(text(), 'Место смерти')]/following::input[1]");

    private final By submitButton =
            By.xpath("/html/body/div[2]/div[3]/div/div/div[3]/button[3]");

    public DeathServiceForm(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем данные услуги 'Смерть': дата смерти {data.deathDate}, место смерти {data.placeOfDeath}")
    public DeathServiceForm fillDeathServiceData(DeathApplicationDTO data) {
        logger.info("Заполняем данные услуги 'Смерть': дата смерти={}, место смерти={}",
                data.getDeathDate(),
                data.getPlaceOfDeath());

        type(deathDateField,    data.getDeathDate());
        type(placeOfDeathField, data.getPlaceOfDeath());

        return this;
    }

    @Step("Отправляем форму услуги 'Смерть'")
    public DeathServiceForm submit() {
        logger.info("Нажимаем кнопку отправки формы услуги 'Смерть'");
        click(submitButton);
        return this;
    }
}