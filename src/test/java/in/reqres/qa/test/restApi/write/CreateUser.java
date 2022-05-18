package in.reqres.qa.test.restApi.write;

import com.thedeanda.lorem.LoremIpsum;
import in.reqres.qa.test.restApi.BaseApiTest;
import in.reqres.qa.test.restApi.pojo.write.UserData;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUser extends BaseApiTest {

    @Test
    public void createUserShouldSucceed() {
        Map<String, Object> stringObjectMa = new HashMap<>();
        stringObjectMa.put("name", LoremIpsum.getInstance().getTitle(1));
        stringObjectMa.put("job", "leader");
        System.out.println(stringObjectMa);

        given()
                .spec(requestSpecification())
                .body(stringObjectMa.toString())
                .log().uri()
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().body()
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));
    }

    @Test
    public void patchUserShouldSucceed() {
        String name = LoremIpsum.getInstance().getTitle(1);
        Map<String, Object> stringObjectMa = new HashMap<>();
        stringObjectMa.put("name", name);
        stringObjectMa.put("job", "leader");
        System.out.println(stringObjectMa.toString());
        JSONObject jsonObject = new JSONObject(stringObjectMa);

        given()
                .spec(requestSpecification())
                .body(jsonObject.toJSONString())
                .log().uri()
                .log().body()
                .when()
                .post("/users/2")
                .then()
                .statusCode(200)
                .log().body()
                .body("name", equalTo(name))
                .body("job", equalTo("leader"));

    }


    @Test
    public void patchUserShouldSucceed2() {
        String name = LoremIpsum.getInstance().getTitle(1);

        given()
                .spec(requestSpecification())
                .body(new UserData(name, "leader"))
                .log().uri()
                .log().body()
                .when()
                .post("/users/2")
                .then()
                .statusCode(200)
                .log().body()
                .body("name", equalTo(name))
                .body("job", equalTo("leader"));

    }
}
