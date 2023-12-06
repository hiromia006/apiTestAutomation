package com.dummy.api;

import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class BearerTokenTest extends BaseBearerTokenTest {
    @Test
    public void loginShouldSucceed() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("email", "batch7@gmail.com");
        jsonObject.put("password", "123456");

        given()
                .spec(getSpecification())
                .body(jsonObject.toJSONString())
                .log().uri()
                .log().body()
                .when()
                .post("/login")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(enabled = false)
    public void registerShouldSucceed() {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", LoremIpsum.getInstance().getName());
        jsonObject.put("email", "batch7@gmail.com");
        jsonObject.put("password", "123456");
        jsonObject.put("password_confirmation", "123456");
        jsonObject.put("phone", "5555551234");


        given()
                .spec(getSpecification())
                .body(jsonObject.toJSONString())
                .log().uri()
                .log().body()
                .when()
                .post("/register")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void getArticlesShouldSucceed() {
        given()
                .spec(getSpecificationWithToken())
                .log().uri()
                .when()
                .get("/articles")
                .then()
                .log().body()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1))
                .body("success", equalTo(true))
                .body("message", equalTo("Success"))
                .body("result[0].id", equalTo(2));
    }

    @Test
    public void getArticleDetailShouldSucceed() {
        int articleId = given()
                .spec(getSpecificationWithToken())
                .log().uri()
                .when()
                .get("/articles")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1))
                .body("success", equalTo(true))
                .body("message", equalTo("Success"))
                .body("result[0].id", equalTo(2))
                .extract().jsonPath().getInt("result[0].id");

        given()
                .spec(getSpecificationWithToken())
                .log().uri()
                .when()
                .get("/article/" + articleId)
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void createArticleShouldSucceed() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", LoremIpsum.getInstance().getTitle(4));
        jsonObject.put("body", LoremIpsum.getInstance().getParagraphs(1, 1));
        jsonObject.put("picture", "https://example.com/" + LoremIpsum.getInstance().getName() + ".png");


        given()
                .spec(getSpecificationWithToken())
                .body(jsonObject.toJSONString())
                .log().uri()
                .log().body()
                .when()
                .post("/article/create")
                .then()
                .log().body()
                .statusCode(200)
                .body("status", equalTo(200))
//                .body("errors", emptyArray())
                .body("message", equalTo("Article has been created"));
    }

    @Test
    public void updateArticleShouldSucceed() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", LoremIpsum.getInstance().getTitle(4));
        jsonObject.put("body", LoremIpsum.getInstance().getParagraphs(1, 1));
        jsonObject.put("picture", "https://example.com/" + LoremIpsum.getInstance().getName() + ".png");


        given()
                .spec(getSpecificationWithToken())
                .body(jsonObject.toJSONString())
                .log().uri()
                .log().body()
                .when()
                .put("/article/edit/1")
                .then()
                .log().body()
                .statusCode(200)
                .body("status", equalTo(200));
    }
}
