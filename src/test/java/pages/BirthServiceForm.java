package pages;

import io.qameta.allure.Step;
import model.BirthApplicationDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BirthServiceForm extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BirthServiceForm.class);

    private final By placeOfBirthField =
            By.xpath("//label[contains(text(), 'Место рождения')]/following::input[1]");
    private final By motherField =
            By.xpath("//label[contains(text(), 'Мать')]/following::input[1]");
    private final By fatherField =
            By.xpath("//label[contains(text(), 'Отец')]/following::input[1]");
    private final By grandmotherField =
            By.xpath("//label[contains(text(), 'Бабушка')]/following::input[1]");
    private final By grandfatherField =
            By.xpath("//label[contains(text(), 'Дедушка')]/following::input[1]");

    private final By submitButton =
            By.xpath("/html/body/div[2]/div[3]/div/div/div[3]/button[3]");

    public BirthServiceForm(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем данные услуги 'Рождение': место рождения {data.placeOfBirth}")
    public BirthServiceForm fillBirthServiceData(BirthApplicationDTO data) {
        logger.info("Заполняем данные услуги 'Рождение': место рождения={}, мать={}, отец={}, бабушка={}, дедушка={}",
                data.getPlaceOfBirth(),
                data.getMother(),
                data.getFather(),
                data.getGrandmother(),
                data.getGrandfather());

        type(placeOfBirthField, data.getPlaceOfBirth());
        type(motherField,       data.getMother());
        type(fatherField,       data.getFather());
        type(grandmotherField,  data.getGrandmother());
        type(grandfatherField,  data.getGrandfather());

        return this;
    }

    @Step("Отправляем форму услуги 'Рождение'")
    public BirthServiceForm submit() {
        logger.info("Нажимаем кнопку отправки формы услуги 'Рождение'");
        click(submitButton);
        return this;
    }
}