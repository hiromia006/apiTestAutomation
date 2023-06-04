package com.jsonServer.restApi;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class BasePublicApiTest {

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
        RestAssured.basePath = "";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
