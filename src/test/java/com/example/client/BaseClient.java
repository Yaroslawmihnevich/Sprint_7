package com.example.client;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseClient {

    protected RequestSpecification logRequest() {
        return given().log().all();
    }


}
