package com.apingweb.restApi.basicAuth;

import com.thedeanda.lorem.LoremIpsum;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicAuthenticationApiTest extends BaseBasicAuthTest {
    @Test
    public void getUsersListShouldSucceed() {
        given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "12345")
                .log().uri()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .log().body()
                .body("success", equalTo(true))
                .body("message", equalTo("Success"));
    }

    @Test
    public void getUserDetailShouldSucceed() {
        int userId = given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "12345")
                .log().uri()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Success"))
                .extract().jsonPath().getInt("data[0].user_id");


        given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "12345")
                .log().uri()
                .when()
                .get("/user/{userId}", userId)
                .then()
                .statusCode(200)
                .log().body()
                .body("success", equalTo(true))
                .body("message", equalTo("Success"));
    }

    @Test
    public void createUserShouldSucceed() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", LoremIpsum.getInstance().getEmail());
        jsonObject.put("name", LoremIpsum.getInstance().getName());
        jsonObject.put("age", 30);
        jsonObject.put("image", "https://example.com/batman.png");


        given()
                .auth().basic("admin", "12345")
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/user/create")
                .then()
                .statusCode(200)
                .log().body()
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .body("message", equalTo("Success"));
    }

    @Test
    public void updateUserShouldSucceed() {
        int userId = given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "12345")
                .log().uri()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Success"))
                .extract().jsonPath().getInt("data[0].user_id");

        Map mapJson = new HashMap();
        mapJson.put("email", LoremIpsum.getInstance().getEmail());
        mapJson.put("name", LoremIpsum.getInstance().getName());
        mapJson.put("age", 30);
        mapJson.put("image", "https://example.com/batman.png");


        given()
                .auth().basic("admin", "12345")
                .contentType(ContentType.JSON)
                .body(mapJson)
                .log().uri()
                .log().body()
                .when()
                .put("/user/edit/{userId}", userId)
                .then()
                .statusCode(200)
                .log().body()
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .body("message", equalTo("Success"));
    }


    @Test
    public void deleteUserShouldSucceed() {
        int userId = given()
                .contentType(ContentType.JSON)
                .auth().basic("admin", "12345")
                .log().uri()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Success"))
                .extract().jsonPath().getInt("data[0].user_id");


        given()
                .auth().basic("admin", "12345")
                .contentType(ContentType.JSON)
                .log().uri()
                .log().body()
                .when()
                .delete("/user/delete/{userId}", userId)
                .then()
                .statusCode(200)
                .log().body();
    }
}
