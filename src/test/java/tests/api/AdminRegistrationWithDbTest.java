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
import specs.ApiSpecs;
import utils.DbHelper;
import utils.TestDataReader;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("ЗАГС API + БД")
@Feature("Администрирование")
public class AdminRegistrationWithDbTest {

    private static final Logger logger = LoggerFactory.getLogger(AdminRegistrationWithDbTest.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Создание админа через API и проверка записи в БД (reg_office.staff)")
    @Story("Создание администратора с проверкой в БД")
    public void createAdminAndVerifyInDb() {
        logger.info("=== СТАРТ createAdminAndVerifyInDb ===");

        AdminRequestDTO request = TestDataReader.readAdminRequest();

        // 1. Создаём админа через API
        int staffId = given()
                .spec(ApiSpecs.requestSpec())
                .body(request)
                .post("/sendAdminRequest")
                .then()
                .statusCode(200)
                .body("data.staffid", notNullValue())
                .extract().path("data.staffid");

        logger.info("Создан администратор через API: staffId={}", staffId);

        // 2. Проверяем запись в БД
        Map<String, Object> row = DbHelper.querySingleRow(
                "SELECT * FROM reg_office.staff WHERE staffid = ?", staffId);

        assertNotNull(row, "Админ staffId=" + staffId + " не найден в БД");

        // 3. Сверяем поля
        assertAll(
                () -> assertEquals(request.getPersonalLastName(), row.get("surname"),
                        "Фамилия не совпадает"),
                () -> assertEquals(request.getPersonalFirstName(), row.get("name"),
                        "Имя не совпадает"),
                () -> assertEquals(request.getPersonalMiddleName(), row.get("middlename"),
                        "Отчество не совпадает"),
                () -> assertEquals(request.getPersonalNumberOfPassport(), row.get("passportnumber"),
                        "Номер паспорта не совпадает"),
                () -> assertEquals(request.getPersonalPhoneNumber(), row.get("phonenumber"),
                        "Телефон не совпадает"),
                () -> assertEquals(request.getDateofbirth(), String.valueOf(row.get("dateofbirth")),
                        "Дата рождения не совпадает")
        );

        logger.info("Данные админа staffId={} подтверждены в БД: {}", staffId, row);
        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ createAdminAndVerifyInDb ===");
    }
}