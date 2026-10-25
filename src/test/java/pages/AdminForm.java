package pages;

import io.qameta.allure.Step;
import model.ui.AdminDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminForm extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(AdminForm.class);

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

    @FindBy(xpath = "//label[contains(text(), 'Дата рождения')]/following::input[1]")
    private WebElement birthDateField;

    @FindBy(xpath = "//button[contains(text(), 'Далее')]")
    private WebElement nextButton;

    public AdminForm(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем данные администратора")
    public AdminForm fill(AdminDTO admin) {
        logger.info("Заполняем форму администратора: {} {} {}",
                admin.getLastName(),
                admin.getFirstName(),
                admin.getMiddleName());

        lastNameField.sendKeys(admin.getLastName());
        firstNameField.sendKeys(admin.getFirstName());
        middleNameField.sendKeys(admin.getMiddleName());
        phoneField.sendKeys(admin.getPhone());
        passportField.sendKeys(admin.getPassport());
        birthDateField.sendKeys(admin.getBirthDate());

        return this;
    }

    @Step("Нажимаем 'Далее' на форме администратора")
    public AdminForm clickNext() {
        logger.info("Нажимаем 'Далее'");
        nextButton.click();
        return this;
    }
}