package com.apingweb.restApi.basicAuth;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class BaseBasicAuthTest {
    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "https://apingweb.com";
        RestAssured.basePath = "/api/auth/";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
