package com.example.service;

import com.example.client.CourierClient;
import io.qameta.allure.Step;

public class CourierService {

    private final CourierClient client;

    public CourierService(CourierClient client) {
        this.client = client;
    }

    @Step("Авторизация с последующим удалением курьера")
    public void clearCourier(String login, String password) {
        Long id = loginCourier(login, password);
        client.deleteCourier(id);
    }

    @Step("Авторизация курьера с возвращением его идентификатора")
    public Long loginCourier(String login, String password) {
        return client.loginCourier(login, password)
                .extract()
                .body()
                .jsonPath()
                .getLong("id");
    }
}
