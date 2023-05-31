package testcases.write;

import com.jsonSever.testcases.BasePublicApiTest;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class DynamicStudentTest extends BasePublicApiTest {
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
    public void replaceStudentUsingJsonShouldSucceed() {
        int studentId = given()
                .log().uri()
                .when()
                .get("/students")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

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
                .put("/students/{studentId}", studentId)
                .then()
                .statusCode(200)
                .log().body()
                .body("name", equalTo(studentName))
                .body("id", notNullValue())
                .body("id", equalTo(studentId))
                .body("courseId", equalTo(2));
    }


    @Test
    public void updateStudentUsingJsonShouldSucceed() {
        int studentId = given()
                .log().uri()
                .when()
                .get("/students")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        String studentName = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", studentName);

        given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .patch("/students/{studentId}", studentId)
                .then()
                .statusCode(200)
                .log().body()
                .body("name", equalTo(studentName))
                .body("id", notNullValue())
                .body("id", equalTo(studentId));
    }

    @Test
    public void deleteStudentUsingJsonShouldSucceed() {
        int studentId = given()
                .log().uri()
                .when()
                .get("/students")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        given()
                .log().uri()
                .log().body()
                .when()
                .patch("/students/{studentId}", studentId)
                .then()
                .statusCode(200)
                .log().body();
    }
}
