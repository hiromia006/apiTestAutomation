package com.dummy.pojo;

import com.thedeanda.lorem.LoremIpsum;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class DummyApiTest extends BasePojoApiTest {

    @Test
    public void getPostsShouldSucceed() {
        List<Post> posts = given()
                .spec(getCommon())
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .log().body()
                .statusCode(200)
                .body("[0].id", equalTo(1))
                .extract().jsonPath().getList("", Post.class);

        System.out.println("Size : " + posts.size());
        for (Post post : posts) {
            System.out.println(post.getTitle());
            System.out.println(post.getAuthor());
        }
    }


    @Test
    public void doPostShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(3);
        String name = LoremIpsum.getInstance().getName();

//        Post post=new Post(title, name);
        Post post = given()
                .spec(getCommon())
                .body(new Post(title, name))
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
                .extract().jsonPath().getObject("", Post.class);

        System.out.println(post.getAuthor());
        System.out.println(post.getTitle());
        System.out.println(post.getId());
    }

    @Test
    public void getPostShouldDetailSucceed() {
        String title = LoremIpsum.getInstance().getTitle(3);
        String name = LoremIpsum.getInstance().getName();

        int postId = given()
                .spec(getCommon())
                .body(new Post(title, name))
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
                .extract().jsonPath().getObject("", Post.class)
                .getId();

        Post post = given()
                .spec(getCommon())
                .log().uri()
                .when()
                .get("/posts/" + postId)
                .then()
                .log().body()
                .statusCode(200)
                .body("author", equalTo(name))
                .body("title", equalTo(title))
                .body("id", equalTo(postId))
                .extract().jsonPath().getObject("", Post.class);

        Assert.assertEquals(postId, post.getId());
    }


    @Test
    public void putPostShouldDetailSucceed() {
        String title = LoremIpsum.getInstance().getTitle(3);
        String name = LoremIpsum.getInstance().getName();

        int postId = given()
                .spec(getCommon())
                .body(new Post(title,name))
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
                .extract().jsonPath().getObject("", Post.class)
                .getId();

        title = LoremIpsum.getInstance().getTitle(3);
        name = LoremIpsum.getInstance().getName();

        given()
                .spec(getCommon())
                .body(new Post(title, name))
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
}
