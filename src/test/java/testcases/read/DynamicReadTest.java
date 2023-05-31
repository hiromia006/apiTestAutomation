package testcases.read;

import com.jsonSever.testcases.BasePublicApiTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicReadTest extends BasePublicApiTest {
    @Test(timeOut = 2000)
    public void getCourseShouldSucceed() {
        int courseId = given()
                .log().uri()
                .when()
                .get("/courses")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        given()
                .log().uri()
                .when()
                .get("/courses/{courseId}", courseId)
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(courseId));
    }


    @Test
    public void getStudentShouldSucceed() {
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
                .when()
                .get("/students/{studentId}", studentId)
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(1));
    }
}
