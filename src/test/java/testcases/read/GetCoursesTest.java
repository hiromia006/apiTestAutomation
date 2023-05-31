package testcases.read;

import com.jsonSever.testcases.BasePublicApiTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetCoursesTest extends BasePublicApiTest {
    @Test(timeOut = 2000)
    public void getCoursesShouldSucceed() {
        given()
                .log().uri()
                .when()
                .get("/courses")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(timeOut = 2000)
    public void getCourseShouldSucceed() {
        given()
                .log().uri()
                .when()
                .get("/courses/2")
                .then()
                .statusCode(200)
                .log().body();
    }
}
