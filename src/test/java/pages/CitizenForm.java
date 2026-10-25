package pages;

import io.qameta.allure.Step;
import model.ui.BaseApplicationDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CitizenForm extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CitizenForm.class);

    @FindBy(xpath = "//label[contains(text(), 'Фамилия')]/following::input[1]")
    private WebElement lastNameField;

    @FindBy(xpath = "//label[contains(text(), 'Имя')]/following::input[1]")
    private WebElement firstNameField;

    @FindBy(xpath = "//label[contains(text(), 'Отчество')]/following::input[1]")
    private WebElement middleNameField;

    @FindBy(xpath = "//label[contains(text(), 'Дата рождения')]/following::input[1]")
    private WebElement birthDateField;

    @FindBy(xpath = "//label[contains(text(), 'Номер паспорта')]/following::input[1]")
    private WebElement passportField;

    @FindBy(xpath = "//label[contains(text(), 'Пол')]/following::input[1]")
    private WebElement genderField;

    @FindBy(xpath = "//label[contains(text(), 'Адрес прописки')]/following::input[1]")
    private WebElement addressField;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div/div[3]/button[3]")
    private WebElement nextButton;

    public CitizenForm(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем данные гражданина")
    public CitizenForm fill(BaseApplicationDTO data) {
        logger.info("Заполняем данные гражданина: {} {} {}",
                data.getCitizenLastName(),
                data.getCitizenFirstName(),
                data.getCitizenMiddleName());

        lastNameField.sendKeys(data.getCitizenLastName());
        firstNameField.sendKeys(data.getCitizenFirstName());
        middleNameField.sendKeys(data.getCitizenMiddleName());
        birthDateField.sendKeys(data.getCitizenBirthDate());

        if (data.getCitizenPassport() != null) {
            passportField.sendKeys(data.getCitizenPassport());
        }

        genderField.sendKeys(data.getCitizenGender());
        addressField.sendKeys(data.getCitizenAddress());

        return this;
    }

    @Step("Нажимаем 'Далее' на форме гражданина")
    public CitizenForm clickNext() {
        logger.info("Нажимаем 'Далее' на форме гражданина");
        nextButton.click();
        return this;
    }
}