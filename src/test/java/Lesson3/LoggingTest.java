package Lesson3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoggingTest extends AbstractTest {

    @BeforeAll
    static void setUp(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @Test
    void getRequestLogTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .log().method()
                .log().params()
                .when()
                .get("https://api.spoonacular.com/recipes/715495/information");

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++=");

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
                .log().all()
                .when()
                .get("https://api.spoonacular.com/recipes/665672/information");

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++=");
    }

    @Test
    void getResponseLogTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("titleMatch", "Fast Tiramisu")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .formParam("ingredientList","tomato,cheese")
                .when()
                .get("https://api.spoonacular.com/recipes/642614/information")
                .prettyPeek();

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++=");
    }

    @Test
    void getErrorTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/715495/information")
                .then().statusCode(400);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++=");

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/665672/information")
                .then().statusCode(400);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++=");

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/642614/information")
                .then().statusCode(400);
    }
}
