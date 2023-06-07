package com.apingweb.restApi.privateRestApiWithPojo;

import com.thedeanda.lorem.LoremIpsum;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateArticleTest extends BasePrivateRestApiWithPojoTest {
    @Test
    public void updateArticleShouldSucceed() {
        String tittle = LoremIpsum.getInstance().getTitle(2);
        String body = LoremIpsum.getInstance().getParagraphs(1, 1);
        String image = LoremIpsum.getInstance().getUrl();

        given()
                .spec(requestSpecification())
                .body(new CreateArticle(tittle, body, image))
                .log().uri()
                .log().body()
                .when()
                .put("/article/edit/91")
                .then()
                .statusCode(200)
                .log().body();

    }
}
