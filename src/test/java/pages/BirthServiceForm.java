package pages;

import io.qameta.allure.Step;
import model.ui.BirthApplicationDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BirthServiceForm extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BirthServiceForm.class);

    @FindBy(xpath = "//label[contains(text(), 'Место рождения')]/following::input[1]")
    private WebElement placeOfBirthField;

    @FindBy(xpath = "//label[contains(text(), 'Мать')]/following::input[1]")
    private WebElement motherField;

    @FindBy(xpath = "//label[contains(text(), 'Отец')]/following::input[1]")
    private WebElement fatherField;

    @FindBy(xpath = "//label[contains(text(), 'Бабушка')]/following::input[1]")
    private WebElement grandmotherField;

    @FindBy(xpath = "//label[contains(text(), 'Дедушка')]/following::input[1]")
    private WebElement grandfatherField;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div/div[3]/button[3]")
    private WebElement submitButton;

    public BirthServiceForm(WebDriver driver) {
        super(driver);
    }

    @Step("Заполняем данные услуги 'Рождение'")
    public BirthServiceForm fill(BirthApplicationDTO data) {
        logger.info("Заполняем данные услуги 'Рождение': место рождения={}, мать={}, отец={}, бабушка={}, дедушка={}",
                data.getPlaceOfBirth(),
                data.getMother(),
                data.getFather(),
                data.getGrandmother(),
                data.getGrandfather());

        placeOfBirthField.sendKeys(data.getPlaceOfBirth());
        motherField.sendKeys(data.getMother());
        fatherField.sendKeys(data.getFather());
        grandmotherField.sendKeys(data.getGrandmother());
        grandfatherField.sendKeys(data.getGrandfather());

        return this;
    }

    @Step("Отправляем форму услуги 'Рождение'")
    public BirthServiceForm submit() {
        logger.info("Нажимаем кнопку отправки формы услуги 'Рождение'");
        submitButton.click();
        return this;
    }
}