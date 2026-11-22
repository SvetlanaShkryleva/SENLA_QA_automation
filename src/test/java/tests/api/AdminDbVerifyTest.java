package tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import model.api.AdminRequestDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.AdminStaffIdStorage;
import utils.DbHelper;
import utils.TestDataReader;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Проверка, что администратор, созданный в AdminRegistrationApiTest,
 * есть в БД (reg_office.staff).
 *
 * Порядок запуска:
 *   1. AdminRegistrationApiTest   → создаёт админа, сохраняет staffId
 *   2. AdminDbVerifyTest          → читает staffId, ищет запись в БД
 *
 * Можно запускать без VPN — JDBC работает напрямую.
 */
@Epic("ЗАГС API + БД")
@Feature("Администрирование")
public class AdminDbVerifyTest {

    private static final Logger logger = LoggerFactory.getLogger(AdminDbVerifyTest.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка данных администратора в reg_office.staff по staffId из AdminRegistrationApiTest")
    @Story("Проверка администратора в БД")
    public void verifyAdminInDb() {
        logger.info("=== СТАРТ verifyAdminInDb ===");

        int staffId = AdminStaffIdStorage.read();
        logger.info("Прочитан staffId из файла: {}", staffId);

        Map<String, Object> adminEntity = DbHelper.querySingleRow(
                "SELECT * FROM reg_office.staff WHERE staffid = ?", staffId);

        assertNotNull(adminEntity, "Админ staffId=" + staffId + " не найден в БД");
        logger.info("Найдена запись в БД: {}", adminEntity);

        AdminRequestDTO expected = TestDataReader.readAdminRequest();

        assertAll(
                () -> assertEquals(expected.getPersonalLastName(),   adminEntity.get("surname"),        "Фамилия не совпадает"),
                () -> assertEquals(expected.getPersonalFirstName(),  adminEntity.get("name"),           "Имя не совпадает"),
                () -> assertEquals(expected.getPersonalMiddleName(), adminEntity.get("middlename"),     "Отчество не совпадает"),
                () -> assertEquals(expected.getPersonalNumberOfPassport(), adminEntity.get("passportnumber"), "Паспорт не совпадает"),
                () -> assertEquals(expected.getPersonalPhoneNumber(), adminEntity.get("phonenumber"),    "Телефон не совпадает"),
                () -> assertEquals(expected.getDateofbirth(), String.valueOf(adminEntity.get("dateofbirth")), "Дата рождения не совпадает")
        );

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ verifyAdminInDb ===");
    }
}