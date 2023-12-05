package com.dummy.publicApi;

import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PostApiTest extends BaseApi {

    @Test
    public void getPostsShouldSucceed() {
        given()
                .spec(getCommon())
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .log().body()
                .statusCode(200)
                .body("[0].author", equalTo("Tracy Collier"))
                .body("[0].id", equalTo(1));
    }

    @Test
    public void doPostShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(3);
        String name = LoremIpsum.getInstance().getName();

        given()
                .spec(getCommon())
                .body(getPost(title, name))
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201)
                .body("author", equalTo(name))
                .body("title", equalTo(title))
                .body("id", notNullValue());
    }

    @Test
    public void getPostShouldDetailSucceed() {
        String title = LoremIpsum.getInstance().getTitle(3);
        String name = LoremIpsum.getInstance().getName();

        int postId = given()
                .spec(getCommon())
                .body(getPost(title, name))
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201)
                .body("author", equalTo(name))
                .body("title", equalTo(title))
                .body("id", notNullValue())
                .extract().jsonPath().getInt("id");

        given()
                .spec(getCommon())
                .log().uri()
                .when()
                .get("/posts/" + postId)
                .then()
                .log().body()
                .statusCode(200)
                .body("author", equalTo(name))
                .body("title", equalTo(title))
                .body("id", equalTo(postId));
    }


    @Test
    public void putPostShouldDetailSucceed() {
        String title = LoremIpsum.getInstance().getTitle(3);
        String name = LoremIpsum.getInstance().getName();

        int postId = given()
                .spec(getCommon())
                .body(getPost(title, name))
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201)
                .body("author", equalTo(name))
                .body("title", equalTo(title))
                .body("id", notNullValue())
                .extract().jsonPath().getInt("id");

        title = LoremIpsum.getInstance().getTitle(3);
        name = LoremIpsum.getInstance().getName();

        given()
                .spec(getCommon())
                .body(getPost(title, name))
                .log().uri()
                .log().body()
                .when()
                .put("/posts/" + postId)
                .then()
                .log().body()
                .statusCode(200)
                .body("author", equalTo(name))
                .body("title", equalTo(title))
                .body("id", equalTo(postId));
    }

    @Test
    public void patchPostShouldDetailSucceed() {
        String title = LoremIpsum.getInstance().getTitle(3);
        String name = LoremIpsum.getInstance().getName();

        int postId = given()
                .spec(getCommon())
                .body(getPost(title, name))
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201)
                .body("author", equalTo(name))
                .body("title", equalTo(title))
                .body("id", notNullValue())
                .extract().jsonPath().getInt("id");


        title = LoremIpsum.getInstance().getTitle(3);
        JSONObject  jsonObject = new JSONObject();
        jsonObject.put("title", title);

        given()
                .spec(getCommon())
                .body(jsonObject.toJSONString())
                .log().uri()
                .log().body()
                .when()
                .patch("/posts/" + postId)
                .then()
                .log().body()
                .statusCode(200)
                .body("author", equalTo(name))
                .body("title", equalTo(title))
                .body("id", equalTo(postId));
    }

    @Test
    public void deletePostShouldDetailSucceed() {
        String title = LoremIpsum.getInstance().getTitle(3);
        String name = LoremIpsum.getInstance().getName();

        int postId = given()
                .spec(getCommon())
                .body(getPost(title, name))
                .log().uri()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201)
                .body("author", equalTo(name))
                .body("title", equalTo(title))
                .body("id", notNullValue())
                .extract().jsonPath().getInt("id");


        given()
                .spec(getCommon())
                .log().uri()
                .log().body()
                .when()
                .delete("/posts/" + postId)
                .then()
                .log().body()
                .statusCode(200);
    }
}
