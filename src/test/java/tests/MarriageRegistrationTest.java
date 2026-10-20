package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import model.MarriageApplicationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.ApplicantForm;
import pages.ApplicationSuccessPage;
import pages.CitizenForm;
import pages.LoginPage;
import pages.MarriageServiceForm;
import pages.ServiceChoicePage;
import utils.ApplicationNumberStorage;

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

        MarriageApplicationDTO data = buildMarriageData();
        logger.info("Подготовлены данные заявителя: {} {} {}",
                data.getApplicantLastName(),
                data.getApplicantFirstName(),
                data.getApplicantMiddleName());

        loginPage.clickLoginAsUser();
        logger.info("Выполнен вход как пользователь");

        applicantForm
                .fillApplicantData(data)
                .clickNext();
        logger.info("Заполнена форма данных заявителя");

        serviceChoicePage
                .chooseService("Регистрация брака");
        logger.info("Выбрана услуга 'Регистрация брака'");

        citizenForm
                .fillCitizenData(data)
                .clickNext();
        logger.info("Заполнена форма данных гражданина");

        marriageServiceForm
                .fillMarriageServiceData(data)
                .submit();
        logger.info("Форма услуги отправлена");

        String applicationNumber = applicationSuccessPage.getApplicationNumber();
        logger.info("Считан номер заявки: {}", applicationNumber);

        ApplicationNumberStorage.save(ApplicationNumberStorage.MARRIAGE, applicationNumber);
        logger.info("Создана заявка № {}. Номер сохранён под ключом '{}'.",
                applicationNumber, ApplicationNumberStorage.MARRIAGE);

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ теста registerMarriage ===");
    }

    /**
     * Собирает VO с данными для регистрации брака.
     */
    private MarriageApplicationDTO buildMarriageData() {
        MarriageApplicationDTO data = new MarriageApplicationDTO();

        data.setApplicantLastName("Иванов");
        data.setApplicantFirstName("Иван");
        data.setApplicantMiddleName("Иванович");
        data.setApplicantPhone("89999999999");
        data.setApplicantPassport("00000000");
        data.setApplicantAddress("ул дом");

        data.setCitizenLastName("Петрова");
        data.setCitizenFirstName("Мария");
        data.setCitizenMiddleName("Сергеевна");
        data.setCitizenBirthDate("01.01.1995");
        data.setCitizenPassport("11111111");
        data.setCitizenGender("Женский");
        data.setCitizenAddress("ул. Пушкина, д. 1");

        data.setRegistrationDate("11.11.2025");
        data.setNewLastName("Иванова");
        data.setSpouseLastName("Иванова");
        data.setSpouseFirstName("Мария");
        data.setSpouseMiddleName("Сергеевна");
        data.setSpouseBirthDate("01.01.1995");
        data.setSpousePassport("11111111");

        return data;
    }
}