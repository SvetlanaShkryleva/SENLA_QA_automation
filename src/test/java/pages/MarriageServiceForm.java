package pages;

import io.qameta.allure.Step;
import model.MarriageApplicationDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarriageServiceForm extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(MarriageServiceForm.class);

    private final By registrationDateField =
            By.xpath("//label[contains(text(), 'Дата регистрации')]/following::input[1]");
    private final By newLastNameField =
            By.xpath("//label[contains(text(), 'Новая фамилия')]/following::input[1]");

    private final By spouseLastNameField =
            By.xpath("//label[contains(text(), 'Фамилия супруга')]/following::input[1]");
    private final By spouseFirstNameField =
            By.xpath("//label[contains(text(), 'Имя супруга')]/following::input[1]");
    private final By spouseMiddleNameField =
            By.xpath("//label[contains(text(), 'Отчество супруга')]/following::input[1]");
    private final By spouseBirthDateField =
            By.xpath("//label[contains(text(), 'Дата рождения супруга')]/following::input[1]");
    private final By spousePassportField =
            By.xpath("//label[contains(text(), 'Номер паспорта супруга')]/following::input[1]");

    private final By submitButton =
            By.xpath("/html/body/div[2]/div[3]/div/div/div[3]/button[3]");

    public MarriageServiceForm(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем данные услуги: дата регистрации {data.registrationDate}, новая фамилия {data.newLastName}")
    public MarriageServiceForm fillMarriageServiceData(MarriageApplicationDTO data) {
        logger.info("Заполняем данные услуги: дата регистрации={}, новая фамилия={}, супруг(а): {} {} {}",
                data.getRegistrationDate(),
                data.getNewLastName(),
                data.getSpouseLastName(),
                data.getSpouseFirstName(),
                data.getSpouseMiddleName());

        type(registrationDateField,   data.getRegistrationDate());
        type(newLastNameField,        data.getNewLastName());
        type(spouseLastNameField,     data.getSpouseLastName());
        type(spouseFirstNameField,    data.getSpouseFirstName());
        type(spouseMiddleNameField,   data.getSpouseMiddleName());
        type(spouseBirthDateField,    data.getSpouseBirthDate());
        type(spousePassportField,     data.getSpousePassport());

        return this;
    }

    @Step("Отправляем форму услуги")
    public MarriageServiceForm submit() {
        logger.info("Нажимаем кнопку отправки формы услуги");
        click(submitButton);
        return this;
    }
}