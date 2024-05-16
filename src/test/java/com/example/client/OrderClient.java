package com.example.client;

import com.example.dto.CreateOrderRequest;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class OrderClient extends BaseClient {

    protected static final String ORDERS_PATH = "/orders";

    @Step("Создание заказа")
    public ValidatableResponse createOrder(CreateOrderRequest request) {
        return logRequest().with()
                .body(request)
                .contentType(ContentType.JSON)
                .post(ORDERS_PATH)
                .then()
                .log()
                .all();
    }

    @Step("Получение заказов")
    public ValidatableResponse getOrders() {
        return logRequest().with()
                .get(ORDERS_PATH)
                .then()
                .log()
                .all();
    }
}
