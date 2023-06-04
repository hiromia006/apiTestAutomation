package com.apingweb.restApi.bearerToken;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseAuthorizationBearerTokenTest {
    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "https://apingweb.com";
        RestAssured.basePath = "/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    public String getBearerToken() {
        Map map = new HashMap();
        map.put("email", "superman@gmail.com");
        map.put("password", "123456");

        return given()
                .contentType(ContentType.JSON)
                .body(map)
                .log().uri()
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

    }

    public RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + getBearerToken())
                .addHeader("Content-Type", "application/json")
                .build();

    }
}
