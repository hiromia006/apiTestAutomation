package in.reqres.qa.test.restApi.write;


import com.thedeanda.lorem.LoremIpsum;
import in.reqres.qa.test.restApi.BaseApiTest;
import in.reqres.qa.test.restApi.pojo.write.RegisterData;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegisterTest extends BaseApiTest {

    @Test
    public void testRegisterWithMap() {
        String email = LoremIpsum.getInstance().getEmail();
        String password = LoremIpsum.getInstance().getTitle(1);

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("email", "eve.holt@reqres.in");
        stringObjectMap.put("password", password);

        JSONObject jsonObject = new JSONObject(stringObjectMap);
        System.out.println(jsonObject.toJSONString());

        given()
                .spec(requestSpecification())
                .body(jsonObject.toJSONString())
                .when()
                .post("/register")
                .then()
                .statusCode(200);
    }

    @Test
    public void testRegisterWithJson() {
        String password = LoremIpsum.getInstance().getTitle(1);
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("email", "eve.holt@reqres.in");
        stringObjectMap.put("password", password);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "eve.holt@reqres.in");
        jsonObject.put("password", password);

        System.out.println(jsonObject.toJSONString());

        given()
                .spec(requestSpecification())
                .body(jsonObject.toJSONString())
                .log().uri()
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .and().log().body();
    }

    @Test
    public void testRegisterWithPojo() {
        String password = LoremIpsum.getInstance().getTitle(1);

        given()
                .spec(requestSpecification())
                .body(new RegisterData("eve.holt@reqres.in", password))
                .log().uri()
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .and().log().body();
    }

    @Test
    public void testRegisterShouldFail() {
        String email = LoremIpsum.getInstance().getEmail();

        given()
                .spec(requestSpecification())
                .body(new RegisterData(email))
                .log().uri()
                .log().body()
                .when()
                .post("/register")
                .then()
                .statusCode(400)
                .and().log().body()
                .body("error", equalTo("Missing password"));
    }



}
