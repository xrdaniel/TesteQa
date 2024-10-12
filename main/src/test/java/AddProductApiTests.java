import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AddProductApiTests {

    @Test
    public void testAddProduct() {
        RestAssured.baseURI = "https://dummyjson.com";

        String requestBody = """
            {
                "title": "Perfume Oil",
                "description": "Mega Discount, Impression of A...",
                "price": 13,
                "discountPercentage": 8.4,
                "rating": 4.26,
                "stock": 65,
                "brand": "Impression of Acqua Di Gio",
                "category": "fragrances",
                "thumbnail": "https://i.dummyjson.com/data/products/11/thumnail.jpg"
            }
        """;

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/products/add")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo("Perfume Oil"))
                .body("description", equalTo("Mega Discount, Impression of A..."))
                .body("price", equalTo(13))
                .body("stock", equalTo(65))
                .body("category", equalTo("fragrances"))
                .body("thumbnail", equalTo("https://i.dummyjson.com/data/products/11/thumnail.jpg"));
    }
}
