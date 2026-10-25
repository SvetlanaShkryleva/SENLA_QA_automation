package pages;

import io.qameta.allure.Step;
import model.ui.BaseApplicationDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicantForm extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ApplicantForm.class);

    @FindBy(xpath = "//label[contains(text(), 'Фамилия')]/following::input[1]")
    private WebElement lastNameField;

    @FindBy(xpath = "//label[contains(text(), 'Имя')]/following::input[1]")
    private WebElement firstNameField;

    @FindBy(xpath = "//label[contains(text(), 'Отчество')]/following::input[1]")
    private WebElement middleNameField;

    @FindBy(xpath = "//label[contains(text(), 'Телефон')]/following::input[1]")
    private WebElement phoneField;

    @FindBy(xpath = "//label[contains(text(), 'Номер паспорта')]/following::input[1]")
    private WebElement passportField;

    @FindBy(xpath = "//label[contains(text(), 'Адрес')]/following::input[1]")
    private WebElement addressField;

    @FindBy(css = "button:nth-child(2)")
    private WebElement nextButton;

    public ApplicantForm(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем данные заявителя")
    public ApplicantForm fill(BaseApplicationDTO data) {
        logger.info("Заполняем данные заявителя: {} {} {}",
                data.getApplicantLastName(),
                data.getApplicantFirstName(),
                data.getApplicantMiddleName());

        lastNameField.sendKeys(data.getApplicantLastName());
        firstNameField.sendKeys(data.getApplicantFirstName());
        middleNameField.sendKeys(data.getApplicantMiddleName());
        phoneField.sendKeys(data.getApplicantPhone());
        passportField.sendKeys(data.getApplicantPassport());
        addressField.sendKeys(data.getApplicantAddress());

        return this;
    }

    @Step("Нажимаем 'Далее' на форме заявителя")
    public ApplicantForm clickNext() {
        logger.info("Нажимаем 'Далее' на форме заявителя");
        nextButton.click();
        return this;
    }
}