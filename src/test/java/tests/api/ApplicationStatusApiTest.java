package tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import specs.ApiSpecs;
import utils.ApplicationNumberStorage;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("ЗАГС API")
@Feature("Статус заявок")
public class ApplicationStatusApiTest {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStatusApiTest.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Получение статуса заявки, созданной через UI, — ожидается 'under consideration'")
    @Story("Статус заявки по ID")
    public void getApplicationStatusById() {
        logger.info("=== СТАРТ getApplicationStatusById ===");

        String applicationId = ApplicationNumberStorage.read(ApplicationNumberStorage.MARRIAGE);
        logger.info("Используем номер заявки из файла: applicationId={}", applicationId);

        given()
                .spec(ApiSpecs.requestSpec())
                .pathParam("applicationId", applicationId)
                .when()
                .get("/getApplStatus/{applicationId}")
                .then()
                .statusCode(200)
                .body("data.statusofapplication", equalTo("under consideration"))
                .body("data.kindofapplication", equalTo("Получение свидетельства о браке"))
                .body("data.dateofapplication", notNullValue())
                .body("requestId", notNullValue());

        logger.info("Статус заявки {} подтверждён как 'under consideration'", applicationId);
        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ getApplicationStatusById ===");
    }
}