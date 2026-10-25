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
import utils.TestDataReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("ЗАГС API")
@Feature("Администрирование")
public class AdminRegistrationApiTest {

    private static final Logger logger = LoggerFactory.getLogger(AdminRegistrationApiTest.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Создание администратора через POST /sendAdminRequest — должен вернуться положительный staffid")
    @Story("Создание администратора")
    public void createAdmin() {
        logger.info("=== СТАРТ createAdmin ===");

        AdminRequestDTO body = TestDataReader.readAdminRequest();
        logger.info("Данные админа из JSON: {} {} {}",
                body.getPersonalLastName(),
                body.getPersonalFirstName(),
                body.getPersonalMiddleName());

        int staffId = given()
                .spec(ApiSpecs.requestSpec())
                .body(body)
                .when()
                .post("/sendAdminRequest")
                .then()
                .statusCode(200)
                .body("data.staffid", notNullValue())
                .body("requestId", notNullValue())
                .extract().path("data.staffid");

        logger.info("Создан администратор, staffid={}", staffId);

        assertTrue(staffId > 0,
                "staffid должен быть положительным, получен: " + staffId);

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ createAdmin ===");
    }
}