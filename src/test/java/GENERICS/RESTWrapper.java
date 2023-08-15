package GENERICS;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RESTWrapper {

  public static Response getResponse(String baseURI, String basePath, Map<String,String> headerInfo,String requestBody)
  {
      return  given()
              .baseUri(baseURI)
              .basePath(basePath)
              .headers(headerInfo)
              .filter(new AllureRestAssured())
              .log()
              .body()
              .when()
              .get()
              .then()
              .log()
              .all()
              .assertThat()
              .statusCode(200)
              .extract().response();

  }

    public static Response postResponse(String baseURI, String basePath,String requestBody)
    {
        return  given()
                .baseUri(baseURI)
                .basePath(basePath)
                .headers("Content-Type", "text/xml")
                .filter(new AllureRestAssured())
                .body(requestBody)
                .log()
                .body()
                .when()
                .post()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .extract().response();

    }


}
