package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import model.BirthApplicationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.ApplicantForm;
import pages.ApplicationSuccessPage;
import pages.BirthServiceForm;
import pages.CitizenForm;
import pages.LoginPage;
import pages.ServiceChoicePage;
import utils.ApplicationNumberStorage;

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

        BirthApplicationDTO data = buildBirthData();
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
                .chooseService("Регистрация рождения");
        logger.info("Выбрана услуга 'Регистрация рождения'");

        citizenForm
                .fillCitizenData(data)
                .clickNext();
        logger.info("Заполнена форма данных гражданина");

        birthServiceForm
                .fillBirthServiceData(data)
                .submit();
        logger.info("Форма услуги 'Рождение' отправлена");

        String applicationNumber = applicationSuccessPage.getApplicationNumber();
        ApplicationNumberStorage.save(ApplicationNumberStorage.BIRTH, applicationNumber);
        logger.info("Создана заявка № {}. Номер сохранён под ключом '{}'.",
                applicationNumber, ApplicationNumberStorage.BIRTH);

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ теста registerBirth ===");
    }

    /**
     * Собирает VO с данными для регистрации рождения.
     */
    private BirthApplicationDTO buildBirthData() {
        BirthApplicationDTO data = new BirthApplicationDTO();

        // ===== Данные заявителя (форма 1) =====
        data.setApplicantLastName("Сидоров");
        data.setApplicantFirstName("Пётр");
        data.setApplicantMiddleName("Петрович");
        data.setApplicantPhone("89991112233");
        data.setApplicantPassport("44444444");
        data.setApplicantAddress("ул. Ленина, д. 10");

        // ===== Данные гражданина (форма 2) — ребёнок =====
        data.setCitizenLastName("Сидоров");
        data.setCitizenFirstName("Михаил");
        data.setCitizenMiddleName("Петрович");
        data.setCitizenBirthDate("01.01.2024");
        data.setCitizenPassport("55555555");
        data.setCitizenGender("Мужской");
        data.setCitizenAddress("ул. Ленина, д. 10");

        // ===== Данные услуги (форма 3) — специфичные для рождения =====
        data.setPlaceOfBirth("г. Москва");
        data.setMother("Сидорова Анна Ивановна");
        data.setFather("Сидоров Пётр Петрович");
        data.setGrandmother("Сидорова Ольга Сергеевна");
        data.setGrandfather("Сидоров Иван Иванович");

        return data;
    }
}