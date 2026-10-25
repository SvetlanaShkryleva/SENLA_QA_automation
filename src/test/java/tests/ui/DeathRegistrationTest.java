package tests.ui;

import io.qameta.allure.*;
import model.ui.DeathApplicationDTO;
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
@Story("Регистрация смерти")
public class DeathRegistrationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DeathRegistrationTest.class);

    private LoginPage loginPage;
    private ApplicantForm applicantForm;
    private ServiceChoicePage serviceChoicePage;
    private CitizenForm citizenForm;
    private DeathServiceForm deathServiceForm;
    private ApplicationSuccessPage applicationSuccessPage;

    @BeforeEach
    public void initPages() {
        loginPage = new LoginPage(driver);
        applicantForm = new ApplicantForm(driver);
        serviceChoicePage = new ServiceChoicePage(driver);
        citizenForm = new CitizenForm(driver);
        deathServiceForm = new DeathServiceForm(driver);
        applicationSuccessPage = new ApplicationSuccessPage(driver);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Создание заявки на регистрацию смерти")
    public void registerDeath() {
        logger.info("=== СТАРТ теста registerDeath ===");

        DeathApplicationDTO data = TestDataReader.readDeathData();
        logger.info("Данные заявителя из JSON: {} {} {}",
                data.getApplicantLastName(),
                data.getApplicantFirstName(),
                data.getApplicantMiddleName());

        loginPage.clickLoginAsUser();
        applicantForm.fill(data).clickNext();
        serviceChoicePage.chooseService("Регистрация смерти");
        citizenForm.fill(data).clickNext();
        deathServiceForm.fill(data).submit();

        String applicationNumber = applicationSuccessPage.getApplicationNumber();
        ApplicationNumberStorage.save(ApplicationNumberStorage.DEATH, applicationNumber);
        logger.info("Создана заявка № {}. Номер сохранён под ключом '{}'.",
                applicationNumber, ApplicationNumberStorage.DEATH);

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ теста registerDeath ===");
    }
}