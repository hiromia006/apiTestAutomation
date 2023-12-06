package com.dummy.publicApi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class BaseApi {

    public RequestSpecification getCommon() {
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(3000)
                .addHeader("Content-Type", "application/json")
                .build();

    }

    public String getPost(String title, String author) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("author", author);

        return jsonObject.toJSONString();
    }
}
