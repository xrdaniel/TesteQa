import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserApiTests {

    @Test
    public void testGetUsers() {
        RestAssured.baseURI = "https://dummyjson.com";

        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("users", not(empty()))
                .body("users[0].id", notNullValue())
                .body("users[0].firstName", notNullValue())
                .body("users[0].email", containsString("@"));
    }
}
