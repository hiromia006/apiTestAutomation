package in.reqres.qa.test.restApi.write;

import in.reqres.qa.test.restApi.BaseApiTest;
import in.reqres.qa.test.restApi.pojo.write.RegisterData;
import org.testng.annotations.Test;

import static in.reqres.qa.util.General.GET_RESPONSE_SUCCESS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginTest extends BaseApiTest {
    @Test
    public void loginShouldSucceed() {
        given()
                .spec(requestSpecification())
                .body(new RegisterData("eve.holt@reqres.in", "cityslicka"))
                .log().uri()
                .log().body()
                .when()
                .post("/login")
                .then()
                .statusCode(GET_RESPONSE_SUCCESS)
                .log().body()
                .body("token", notNullValue());
    }

    @Test
    public void loginShouldFail() {
        given()
                .spec(requestSpecification())
                .body(new RegisterData("cityslicka"))
                .log().uri()
                .log().body()
                .when()
                .post("/login")
                .then()
                .statusCode(400)
                .log().body()
                .body("error", equalTo("Missing password"));
    }
}
