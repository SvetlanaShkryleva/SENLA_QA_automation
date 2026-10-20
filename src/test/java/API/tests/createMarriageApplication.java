package API.tests;

import API.models.UserRequest;
import API.models.UserResponse;
import API.models.UserResponseWrapper;
import API.specs.ApiSpecs;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("ЗАГС API")
@Feature("Создание заявок")
public class createMarriageApplication {

    private static final Logger logger = LoggerFactory.getLogger(createMarriageApplication.class);

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Создание заявки на брак через API")
    public void createMarriageApplication() {
        logger.info("=== СТАРТ createMarriageApplication ===");

        UserRequest body = buildWeddingUser();

        UserResponseWrapper wrapper = given()
                .spec(ApiSpecs.requestSpec())
                .body(body)
                .when()
                .post("/sendUserRequest")
                .then()
                .spec(ApiSpecs.responseOk())
                .body("data.applicationid", notNullValue())
                .extract().as(UserResponseWrapper.class);

        UserResponse response = wrapper.getData();

        logger.info("Создана заявка: applicantid={}, applicationid={}, citizenid={}",
                response.getApplicantid(),
                response.getApplicationid(),
                response.getCitizenid());

        assertTrue(response.getApplicationid() > 0,
                "applicationid должен быть положительным, получен: " + response.getApplicationid());

        logger.info("=== УСПЕШНОЕ ЗАВЕРШЕНИЕ createMarriageApplication ===");
    }

    private UserRequest buildWeddingUser() {
        UserRequest u = new UserRequest();

        u.setMode("wedding");

        // Заявитель
        u.setPersonalLastName("Иванов");
        u.setPersonalFirstName("Иван");
        u.setPersonalMiddleName("Иванович");
        u.setPersonalPhoneNumber("89999999999");
        u.setPersonalNumberOfPassport("00000000");
        u.setPersonalAddress("ул. Ленина, д. 1");

        // Гражданин
        u.setCitizenLastName("Петрова");
        u.setCitizenFirstName("Мария");
        u.setCitizenMiddleName("Сергеевна");
        u.setCitizenBirthDate("1995-01-01");
        u.setCitizenNumberOfPassport("11111111");
        u.setCitizenGender("Женский");
        u.setCitizenAddress("ул. Пушкина, д. 2");

        // Данные свадьбы
        u.setDateOfMarriage("2025-11-11");
        u.setNewLastName("Иванова");
        u.setAnotherPersonLastName("Иванова");
        u.setAnotherPersonFirstName("Мария");
        u.setAnotherPersonMiddleName("Сергеевна");
        u.setBirth_of_anotoherPerson("1995-01-01");
        u.setAnotherPersonPassport("11111111");

        // Поля для других услуг — null
        u.setBirth_place(null);
        u.setBirth_mother(null);
        u.setBirth_father(null);
        u.setBirth_grandma(null);
        u.setBirth_grandpa(null);
        u.setDeath_dateOfDeath(null);
        u.setDeath_placeOfDeath(null);

        return u;
    }
}