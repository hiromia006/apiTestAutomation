package in.reqres.qa.test.restApi.write;

import com.thedeanda.lorem.LoremIpsum;
import in.reqres.qa.test.restApi.BaseApiTest;
import in.reqres.qa.test.restApi.pojo.write.UserData;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest extends BaseApiTest {
    @Test
    public void updateUserShouldSucceed() {
        String name = LoremIpsum.getInstance().getTitle(1);
        given()
                .spec(requestSpecification())
                .body(new UserData(name, "leader"))
                .log().uri()
                .log().body()
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .log().body()
                .body("name", equalTo(name))
                .body("job", equalTo("leader"));
    }

    @Test
    public void updateUserPatchShouldSucceed() {
        String name = LoremIpsum.getInstance().getTitle(1);
        given()
                .spec(requestSpecification())
                .body(new UserData(name, "leader"))
                .log().uri()
                .log().body()
                .when()
                .patch("/users/2")
                .then()
                .statusCode(200)
                .log().body()
                .body("name", equalTo(name))
                .body("job", equalTo("leader"));
    }
}
