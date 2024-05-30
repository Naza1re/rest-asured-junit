package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification requestSpecifications(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecificationOK200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpecificationError400() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    public static ResponseSpecification responseSpecificationSpec(int status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    public static void installSpecification(RequestSpecification request,ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

}
