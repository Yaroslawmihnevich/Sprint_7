package com.example;

import com.example.client.CourierClient;
import com.example.service.CourierService;
import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(JUnitParamsRunner.class)
public class LoginCourierTest extends BaseTest {

    private final CourierClient client = new CourierClient();

    private final CourierService courierService = new CourierService(client);

    @Test
    @DisplayName("Авторизация курьера возвращает 200")
    public void loginCourierShouldReturnOk() {
        //given
        String login = "loginasdasd";
        String password = "passsadasdasdafs";
        client.createCourier(login, password, "firstttttt");

        //when-then
        client.loginCourier(login, password)
                .statusCode(HttpStatus.SC_OK)
                .body("id", notNullValue());

        courierService.clearCourier(login, password);
    }

    @Test
    @Parameters({
            "login12312414, ",
            ", login12312414",
            ", ",
    })
    @DisplayName("Авторизация курьера возвращает 400 при незаполненных полях")
    public void loginCourierShouldReturnBadRequestWhenSomeFieldsNotPassed(String login, String password) {
        //when-then
        client.loginCourier(login, password)
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Авторизация курьера возвращает 404 при несуществующем курьере")
    public void loginCourierShouldNotFoundWhenUserNotExists() {
        //given
        String login = "loginasdasdasgdjhasgfjsgahjfhakjhdfhksafsasjf";
        String password = "passsadasdasdafsasfjhdafkjsdhfkjshfkjsdhfjksd";

        //when-then
        client.loginCourier(login, password)
                .log()
                .all()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
