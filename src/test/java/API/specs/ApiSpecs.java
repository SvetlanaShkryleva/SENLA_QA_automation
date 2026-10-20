package API.specs;

import config.ConfigReader;
import config.Credentials;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ApiSpecs {

    public static RequestSpecification requestSpec() {
        Credentials creds = ConfigReader.readCredentials();

        PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
        auth.setUserName(creds.getUsername());
        auth.setPassword(creds.getPassword());

        return new RequestSpecBuilder()
                .setBaseUri("https://regoffice.senla.eu")
                .setContentType("application/json")
                .setAuth(auth)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static ResponseSpecification responseOk() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}