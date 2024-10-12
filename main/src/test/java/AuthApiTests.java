import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class AuthApiTests {

    @Test
    public void testUserLogin() {
        RestAssured.baseURI = "https://dummyjson.com";

        String response = given()
                .header("Content-Type", "application/json")
                .body("{ \"username\": \"emilys\", \"password\": \"emilyspass\" }")
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        System.out.println("Response: " + response);
    }
}
