package tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import model.api.UserRequestDTO;
import model.api.UserResponseDTO;
import model.api.UserResponseWrapperDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import specs.ApiSpecs;
import utils.TestDataReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("ЗАГС API")
@Feature("Создание заявок")
public class MarriageApplicationApiTest {

    private static final Logger logger = LoggerFactory.getLogger(MarriageApplicationApiTest.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Создание заявки на брак через API")
    public void createMarriageApplication() {
        logger.info("=== СТАРТ createMarriageApplication ===");

        UserRequestDTO body = TestDataReader.readWeddingRequest();
        logger.info("Данные заявки из JSON: заявитель {} {}, mode={}",
                body.getPersonalLastName(),
                body.getPersonalFirstName(),
                body.getMode());

        UserResponseWrapperDTO wrapper = given()
                .spec(ApiSpecs.requestSpec())
                .body(body)
                .when()
                .post("/sendUserRequest")
                .then()
                .spec(ApiSpecs.responseOk())
                .body("data.applicationid", notNullValue())
                .extract().as(UserResponseWrapperDTO.class);

        UserResponseDTO response = wrapper.getData();

        logger.info("Создана заявка: applicantid={}, applicationid={}, citizenid={}",
                response.getApplicantid(),
                response.getApplicationid(),
                response.getCitizenid());

        assertTrue(response.getApplicationid() > 0,
                "applicationid должен быть положительным, получен: " + response.getApplicationid());

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ createMarriageApplication ===");
    }
}