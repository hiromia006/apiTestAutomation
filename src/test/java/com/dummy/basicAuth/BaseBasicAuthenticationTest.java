package com.dummy.basicAuth;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseBasicAuthenticationTest {
    RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("https://apingweb.com")
                .setBasePath("/api/auth/")
                .addHeader("Content-Type", "application/json")
                .build();
    }
}
