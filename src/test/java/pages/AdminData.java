package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminData extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(AdminData.class);

    private final By lastNameField   = By.xpath("//label[contains(text(), 'Фамилия')]/following::input[1]");
    private final By firstNameField  = By.xpath("//label[contains(text(), 'Имя')]/following::input[1]");
    private final By middleNameField = By.xpath("//label[contains(text(), 'Отчество')]/following::input[1]");
    private final By phoneField      = By.xpath("//label[contains(text(), 'Телефон')]/following::input[1]");
    private final By passportField   = By.xpath("//label[contains(text(), 'Номер паспорта')]/following::input[1]");
    private final By birthDateField  = By.xpath("//label[contains(text(), 'Дата рождения')]/following::input[1]");

    private final By nextButton =
            By.xpath("//button[contains(text(), 'Далее')]");

    public AdminData(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем данные администратора: {lastName} {firstName} {middleName}")
    public AdminData fillAdminData(String lastName, String firstName, String middleName,
                                   String phone, String passport, String birthDate) {
        logger.info("Заполняем форму администратора: {} {} {}, тел. {}, паспорт {}, дата рождения {}",
                lastName, firstName, middleName, phone, passport, birthDate);
        type(lastNameField,   lastName);
        type(firstNameField,  firstName);
        type(middleNameField, middleName);
        type(phoneField,      phone);
        type(passportField,   passport);
        type(birthDateField,  birthDate);
        return this;
    }

    @Step("Нажимаем 'Далее' на форме администратора")
    public AdminData clickNext() {
        logger.info("Нажимаем 'Далее'");
        click(nextButton);
        return this;
    }
}