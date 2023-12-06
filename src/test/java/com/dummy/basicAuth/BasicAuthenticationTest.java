package com.dummy.basicAuth;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicAuthenticationTest extends BaseBasicAuthenticationTest {

    @Test
    public void getUsersShouldSucceed() {
        given()
                .spec(getSpecification())
                .auth().basic("admin", "12345")
                .log().uri()
                .when()
                .get("/users")
                .then()
                .log().body()
                .statusCode(200);
    }
}
