package com.apingweb.restApi.bearerToken;

import com.thedeanda.lorem.LoremIpsum;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthorizationBearerTokenTest extends BaseAuthorizationBearerTokenTest {
    @Test
    public void getArticlesShouldSucceed() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getBearerToken())
                .log().uri()
                .when()
                .get("articles")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void getArticlesShouldSucceed2() {
        given()
                .spec(requestSpecification())
                .log().uri()
                .when()
                .get("articles")
                .then()
                .statusCode(200)
                .log().body();
    }


    @Test
    public void getArticleShouldSucceed() {
        int articleId = given()
                .spec(requestSpecification())
                .log().uri()
                .when()
                .get("articles")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("result[0].id");

        given()
                .spec(requestSpecification())
                .log().uri()
                .when()
                .get("article/{articleId}", articleId)
                .then()
                .statusCode(200)
                .log().body();
    }


    @Test
    public void createArticleShouldSucceed() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", LoremIpsum.getInstance().getTitle(1));
        jsonObject.put("body", LoremIpsum.getInstance().getParagraphs(1, 1));
        jsonObject.put("picture", LoremIpsum.getInstance().getUrl());

        given()
                .spec(requestSpecification())
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("article/create")
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Article has been created"))
                .log().body();
    }
}
