package com.example;

import com.example.dto.CreateCourierRequest;
import io.restassured.http.ContentType;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(JUnitParamsRunner.class)
public class CreateCourierTest extends BaseTest {

    private final String login = "suppperrrrrrrr";

    private final String password = "sippperrrdsaddrr";

    @Test
    public void createCourierShouldReturnCreated() {
        //given
        String firstName = "firstttttrrrsaddrr";

        //when-then
        logRequest().with()
                .body(new CreateCourierRequest(login, password, firstName))
                .contentType(ContentType.JSON)
                .post(COURIER_PATH)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_CREATED)
                .body("ok", equalTo(true));

        clearCourier(login, password);
    }

    @Test
    @Parameters({
            "login12312414, ",
            ", login12312414",
            ", ",
    })
    public void createCourierShouldReturnBadRequestWhenSomeFieldsNotPassed(String login, String password) {
        //when-then
        logRequest().with()
                .body(new CreateCourierRequest(login, password, "firstName"))
                .contentType(ContentType.JSON)
                .post(COURIER_PATH)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void createCourierShouldReturnConflictWhenCourierAlreadyExists() {
        //given
        String firstName = "firstName";
        createCourier(login, password, firstName);

        //when-then
        logRequest().with()
                .body(new CreateCourierRequest(login, password, firstName))
                .contentType(ContentType.JSON)
                .post(COURIER_PATH)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_CONFLICT);

        clearCourier(login, password);
    }

}
