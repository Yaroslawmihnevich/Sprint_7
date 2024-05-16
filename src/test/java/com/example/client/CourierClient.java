package com.example.client;

import com.example.dto.CreateCourierRequest;
import com.example.dto.LoginCourierRequest;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.with;

public class CourierClient extends BaseClient {

    protected static final String COURIER_PATH = "/courier";
    protected static final String COURIER_LOGIN_PATH = COURIER_PATH + "/login";

    @Step("Создание курьера")
    public ValidatableResponse createCourier(String login, String password, String firstName) {
        return logRequest().with().body(new CreateCourierRequest(login, password, firstName))
                .contentType(ContentType.JSON)
                .post(COURIER_PATH)
                .then()
                .log()
                .all();
    }

    @Step("Авторизация курьера")
    public ValidatableResponse loginCourier(String login, String password) {
        return with().body(new LoginCourierRequest(login, password))
                .contentType(ContentType.JSON)
                .post(COURIER_LOGIN_PATH)
                .then();
    }

    @Step("Удаление курьера")
    public void deleteCourier(Long id) {
        with().delete(COURIER_PATH + "/" + id);
    }

}
