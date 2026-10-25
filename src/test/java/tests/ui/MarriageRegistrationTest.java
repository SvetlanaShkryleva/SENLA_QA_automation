package tests.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import model.ui.MarriageApplicationDTO;
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
@Story("Регистрация брака")
public class MarriageRegistrationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(MarriageRegistrationTest.class);

    private LoginPage loginPage;
    private ApplicantForm applicantForm;
    private ServiceChoicePage serviceChoicePage;
    private CitizenForm citizenForm;
    private MarriageServiceForm marriageServiceForm;
    private ApplicationSuccessPage applicationSuccessPage;

    @BeforeEach
    public void initPages() {
        loginPage = new LoginPage(driver);
        applicantForm = new ApplicantForm(driver);
        serviceChoicePage = new ServiceChoicePage(driver);
        citizenForm = new CitizenForm(driver);
        marriageServiceForm = new MarriageServiceForm(driver);
        applicationSuccessPage = new ApplicationSuccessPage(driver);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Создание заявки на регистрацию брака и сохранение её номера")
    public void registerMarriage() {
        logger.info("=== СТАРТ теста registerMarriage ===");

        MarriageApplicationDTO data = TestDataReader.readMarriageData();
        logger.info("Данные заявителя из JSON: {} {} {}",
                data.getApplicantLastName(),
                data.getApplicantFirstName(),
                data.getApplicantMiddleName());

        loginPage.clickLoginAsUser();
        applicantForm.fill(data).clickNext();
        serviceChoicePage.chooseService("Регистрация брака");
        citizenForm.fill(data).clickNext();
        marriageServiceForm.fill(data).submit();

        String applicationNumber = applicationSuccessPage.getApplicationNumber();
        ApplicationNumberStorage.save(ApplicationNumberStorage.MARRIAGE, applicationNumber);
        logger.info("Создана заявка № {}. Номер сохранён под ключом '{}'.",
                applicationNumber, ApplicationNumberStorage.MARRIAGE);

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ теста registerMarriage ===");
    }
}