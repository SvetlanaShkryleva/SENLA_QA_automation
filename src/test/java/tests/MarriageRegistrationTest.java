package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import model.MarriageApplicationData;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.ApplicantsData;
import pages.ApplicationSuccessPage;
import pages.CitizensData;
import pages.LoginPage;
import pages.ServiceChoice;
import pages.ServicesData;
import utils.ApplicationNumberStorage;

@Epic("ЗАГС")
@Feature("Регистрация заявок")
@Story("Регистрация брака")
public class MarriageRegistrationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(MarriageRegistrationTest.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Создание заявки на регистрацию брака и сохранение её номера")
    public void registerMarriage() {
        logger.info("=== СТАРТ теста registerMarriage ===");

        MarriageApplicationData data = buildMarriageData();
        logger.info("Подготовлены данные заявителя: {} {} {}",
                data.getApplicantLastName(),
                data.getApplicantFirstName(),
                data.getApplicantMiddleName());

        new LoginPage(driver).clickLoginAsUser();
        logger.info("Выполнен вход как пользователь");

        new ApplicantsData(driver)
                .fillApplicantData(data)
                .clickNext();
        logger.info("Заполнена форма данных заявителя");

        new ServiceChoice(driver)
                .chooseService("Регистрация брака");
        logger.info("Выбрана услуга 'Регистрация брака'");

        new CitizensData(driver)
                .fillCitizenData(data)
                .clickNext();
        logger.info("Заполнена форма данных гражданина");

        new ServicesData(driver)
                .fillMarriageServiceData(data)
                .submit();
        logger.info("Форма услуги отправлена");

        String applicationNumber =
                new ApplicationSuccessPage(driver).getApplicationNumber();
        logger.info("Считан номер заявки: {}", applicationNumber);

        ApplicationNumberStorage.save(applicationNumber);
        logger.info("Номер заявки сохранён в target/application-number.txt");

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ теста registerMarriage ===");
    }

    /**
     * Собирает VO с данными для регистрации брака.
     */
    private MarriageApplicationData buildMarriageData() {
        MarriageApplicationData data = new MarriageApplicationData();

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