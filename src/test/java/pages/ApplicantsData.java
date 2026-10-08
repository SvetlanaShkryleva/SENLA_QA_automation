package pages;

import model.MarriageApplicationData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApplicantsData extends BasePage {

    private final By lastNameField   = By.xpath("//label[contains(text(), 'Фамилия')]/following::input[1]");
    private final By firstNameField  = By.xpath("//label[contains(text(), 'Имя')]/following::input[1]");
    private final By middleNameField = By.xpath("//label[contains(text(), 'Отчество')]/following::input[1]");
    private final By phoneField      = By.xpath("//label[contains(text(), 'Телефон')]/following::input[1]");
    private final By passportField   = By.xpath("//label[contains(text(), 'Номер паспорта')]/following::input[1]");
    private final By addressField    = By.xpath("//label[contains(text(), 'Адрес')]/following::input[1]");

    private final By nextButton =
            By.cssSelector("button:nth-child(2)");

    public ApplicantsData(WebDriver driver) {
        super(driver);
    }

    public ApplicantsData fillApplicantData(MarriageApplicationData data) {
        type(lastNameField,   data.getApplicantLastName());
        type(firstNameField,  data.getApplicantFirstName());
        type(middleNameField, data.getApplicantMiddleName());
        type(phoneField,      data.getApplicantPhone());
        type(passportField,   data.getApplicantPassport());
        type(addressField,    data.getApplicantAddress());
        return this;
    }

    public ApplicantsData clickNext() {
        click(nextButton);
        return this;
    }
}