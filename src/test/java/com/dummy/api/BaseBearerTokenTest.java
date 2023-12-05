package com.dummy.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseBearerTokenTest {

    RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("https://apingweb.com")
                .setBasePath("/api")
                .addHeader("Content-Type", "application/json")
                .build();
    }

    RequestSpecification getSpecificationWithToken() {
        return new RequestSpecBuilder()
                .addRequestSpecification(getSpecification())
                .addHeader("Authorization", "Bearer " + getBearerToken())
                .build();
    }

    public String getBearerToken() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("email", "batch7@gmail.com");
        jsonObject.put("password", "123456");

        return given()
                .spec(getSpecification())
                .body(jsonObject.toJSONString())
//                .log().uri()
//                .log().body()
                .when()
                .post("/login")
                .then()
//                .log().body()
                .statusCode(200)
                .extract().jsonPath().getString("token");
    }
}
