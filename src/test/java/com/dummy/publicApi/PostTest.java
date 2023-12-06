package com.dummy.publicApi;

import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostTest {
    @Test
    public void getPostsShouldSucceed() {
        given()
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .log().body()
                .statusCode(200);
    }


    @Test
    public void postPostsShouldSucceed() {
        String json = "{\n" +
                "\n" +
                "  \"title\": \"json-server4\",\n" +
                "  \"author\": \"typicode4\"\n" +
                "}";
        given()
                .header("Content-Type", "application/json")
                .body(json)
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201);
    }

    @Test(enabled = false)
    public void postPostsShouldSucceed2() {
        Map<String, Object> json = new HashMap<>();
        json.put("title", "API Automation");
        json.put("author", "wasid");

        given()
                .header("Content-Type", "application/json")
                .body(json.toString())
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201);
    }

    @Test
    public void postPostsShouldSucceed3() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "API Automation");
        jsonObject.put("author", "Wasid");


        given()
                .header("Content-Type", "application/json")
                .baseUri("http://localhost")
                .port(3000)
                .body(jsonObject.toJSONString())
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201);
    }

    @Test
    public void postPostsShouldSucceed4() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", LoremIpsum.getInstance().getTitle(3));
        jsonObject.put("author", LoremIpsum.getInstance().getName());


        given()
                .header("Content-Type", "application/json")

                .baseUri("http://localhost")
                .port(3000)
                .body(jsonObject.toJSONString())
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201);
    }

    @Test
    public void updatePostShouldSucceed4() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", LoremIpsum.getInstance().getTitle(3));
        jsonObject.put("author", LoremIpsum.getInstance().getName());


        given()
                .header("Content-Type", "application/json")
                .body(jsonObject.toJSONString())
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .log().body()
                .when()
                .put("/posts/1")
                .then()
                .log().body()
                .statusCode(200);
    }
}
