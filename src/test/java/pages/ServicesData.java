package pages;

import model.MarriageApplicationData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ServicesData extends BasePage {

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

    public ServicesData(WebDriver driver) {
        super(driver);
    }

    public ServicesData fillMarriageServiceData(MarriageApplicationData data) {
        type(registrationDateField,   data.getRegistrationDate());
        type(newLastNameField,        data.getNewLastName());
        type(spouseLastNameField,     data.getSpouseLastName());
        type(spouseFirstNameField,    data.getSpouseFirstName());
        type(spouseMiddleNameField,   data.getSpouseMiddleName());
        type(spouseBirthDateField,    data.getSpouseBirthDate());
        type(spousePassportField,     data.getSpousePassport());
        return this;
    }

    public ServicesData submit() {
        click(submitButton);
        return this;
    }
}