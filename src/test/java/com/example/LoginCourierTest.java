package com.example;

import com.example.dto.LoginCourierRequest;
import io.restassured.http.ContentType;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(JUnitParamsRunner.class)
public class LoginCourierTest extends BaseTest {

    @Test
    public void loginCourierShouldReturnOk() {
        //given
        String login = "loginasdasd";
        String password = "passsadasdasdafs";
        createCourier(login, password, "firstttttt");

        //when-then
        logRequest().with()
                .body(new LoginCourierRequest(login, password))
                .contentType(ContentType.JSON)
                .post(COURIER_LOGIN_PATH)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .body("id", notNullValue());

        clearCourier(login, password);
    }

    @Test
    @Parameters({
            "login12312414, ",
            ", login12312414",
            ", ",
    })
    public void loginCourierShouldReturnBadRequestWhenSomeFieldsNotPassed(String login, String password) {
        //when-then
        logRequest().with()
                .body(new LoginCourierRequest(login, password))
                .contentType(ContentType.JSON)
                .post(COURIER_LOGIN_PATH)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void loginCourierShouldNotFoundWhenUserNotExists() {
        //given
        String login = "loginasdasdasgdjhasgfjsgahjfhakjhdfhksafsasjf";
        String password = "passsadasdasdafsasfjhdafkjsdhfkjshfkjsdhfjksd";

        //when-then
        logRequest().with()
                .body(new LoginCourierRequest(login, password))
                .contentType(ContentType.JSON)
                .post(COURIER_LOGIN_PATH)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
