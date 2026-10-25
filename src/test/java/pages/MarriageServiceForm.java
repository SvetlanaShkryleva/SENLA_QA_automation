package pages;

import io.qameta.allure.Step;
import model.ui.MarriageApplicationDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarriageServiceForm extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(MarriageServiceForm.class);

    @FindBy(xpath = "//label[contains(text(), 'Дата регистрации')]/following::input[1]")
    private WebElement registrationDateField;

    @FindBy(xpath = "//label[contains(text(), 'Новая фамилия')]/following::input[1]")
    private WebElement newLastNameField;

    @FindBy(xpath = "//label[contains(text(), 'Фамилия супруга')]/following::input[1]")
    private WebElement spouseLastNameField;

    @FindBy(xpath = "//label[contains(text(), 'Имя супруга')]/following::input[1]")
    private WebElement spouseFirstNameField;

    @FindBy(xpath = "//label[contains(text(), 'Отчество супруга')]/following::input[1]")
    private WebElement spouseMiddleNameField;

    @FindBy(xpath = "//label[contains(text(), 'Дата рождения супруга')]/following::input[1]")
    private WebElement spouseBirthDateField;

    @FindBy(xpath = "//label[contains(text(), 'Номер паспорта супруга')]/following::input[1]")
    private WebElement spousePassportField;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div/div[3]/button[3]")
    private WebElement submitButton;

    public MarriageServiceForm(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем данные услуги 'Брак'")
    public MarriageServiceForm fill(MarriageApplicationDTO data) {
        logger.info("Заполняем данные услуги 'Брак': дата регистрации={}, новая фамилия={}, супруг(а): {} {} {}",
                data.getRegistrationDate(),
                data.getNewLastName(),
                data.getSpouseLastName(),
                data.getSpouseFirstName(),
                data.getSpouseMiddleName());

        registrationDateField.sendKeys(data.getRegistrationDate());
        newLastNameField.sendKeys(data.getNewLastName());
        spouseLastNameField.sendKeys(data.getSpouseLastName());
        spouseFirstNameField.sendKeys(data.getSpouseFirstName());
        spouseMiddleNameField.sendKeys(data.getSpouseMiddleName());
        spouseBirthDateField.sendKeys(data.getSpouseBirthDate());
        spousePassportField.sendKeys(data.getSpousePassport());

        return this;
    }

    @Step("Отправляем форму услуги 'Брак'")
    public MarriageServiceForm submit() {
        logger.info("Нажимаем кнопку отправки формы услуги 'Брак'");
        submitButton.click();
        return this;
    }
}