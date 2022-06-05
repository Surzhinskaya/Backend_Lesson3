package Lesson3;


import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class ExampleTest extends AbstractTest {


    @Test
    void getSpecifyingRequestDataTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "italian")
                .queryParam("maxReadyTime", "15")
                .queryParam("minCarbs", "10")
                .queryParam("maxCarbs", "100")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .formParam("ingredientList","tomato,cheese")
                .when()
                .get(getBaseUrl()+"recipes/715495/information")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "italian")
                .queryParam("maxReadyTime", "5")
                .queryParam("diet", "Gluten Free")
                .queryParam("type", "dessert")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .formParam("ingredientList","tomato,cheese")
                .when()
                .get(getBaseUrl()+"recipes")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "French")
                .queryParam("excludeCuisine", "breakfast")
                .queryParam("includeIngredients", "apple")
                .queryParam("minCarbs", "10")
                .queryParam("maxCarbs", "100")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .formParam("ingredientList","tomato,cheese")
                .when()
                .get(getBaseUrl()+"recipes/665672/information")
                .then()
                .statusCode(200);


        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("titleMatch", "Fast Tiramisu")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .formParam("ingredientList","tomato,cheese")
                .when()
                .get(getBaseUrl()+"recipes/642614/information")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("recipeBoxId", " ")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .formParam("ingredientList","tomato,cheese")
                .when()
                .get(getBaseUrl()+"recipes")
                .then()
                .statusCode(404);



       given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Tiramisu")
                .formParam("ingredientList","3 cups coffee")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "rus")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Tiramisu")
                .formParam("ingredientList","3 cups coffee")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(500);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","$$$$$")
                .formParam("ingredientList","3 cups coffee")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);


        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Fast Apple pie")
                .formParam("ingredientList","3 oz pork shoulder")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Tiramisu")
                .formParam("ingredientList","$$$$$")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

           }


    @Test
    void getResponseData(){
        Response response1 = given()
                .when()
                .get(getBaseUrl()+"recipes/715495/information" +
                        getApiKey());

        Headers allHeaders1 = response1.getHeaders();
        System.out.println("Content-Encoding: " + response1.getHeader("Content-Encoding"));

        System.out.println("StatusLine: " + response1.getStatusLine());

        System.out.println("Code: " + response1.getStatusCode());

        Response response2 = given()
                .when()
                .get(getBaseUrl()+"recipes/665672/information" +
                        getApiKey());

        Headers allHeaders2 = response2.getHeaders();
        System.out.println("Content-Encoding: " + response2.getHeader("Content-Encoding"));
        System.out.println("StatusLine: " + response2.getStatusLine());
        System.out.println("Code: " + response2.getStatusCode());

        Response response3 = given()
                .when()
                .get(getBaseUrl()+"recipes/642614/information" +
                        getApiKey());

        Headers allHeaders3 = response3.getHeaders();
        System.out.println("Content-Encoding: " + response3.getHeader("Content-Encoding"));
        System.out.println("StatusLine: " + response3.getStatusLine());
        System.out.println("Code: " + response3.getStatusCode());


        String cuisine1 = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Tiramisu")
                .formParam("ingredientList","3 cups coffee")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .path("cuisine");

        System.out.println("cuisine: " + cuisine1);


        String confidence1 = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Tiramisu")
                .formParam("ingredientList","3 cups coffee")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then().extract()
                .jsonPath()
                .get("confidence")
                .toString();

        System.out.println("confidence: " + confidence1);

        String cuisine2 = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "rus")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Tiramisu")
                .formParam("ingredientList","3 cups coffee")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .path("cuisine");

        System.out.println("cuisine: " + cuisine2);


        String confidence2 = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "rus")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Tiramisu")
                .formParam("ingredientList","3 cups coffee")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then().extract()
                .jsonPath()
                .get("confidence")
                .toString();

        System.out.println("confidence: " + confidence2);

        String cuisine3 = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .path("cuisine");

        System.out.println("cuisine: " + cuisine3);


        String confidence3 = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","$$$$$")
                .formParam("ingredientList","3 cups coffee")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then().extract()
                .jsonPath()
                .get("confidence")
                .toString();

        System.out.println("confidence: " + confidence3);

        String cuisine4 = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Fast Apple pie")
                .formParam("ingredientList","3 oz pork shoulder")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .path("cuisine");

        System.out.println("cuisine: " + cuisine4);


        String confidence4 = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Fast Apple pie")
                .formParam("ingredientList","3 oz pork shoulder")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then().extract()
                .jsonPath()
                .get("confidence")
                .toString();

        System.out.println("confidence: " + confidence4);


        String cuisine5 = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Tiramisu")
                .formParam("ingredientList","$$$$$")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .path("cuisine");

        System.out.println("cuisine: " + cuisine5);


        String confidence5 = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Tiramisu")
                .formParam("ingredientList","$$$$$")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then().extract()
                .jsonPath()
                .get("confidence")
                .toString();

        System.out.println("confidence: " + confidence5);


    }


    @Test
    void getVerifyingResponseData(){


        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "italian")
                .queryParam("maxReadyTime", "15")
                .queryParam("minCarbs", "10")
                .queryParam("maxCarbs", "100")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .formParam("ingredientList","tomato,cheese")
                .when()
                .get("https://api.spoonacular.com/recipes/715495/information")
                .then()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));


        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "French")
                .queryParam("excludeCuisine", "breakfast")
                .queryParam("includeIngredients", "apple")
                .queryParam("minCarbs", "10")
                .queryParam("maxCarbs", "100")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .formParam("ingredientList","tomato,cheese")
                .when()
                .get("https://api.spoonacular.com/recipes/665672/information")
                .then()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));




        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("titleMatch", "Fast Tiramisu")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .formParam("ingredientList","tomato,cheese")
                .when()
                .get("https://api.spoonacular.com/recipes/642614/information")
                .then()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));

    }





}