import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductApiTests {

    @Test
    public void testGetProductsWithValidToken() {
        RestAssured.baseURI = "https://dummyjson.com";
        String validToken = "/* YOUR_VALID_TOKEN_HERE */";

        given()
                .header("Authorization", "Bearer " + validToken)
                .when()
                .get("/auth/products")
                .then()
                .statusCode(200) // Verifica se o código de status é 200 para um token válido
                .body("products", not(empty())) // Verifica se há produtos retornados
                .body("products[0].id", notNullValue()); // Verifica se o primeiro produto tem um ID
    }

    @Test
    public void testGetProductsWithInvalidToken() {
        RestAssured.baseURI = "https://dummyjson.com";
        String invalidToken = "invalidToken123";

        given()
                .header("Authorization", "Bearer " + invalidToken)
                .when()
                .get("/auth/products")
                .then()
                .statusCode(401) // Verifica se o código de status é 401 para um token inválido
                .body("message", equalTo("Invalid/Expired Token!")); // Verifica a mensagem de erro
    }

    @Test
    public void testGetProductsWithoutToken() {
        RestAssured.baseURI = "https://dummyjson.com";

        given()
                .when()
                .get("/auth/products")
                .then()
                .statusCode(403) // Verifica se o código de status é 403 para ausência de token
                .body("message", equalTo("Authentication Problem")); // Verifica a mensagem de erro
    }
}
