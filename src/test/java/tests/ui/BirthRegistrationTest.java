package tests.ui;

import io.qameta.allure.*;
import model.ui.BirthApplicationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;
import tests.BaseTest;
import utils.ApplicationNumberStorage;
import utils.TestDataReader;

@Epic("ЗАГС")
@Feature("Регистрация заявок")
@Story("Регистрация рождения")
public class BirthRegistrationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BirthRegistrationTest.class);

    private LoginPage loginPage;
    private ApplicantForm applicantForm;
    private ServiceChoicePage serviceChoicePage;
    private CitizenForm citizenForm;
    private BirthServiceForm birthServiceForm;
    private ApplicationSuccessPage applicationSuccessPage;

    @BeforeEach
    public void initPages() {
        loginPage = new LoginPage(driver);
        applicantForm = new ApplicantForm(driver);
        serviceChoicePage = new ServiceChoicePage(driver);
        citizenForm = new CitizenForm(driver);
        birthServiceForm = new BirthServiceForm(driver);
        applicationSuccessPage = new ApplicationSuccessPage(driver);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Создание заявки на регистрацию рождения")
    public void registerBirth() {
        logger.info("=== СТАРТ теста registerBirth ===");

        BirthApplicationDTO data = TestDataReader.readBirthData();
        logger.info("Данные заявителя из JSON: {} {} {}",
                data.getApplicantLastName(),
                data.getApplicantFirstName(),
                data.getApplicantMiddleName());

        loginPage.clickLoginAsUser();
        applicantForm.fill(data).clickNext();
        serviceChoicePage.chooseService("Регистрация рождения");
        citizenForm.fill(data).clickNext();
        birthServiceForm.fill(data).submit();

        String applicationNumber = applicationSuccessPage.getApplicationNumber();
        ApplicationNumberStorage.save(ApplicationNumberStorage.BIRTH, applicationNumber);
        logger.info("Создана заявка № {}. Номер сохранён под ключом '{}'.",
                applicationNumber, ApplicationNumberStorage.BIRTH);

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ теста registerBirth ===");
    }
}