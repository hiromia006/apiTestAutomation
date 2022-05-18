package in.reqres.qa.test.restApi.read;


import in.reqres.qa.test.restApi.BaseApiTest;
import in.reqres.qa.test.restApi.pojo.read.UsersDelayData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UsersDelayTest extends BaseApiTest {

    @Test
    public void testUsersDelay() {
        List<UsersDelayData> usersDelayDatas = given()
                .spec(requestSpecification())
                .log().uri()
                .when()
                .get("/users?delay=3")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getList("data", UsersDelayData.class);

        for (UsersDelayData usersDelayData : usersDelayDatas) {
            Assert.assertTrue(usersDelayData.getEmail().contains("@") && usersDelayData.getEmail().endsWith("in"));
        }
    }
}
