package tests;

import elements.ApplicationTable;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AdminData;
import pages.LoginPage;
import utils.ApplicationNumberStorage;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("ЗАГС")
@Feature("Администрирование заявок")
@Story("Проверка статуса заявки администратором")
public class AdminStatusCheckTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(AdminStatusCheckTest.class);

    @ParameterizedTest(name = "Проверка статуса заявки: {0}")
    @ValueSource(strings = {"marriage", "birth", "death"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка, что созданная заявка видна администратору со статусом 'На рассмотрении'")
    public void adminSeesApplicationStatus(String serviceKey) {
        logger.info("=== СТАРТ теста adminSeesApplicationStatus для услуги '{}' ===", serviceKey);

        String applicationNumber = ApplicationNumberStorage.read(serviceKey);
        logger.info("Прочитан номер заявки из файла по ключу '{}': {}", serviceKey, applicationNumber);

        new LoginPage(driver).clickLoginAsAdmin();
        logger.info("Выполнен вход как администратор");

        new AdminData(driver)
                .fillAdminData("Админов", "Админ", "Админович",
                        "89990000000", "99999999", "01.01.1980")
                .clickNext();
        logger.info("Заполнена форма администратора");

        ApplicationTable table = new ApplicationTable(driver).waitForTable();
        logger.info("Таблица заявок загружена");

        assertTrue(table.hasRowWithId(applicationNumber),
                "Заявка № " + applicationNumber + " (услуга '" + serviceKey + "') не найдена в таблице администратора");
        logger.info("Заявка № {} найдена в таблице", applicationNumber);

        String actualStatus = table.getStatusTextById(applicationNumber);
        logger.info("Фактический статус заявки № {}: '{}'", applicationNumber, actualStatus);

        assertAll(
                () -> assertEquals("На рассмотрении", actualStatus,
                        "Заявка № " + applicationNumber
                                + " (услуга '" + serviceKey + "'): ожидался статус 'На рассмотрении', получен '" + actualStatus + "'")
        );

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ теста для услуги '{}' ===", serviceKey);
    }
}