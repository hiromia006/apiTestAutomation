package com.jsonServer.restApi.write;

import com.jsonServer.restApi.BasePublicApiTest;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StudentTest extends BasePublicApiTest {
    @Test
    public void createStudentUsingMapShouldSucceed() {
        String studentName = LoremIpsum.getInstance().getName();
        Map json = new HashMap<>();
        json.put("name", studentName);
        json.put("courseId", 2);

        given()
                .header("Content-Type", "application/json")
                .body(json)
                .log().uri()
                .log().body()
                .when()
                .post("/students")
                .then()
                .statusCode(201)
                .log().body()
                .body("name", equalTo(studentName))
                .body("id", notNullValue())
                .body("courseId", equalTo(2));
    }

    @Test
    public void createStudentUsingJsonShouldSucceed() {
        String studentName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", studentName);
        jsonObject.put("courseId", 2);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/students")
                .then()
                .statusCode(201)
                .log().body()
                .body("name", equalTo(studentName))
                .body("id", notNullValue())
                .body("courseId", equalTo(2));
    }


    @Test
    public void replaceStudentUsingJsonShouldSucceed() {
        String studentName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", studentName);
        jsonObject.put("courseId", 2);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .put("/students/5")
                .then()
                .statusCode(200)
                .log().body()
                .body("name", equalTo(studentName))
                .body("id", notNullValue())
                .body("id", equalTo(5))
                .body("courseId", equalTo(2));
    }

    @Test
    public void updateStudentUsingJsonShouldSucceed() {
        String studentName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", studentName);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .patch("/students/4")
                .then()
                .statusCode(200)
                .log().body()
                .body("name", equalTo(studentName))
                .body("id", notNullValue())
                .body("id", equalTo(4))
                .body("courseId", equalTo(2));
    }

    @Test
    public void deleteStudentUsingJsonShouldSucceed() {

        given()
                .log().uri()
                .when()
                .delete("/students/4")
                .then()
                .statusCode(200)
                .log().body();
    }
}
