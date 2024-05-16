package com.example;

import com.example.client.OrderClient;
import com.example.dto.CreateOrderRequest;
import com.example.dto.enumeration.Color;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.ContentType;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(JUnitParamsRunner.class)
public class CreateOrderTest extends BaseTest {

    private final OrderClient client = new OrderClient();

    @Test
    @Parameters({
            "BLACK,",
            ",GREY",
            "BLACK,GREY",
            ",",
    })
    @DisplayName("Создание заказа возвращает 201")
    public void createOrderShouldReturnOk(String firstColor, String secondColor) {
        //given
        List<Color> colors = new ArrayList<>();
        if (!Objects.equals(firstColor, "")) {
            colors.add(Color.valueOf(firstColor));
        }
        if (!Objects.equals(secondColor, "")) {
            colors.add(Color.valueOf(secondColor));
        }

        //when-then
        client.createOrder(new CreateOrderRequest(
                        "Naruto", "Uzumaki", "Konoha", "Hokage Village",
                        "+7 800 355 35 35", 5L, "2020-06-06",
                        "Saske, come back to Konoha", colors
                ))
                .statusCode(HttpStatus.SC_CREATED)
                .body("track", notNullValue());
    }
}
