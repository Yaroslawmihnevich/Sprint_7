package com.example;

import com.example.client.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersTest extends BaseTest {

    private final OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Получение заказов возвращает 200")
    public void getOrdersShouldReturnOk() {
        //when-then
        orderClient.getOrders()
                .statusCode(HttpStatus.SC_OK)
                .body("orders", notNullValue())
                .body("orders[0]", notNullValue());
    }

}
