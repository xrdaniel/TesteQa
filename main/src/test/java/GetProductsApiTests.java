import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetProductsApiTests {

    @Test
    public void testGetProducts() {
        RestAssured.baseURI = "https://dummyjson.com";

        given()
                .header("Content-Type", "application/json")
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("products[0].price", greaterThan(0.0f)); // Verifica se o preço do primeiro produto é maior que 0
    }
}
