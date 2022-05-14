package in.reqres.qa.test.restApi.write;

import in.reqres.qa.test.restApi.BaseApiTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends BaseApiTest {
    @Test
    public void deleteUserShouldSucceed() {
        given()
                .spec(requestSpecification())
                .log().uri()
                .log().body()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }
}
