package com.example;

import com.example.client.CourierClient;
import com.example.service.CourierService;
import io.qameta.allure.AllureId;
import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(JUnitParamsRunner.class)
public class CreateCourierTest extends BaseTest {

    private final CourierClient client = new CourierClient();

    private final CourierService courierService = new CourierService(client);

    private final String login = "suppperrrrrrrr";

    private final String password = "sippperrrdsaddrr";

    @Test
    @DisplayName("Создание курьера возвращает 201")
    public void createCourierShouldReturnCreated() {
        //given
        String firstName = "firstttttrrrsaddrr";

        //when-then
        client.createCourier(login, password, firstName)
                .statusCode(HttpStatus.SC_CREATED)
                .body("ok", equalTo(true));

        courierService.clearCourier(login, password);
    }

    @Test
    @Parameters({
            "login12312414, ",
            ", login12312414",
            ", ",
    })
    @DisplayName("Создание курьера возвращает 400 при незаполненных полях")
    public void createCourierShouldReturnBadRequestWhenSomeFieldsNotPassed(String login, String password) {
        //when-then
        client.createCourier(login, password, "firstName")
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Создание курьера возвращает 409 при существующем курьере")
    public void createCourierShouldReturnConflictWhenCourierAlreadyExists() {
        //given
        String firstName = "firstName";
        client.createCourier(login, password, firstName);

        //when-then
        client.createCourier(login, password, firstName)
                .statusCode(HttpStatus.SC_CONFLICT);

        courierService.clearCourier(login, password);
    }

}
