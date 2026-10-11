package tests;

import elements.ApplicationTable;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
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

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка, что созданная заявка видна администратору со статусом 'На рассмотрении'")
    public void adminSeesMarriageApplicationStatus() {
        logger.info("=== СТАРТ теста adminSeesMarriageApplicationStatus ===");

        String applicationNumber = ApplicationNumberStorage.read();
        logger.info("Прочитан номер заявки из файла: {}", applicationNumber);

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
                "Заявка № " + applicationNumber + " не найдена в таблице администратора");
        logger.info("Заявка № {} найдена в таблице", applicationNumber);

        String actualStatus = table.getStatusTextById(applicationNumber);
        logger.info("Фактический статус заявки № {}: '{}'", applicationNumber, actualStatus);

        assertAll(
                () -> assertEquals("На рассмотрении", actualStatus,
                        "Заявка № " + applicationNumber
                                + ": ожидался статус 'На рассмотрении', получен '" + actualStatus + "'")
        );

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ теста adminSeesMarriageApplicationStatus ===");
    }
}