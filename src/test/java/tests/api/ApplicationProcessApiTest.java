package tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import model.api.ProcessRequestDTO;
import model.api.UserResponseWrapperDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import specs.ApiSpecs;
import utils.AdminRequestBuilder;
import utils.UserRequestBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("ЗАГС API")
@Feature("Обработка заявок")
public class ApplicationProcessApiTest {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationProcessApiTest.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Одобрение заявки: requestProcess меняет статус с 'under consideration' на 'approved'")
    @Story("Изменение статуса заявки администратором")
    public void approveApplication() {
        logger.info("=== СТАРТ approveApplication ===");

        // 1. Создаём заявку через API
        UserResponseWrapperDTO wrapper = given()
                .spec(ApiSpecs.requestSpec())
                .body(UserRequestBuilder.weddingRequest())
                .post("/sendUserRequest")
                .then()
                .body("data.applicationid", notNullValue())
                .extract().as(UserResponseWrapperDTO.class);

        int applId = wrapper.getData().getApplicationid();
        logger.info("Создана заявка для одобрения: applId={}", applId);

        // 2. Создаём админа через API
        int staffId = given()
                .spec(ApiSpecs.requestSpec())
                .body(AdminRequestBuilder.validAdmin())
                .post("/sendAdminRequest")
                .then()
                .body("data.staffid", notNullValue())
                .extract().path("data.staffid");

        logger.info("Создан администратор для обработки: staffId={}", staffId);

        // 3. Проверяем начальный статус — должен быть "under consideration"
        String initialStatus = given()
                .spec(ApiSpecs.requestSpec())
                .pathParam("applicationId", applId)
                .get("/getApplStatus/{applicationId}")
                .then()
                .statusCode(200)
                .extract().path("data.statusofapplication");

        logger.info("Начальный статус заявки {}: '{}'", applId, initialStatus);

        // 4. Одобряем заявку
        ProcessRequestDTO process = new ProcessRequestDTO();
        process.setApplId(applId);
        process.setStaffid(staffId);
        process.setAction("approved");

        given()
                .spec(ApiSpecs.requestSpec())
                .body(process)
                .when()
                .post("/requestProcess")
                .then()
                .statusCode(200)
                .body("data.applicationid", equalTo(applId))
                .body("requestId", notNullValue());

        logger.info("Отправлен запрос на одобрение заявки {}", applId);

        // 5. Проверяем, что статус изменился
        String finalStatus = given()
                .spec(ApiSpecs.requestSpec())
                .pathParam("applicationId", applId)
                .get("/getApplStatus/{applicationId}")
                .then()
                .statusCode(200)
                .extract().path("data.statusofapplication");

        logger.info("Финальный статус заявки {}: '{}'", applId, finalStatus);

        assert finalStatus.equals("approved")
                : "Ожидался статус 'approved', получен: '" + finalStatus + "'";

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ approveApplication ===");
    }
}