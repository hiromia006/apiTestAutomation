package com.apingweb.restApi.privateRestApiWithPojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateArticle {
    @JsonProperty("title")
    public String title;

    @JsonProperty("body")
    public String body;

    @JsonProperty("picture")
    public String picture;


    public CreateArticle(String title, String body, String picture) {
        this.title = title;
        this.body = body;
        this.picture = picture;
    }
}