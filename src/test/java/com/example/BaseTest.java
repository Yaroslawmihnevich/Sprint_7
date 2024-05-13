package com.example;

import com.example.dto.CreateCourierRequest;
import com.example.dto.LoginCourierRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

public class BaseTest {

    protected static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/api/v1/";
    protected static final String COURIER_PATH = "/courier";
    protected static final String COURIER_LOGIN_PATH = COURIER_PATH + "/login";
    protected static final String ORDERS_PATH = "/orders";


    @BeforeClass
    public static void init() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                new ObjectMapperConfig().jackson2ObjectMapperFactory(
                        (type, s) -> {
                            ObjectMapper objectMapper = new ObjectMapper();
                            return objectMapper;
                        }
                )
        );
    }

    protected RequestSpecification logRequest() {
        return given().log().all();
    }

    protected void createCourier(String login, String password, String firstName) {
        logRequest().with().body(new CreateCourierRequest(login, password, firstName))
                .contentType(ContentType.JSON)
                .post(COURIER_PATH)
                .then()
                .log()
                .all();
    }

    protected Long loginCourier(String login, String password) {
        return with().body(new LoginCourierRequest(login, password))
                .contentType(ContentType.JSON)
                .post(COURIER_LOGIN_PATH)
                .then()
                .extract()
                .body()
                .jsonPath()
                .getLong("id");
    }

    protected void deleteCourier(Long id) {
        with().delete(COURIER_PATH + "/" + id);
    }

    protected void clearCourier(String login, String password) {
        Long id = loginCourier(login, password);
        deleteCourier(id);
    }

}
