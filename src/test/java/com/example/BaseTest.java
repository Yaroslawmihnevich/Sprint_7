package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.BeforeClass;

public class BaseTest {

    protected static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/api/v1/";

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

}
