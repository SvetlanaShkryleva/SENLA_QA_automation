package specs;

import config.ConfigReader;
import config.Credentials;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ApiSpecs {

//    static {
//        System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
//        System.setProperty("https.protocols", "TLSv1.2");
//    }

    public static RequestSpecification requestSpec() {
        Credentials creds = ConfigReader.readCredentials();

        PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
        auth.setUserName(creds.getUsername());
        auth.setPassword(creds.getPassword());

        // Таймауты: 10 секунд на соединение, 30 секунд на чтение
        RestAssuredConfig config = RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                                        .setParam("http.connection.timeout", 30_000)   // 30 сек на соединение
                                        .setParam("http.socket.timeout", 60_000));     // 60 сек на чтение
        return new RequestSpecBuilder()
                .setBaseUri("https://regoffice.senla.eu")
                .setContentType("application/json")
                .setAuth(auth)
                .setRelaxedHTTPSValidation()
                .setConfig(config)
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