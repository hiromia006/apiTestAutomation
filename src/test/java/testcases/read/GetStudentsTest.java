package testcases.read;

import com.jsonSever.testcases.BasePublicApiTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetStudentsTest extends BasePublicApiTest {
    @Test
    public void getStudentsShouldSucceed() {
        given()
                .log().uri()
                .when()
                .get("/students")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void getStudentShouldSucceed() {
        given()
                .log().uri()
                .when()
                .get("/students/1")
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(1));
    }
}
