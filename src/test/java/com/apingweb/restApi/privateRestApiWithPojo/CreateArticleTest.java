package com.apingweb.restApi.privateRestApiWithPojo;

import com.thedeanda.lorem.LoremIpsum;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateArticleTest extends BasePrivateRestApiWithPojoTest {

    @Test
    public void createArticleShouldSucceed() {
        String tittle = LoremIpsum.getInstance().getTitle(2);
        String body = LoremIpsum.getInstance().getParagraphs(1, 1);
        String image = LoremIpsum.getInstance().getUrl();
        given()
                .spec(requestSpecification())
                .body(new CreateArticle(tittle, body, image))
                .log().uri()
                .log().body()
                .when()
                .post("article/create")
                .then()
                .statusCode(200)
                .log().body();
    }


    @Test
    public void createArticleOnlyTittleShouldFail() {
        String tittle = LoremIpsum.getInstance().getTitle(2);

        given()
                .spec(requestSpecification())
                .body(new CreateArticle(tittle, null, null))
                .log().uri()
                .log().body()
                .when()
                .post("article/create")
                .then()
                .statusCode(400)
                .body("status", equalTo(400))
                .body("message", equalTo("Invalid input"))
                .log().body();

    }


    @Test
    public void createArticleOnlyBodyShouldFail() {
        String body = LoremIpsum.getInstance().getParagraphs(1, 1);

        given()
                .spec(requestSpecification())
                .body(new CreateArticle(null, body, null))
                .log().uri()
                .log().body()
                .when()
                .post("article/create")
                .then()
                .statusCode(400)
                .body("status", equalTo(400))
                .body("message", equalTo("Invalid input"))
                .log().body();

    }

    @Test
    public void createArticleShouldFail() {

        given()
                .spec(requestSpecification())
                .body(new CreateArticle(null, null, null))
                .log().uri()
                .log().body()
                .when()
                .post("article/create")
                .then()
                .statusCode(400)
                .body("status", equalTo(400))
                .body("message", equalTo("Invalid input"))
                .log().body();

    }
}
