package in.reqres.qa.test.restApi.read;


import in.reqres.qa.test.restApi.BaseApiTest;
import in.reqres.qa.test.restApi.pojo.UsersData;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class ListUsersTest extends BaseApiTest {
    @Test
    public void testListUsers() {
        given()
                .log().uri()
                .when()
                .get("/users?page=2")
                .then()
                .log().body()
                .statusCode(200)
                .body("total", equalTo(12));
    }


    @Test
    public void testListUsers2() {
        List<UsersData> usersDatas = given()
                .log().uri()
                .when()
                .get("/users?page=2")
                .then()
//                .log().body()
                .statusCode(200)
                .body("total", equalTo(12))
                .extract().jsonPath().getList("data", UsersData.class);

        System.out.println("Size = " + usersDatas.size());

        for (UsersData usersData : usersDatas) {
            System.out.println(usersData.getAvatar());
        }
    }
}
