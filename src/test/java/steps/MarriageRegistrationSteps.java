package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.ui.MarriageApplicationDTO;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;
import tests.BaseTest;
import utils.ApplicationNumberStorage;
import utils.TestDataReader;

@Epic("ЗАГС (Cucumber)")
@Feature("Регистрация заявок")
@Story("Регистрация брака")
public class MarriageRegistrationSteps extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(MarriageRegistrationSteps.class);

    private LoginPage loginPage;
    private ApplicantForm applicantForm;
    private ServiceChoicePage serviceChoicePage;
    private CitizenForm citizenForm;
    private MarriageServiceForm marriageServiceForm;
    private ApplicationSuccessPage applicationSuccessPage;

    private MarriageApplicationDTO data;
    private String applicationNumber;

    // ===================== ХУКИ =====================

    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("=== СТАРТ сценария: '{}' | Теги: {} ===",
                scenario.getName(), scenario.getSourceTagNames());

        // JUnit @BeforeEach под Cucumber НЕ выполняется — вызываем вручную.
        openApp();

        initPages();
        logger.info("Страницы инициализированы, драйвер готов");
    }

    @After
    public void afterScenario(Scenario scenario) {
        logger.info("=== КОНЕЦ сценария: '{}' | Статус: {} ===",
                scenario.getName(), scenario.getStatus());

        // JUnit @AfterEach под Cucumber НЕ выполняется — вызываем вручную.
        try {
            tearDown();
            logger.info("Драйвер закрыт");
        } catch (Exception e) {
            logger.warn("Ошибка при закрытии драйвера: {}", e.getMessage());
        }
    }

    private void initPages() {
        loginPage = new LoginPage(driver);
        applicantForm = new ApplicantForm(driver);
        serviceChoicePage = new ServiceChoicePage(driver);
        citizenForm = new CitizenForm(driver);
        marriageServiceForm = new MarriageServiceForm(driver);
        applicationSuccessPage = new ApplicationSuccessPage(driver);
    }

    // ===================== ШАГИ =====================
    // (без изменений, как в предыдущем ответе)
    @Дано("пользователь авторизован на портале ЗАГС")
    public void userAuthorized() {
        logger.info("Шаг: авторизация пользователя на портале ЗАГС");
        loginPage.clickLoginAsUser();
    }

    @Когда("пользователь заполняет данные заявителя")
    public void fillApplicantForm() {
        data = TestDataReader.readMarriageData();
        logger.info("Данные из JSON: {} {} {}",
                data.getApplicantLastName(), data.getApplicantFirstName(), data.getApplicantMiddleName());
        applicantForm.fill(data).clickNext();
    }

    @И("выбирает услугу {string}")
    public void chooseService(String serviceName) {
        logger.info("Шаг: выбор услуги '{}'", serviceName);
        serviceChoicePage.chooseService(serviceName);
    }

    @И("заполняет данные о гражданине")
    public void fillCitizenForm() {
        citizenForm.fill(data).clickNext();
    }

    @И("заполняет данные о браке")
    public void fillMarriageForm() {
        marriageServiceForm.fill(data).submit();
    }

    @Тогда("отображается страница успешной регистрации")
    public void successPageDisplayed() {
        Assertions.assertNotNull(applicationSuccessPage,
                "Страница успешной регистрации должна быть инициализирована");
    }

    @И("номер заявки сохранён под ключом {string}")
    public void saveApplicationNumber(String key) {
        applicationNumber = applicationSuccessPage.getApplicationNumber();
        logger.info("Получен номер заявки: {}", applicationNumber);
        ApplicationNumberStorage.save(key, applicationNumber);
        logger.info("Номер заявки '{}' сохранён под ключом '{}'", applicationNumber, key);
    }
}