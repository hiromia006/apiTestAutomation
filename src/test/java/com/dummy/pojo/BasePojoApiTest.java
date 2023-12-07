package com.dummy.pojo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BasePojoApiTest {
    public RequestSpecification getCommon() {
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(3000)
                .addHeader("Content-Type", "application/json")
                .build();

    }
}
