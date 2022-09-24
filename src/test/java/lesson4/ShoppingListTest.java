package lesson4;


import lesson5.api.ApiAddToShoppingListRequest;
import api.ApiUserConnectResult;
import io.restassured.path.json.JsonPath;
import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ShoppingListTest extends BaseApiTest {

    private static ApiUserConnectResult userData;
    private static Integer idForRemove;

    @Test
    @Order(1)
    void connectUser() {
         userData = given()
                .body("{}")
                .expect()
                .log()
                .body()
                .statusCode(200)
                .body("status", is("success"))
                .body("username", notNullValue())
                .body("hash", notNullValue())
                .when()
                .post("/users/connect")
                .as(ApiUserConnectResult.class);
    }

    @Test
    @Order(2)
    void getEmptyItems() {
        String actually = given()
                .pathParams("username", userData.getUsername())
                .queryParam("hash", userData.getHash())
                .expect()
                .when()
                .get("/mealplanner/{username}/shopping-list")
                .asPrettyString();

        String expected = readResourceAsString("getEmptyItem/expected.json"); // прочитали из файла

        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    @Order(3)
    void addItemToShoppingList() {

        ApiAddToShoppingListRequest request = ApiAddToShoppingListRequest.builder()
                .item("1 bag of oranges")
                .aisle("orange")
                .parse(true)
                .build();
        String actually = given()
                .pathParams("username", userData.getUsername())
                .queryParam("hash", userData.getHash())
                .body(request)
                .expect()
                .statusCode(200) // 1-ая проверка
                .when()
                .post("/mealplanner/{username}/shopping-list/items")
                .asPrettyString();

        idForRemove = JsonPath.given(actually).get("id");

        String expected = readResourceAsString("addItemToShoppingList/expected.json");

        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    @Order(4)
    void deleteItem() {
        given()
                .pathParams("username", userData.getUsername())
                .pathParams("id", idForRemove)
                .queryParam("hash", userData.getHash())
                .expect()
                .log()
                .body()
                .when()
                .delete("/mealplanner/{username}/shopping-list/items/{id}");
        getEmptyItems();
    }

}
