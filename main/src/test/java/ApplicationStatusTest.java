import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApplicationStatusTest {

    @Test
    public void testGetStatus() {
        RestAssured.baseURI = "https://dummyjson.com";

        given()
                .when()
                .get("/test")
                .then()
                .statusCode(200) // Espera que o código de status seja 200
                .body("status", equalTo("ok")) // Verifica se o campo "status" tem valor "ok"
                .body("method", equalTo("GET")); // Verifica se o campo "method" tem valor "GET"
    }
}
