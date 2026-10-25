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

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("ЗАГС API")
@Feature("Список заявок")
public class ApplicationsListApiTest {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationsListApiTest.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Получение списка всех заявок — должна присутствовать заявка, созданная через UI")
    @Story("Список всех заявок")
    public void getAllApplicationsContainsMarriageApplication() {
        logger.info("=== СТАРТ getAllApplicationsContainsMarriageApplication ===");

        String applicationIdStr = ApplicationNumberStorage.read(ApplicationNumberStorage.MARRIAGE);
        int applicationId = Integer.parseInt(applicationIdStr);
        logger.info("Ищем в списке заявку с applicationid={}", applicationId);

        List<Integer> allIds = given()
                .spec(ApiSpecs.requestSpec())
                .when()
                .get("/getApplications")
                .then()
                .statusCode(200)
                .body("requestId", notNullValue())
                .body("data", notNullValue())
                .extract().path("data.applicationid");

        logger.info("Получено заявок в списке: {}", allIds.size());

        assertTrue(allIds.contains(applicationId),
                "Заявка " + applicationId + " не найдена в списке. Все applicationid: " + allIds);

        logger.info("Заявка {} найдена в списке", applicationId);
        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ getAllApplicationsContainsMarriageApplication ===");
    }
}