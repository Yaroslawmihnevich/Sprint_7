package com.example;

import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersTest extends BaseTest {

    @Test
    public void getOrdersShouldReturnOk() {
        //when-then
        logRequest().with()
                .get(ORDERS_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log()
                .all()
                .body("orders", notNullValue())
                .body("orders[0]", notNullValue());
    }

}
