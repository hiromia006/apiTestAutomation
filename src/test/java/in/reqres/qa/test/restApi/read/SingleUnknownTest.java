package in.reqres.qa.test.restApi.read;

import in.reqres.qa.test.restApi.BaseApiTest;
import in.reqres.qa.test.restApi.pojo.read.UnknownData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static in.reqres.qa.util.General.GET_RESPONSE_SUCCESS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SingleUnknownTest extends BaseApiTest {
    @Test
    public void testSingleUnknownShouldSucceed() {
        given()
                .spec(requestSpecification())
                .when()
                .get("/unknown/2")
                .then()
                .statusCode(GET_RESPONSE_SUCCESS)
                .log().body()
                .body("data.id", equalTo(2))
                .body("data.year", equalTo(2001));
    }

    @Test
    public void testSingleUnknownDeserializeShouldSucceed() {
        UnknownData unknownData = given()
                .spec(requestSpecification())
                .log().uri()
                .when()
                .get("/unknown/2")
                .then()
                .statusCode(GET_RESPONSE_SUCCESS)
                .log().body()
                .extract().jsonPath().getObject("data", UnknownData.class);

        System.out.println(unknownData.getYear());
    }


}
