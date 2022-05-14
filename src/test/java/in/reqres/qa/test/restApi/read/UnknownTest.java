package in.reqres.qa.test.restApi.read;

import in.reqres.qa.test.restApi.BaseApiTest;
import in.reqres.qa.test.restApi.pojo.read.UnknownData;
import in.reqres.qa.test.restApi.pojo.read.UsersData;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UnknownTest extends BaseApiTest {
    @Test
    public void testUnknownShouldSucceed() {
        given()
                .log().uri()
                .when()
                .get("/unknown")
                .then()
                .log().body()
                .statusCode(200)
                .body("total", equalTo(12));
    }

    @Test
    public void testUnknownDeserializeShouldSucceed() {
        List<UnknownData> unknownData = given()
                .log().uri()
                .when()
                .get("/unknown")
                .then()
                .log().body()
                .statusCode(200)
                .body("total", equalTo(12))
                .extract().jsonPath().getList("data", UnknownData.class);

        System.out.println("Size = " + unknownData.size());

        for (UnknownData usersData : unknownData) {
            System.out.println(usersData.getYear());
        }
    }
}
