package pages;

import io.qameta.allure.Step;
import model.BaseApplicationDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CitizenForm extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CitizenForm.class);

    private final By lastNameField   = By.xpath("//label[contains(text(), 'Фамилия')]/following::input[1]");
    private final By firstNameField  = By.xpath("//label[contains(text(), 'Имя')]/following::input[1]");
    private final By middleNameField = By.xpath("//label[contains(text(), 'Отчество')]/following::input[1]");
    private final By birthDateField  = By.xpath("//label[contains(text(), 'Дата рождения')]/following::input[1]");
    private final By passportField   = By.xpath("//label[contains(text(), 'Номер паспорта')]/following::input[1]");
    private final By genderField     = By.xpath("//label[contains(text(), 'Пол')]/following::input[1]");
    private final By addressField    = By.xpath("//label[contains(text(), 'Адрес прописки')]/following::input[1]");

    private final By nextButton =
            By.xpath("/html/body/div[2]/div[3]/div/div/div[3]/button[3]");

    public CitizenForm(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем данные гражданина: {data.citizenLastName} {data.citizenFirstName}")
    public CitizenForm fillCitizenData(BaseApplicationDTO data) {
        logger.info("Заполняем данные гражданина: {} {} {}",
                data.getCitizenLastName(),
                data.getCitizenFirstName(),
                data.getCitizenMiddleName());
        type(lastNameField,   data.getCitizenLastName());
        type(firstNameField,  data.getCitizenFirstName());
        type(middleNameField, data.getCitizenMiddleName());
        type(birthDateField,  data.getCitizenBirthDate());
        if (data.getCitizenPassport() != null) {
            type(passportField, data.getCitizenPassport());
        }
        type(genderField,     data.getCitizenGender());
        type(addressField,    data.getCitizenAddress());
        return this;
    }

    @Step("Нажимаем 'Далее' на форме гражданина")
    public CitizenForm clickNext() {
        logger.info("Нажимаем 'Далее' на форме гражданина");
        click(nextButton);
        return this;
    }
}