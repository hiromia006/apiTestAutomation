package com.apingweb.restApi.privateRestApiWithPojo;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetArticlesTest extends BasePrivateRestApiWithPojoTest {
    @Test
    public void getArticleShouldSucceed() {
        List<Article> articles = given()
                .spec(requestSpecification())
                .log().uri()
                .when()
                .get("/articles")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("result", Article.class);

        Assert.assertEquals(articles.get(0).getId(), 2);
        for (Article article : articles) {
            System.out.println(article.getId());

        }

    }
}
