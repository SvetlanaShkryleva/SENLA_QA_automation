package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import model.DeathApplicationData;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.ApplicantsData;
import pages.ApplicationSuccessPage;
import pages.CitizensData;
import pages.DeathServiceData;
import pages.LoginPage;
import pages.ServiceChoice;
import utils.ApplicationNumberStorage;

@Epic("ЗАГС")
@Feature("Регистрация заявок")
@Story("Регистрация смерти")
public class DeathRegistrationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DeathRegistrationTest.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Создание заявки на регистрацию смерти")
    public void registerDeath() {
        logger.info("=== СТАРТ теста registerDeath ===");

        DeathApplicationData data = buildDeathData();
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
                .chooseService("Регистрация смерти");
        logger.info("Выбрана услуга 'Регистрация смерти'");

        new CitizensData(driver)
                .fillCitizenData(data)
                .clickNext();
        logger.info("Заполнена форма данных гражданина");

        new DeathServiceData(driver)
                .fillDeathServiceData(data)
                .submit();
        logger.info("Форма услуги 'Смерть' отправлена");

        String applicationNumber =
                new ApplicationSuccessPage(driver).getApplicationNumber();
        ApplicationNumberStorage.save(ApplicationNumberStorage.DEATH, applicationNumber);
        logger.info("Создана заявка № {}. Номер сохранён под ключом '{}'.",
                applicationNumber, ApplicationNumberStorage.DEATH);

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ теста registerDeath ===");
    }

    /**
     * Собирает VO с данными для регистрации смерти.
     */
    private DeathApplicationData buildDeathData() {
        DeathApplicationData data = new DeathApplicationData();

        // ===== Данные заявителя (форма 1) =====
        data.setApplicantLastName("Кузнецов");
        data.setApplicantFirstName("Андрей");
        data.setApplicantMiddleName("Андреевич");
        data.setApplicantPhone("89993334455");
        data.setApplicantPassport("66666666");
        data.setApplicantAddress("ул. Гагарина, д. 5");

        // ===== Данные гражданина (форма 2) — умерший =====
        data.setCitizenLastName("Кузнецов");
        data.setCitizenFirstName("Николай");
        data.setCitizenMiddleName("Петрович");
        data.setCitizenBirthDate("15.05.1950");
        data.setCitizenPassport("77777777");
        data.setCitizenGender("Мужской");
        data.setCitizenAddress("ул. Гагарина, д. 5");

        // ===== Данные услуги (форма 3) — специфичные для смерти =====
        data.setDeathDate("10.10.2026");
        data.setPlaceOfDeath("г. Санкт-Петербург");

        return data;
    }
}