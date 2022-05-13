package in.reqres.qa.test.restApi;

import in.reqres.qa.config.ReadProperties;
import in.reqres.qa.test.BaseExtentReportsTest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest extends BaseExtentReportsTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ReadProperties.getInstance().getUrl();
        RestAssured.basePath = "/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Content-type", "application/json")
                .build();
    }
}
