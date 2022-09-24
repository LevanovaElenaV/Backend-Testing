package lesson4;

//import api.ApiSearchResult;
import api.ApiSearchResult;
import io.restassured.RestAssured;
import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.stream.Stream;

import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static org.hamcrest.Matchers.*;

public class ComplexSearchApiTest  extends BaseApiTest {

    @Test
    void testSearchBread() {

//        LinkedHashMap actually = RestAssured.given()
        ApiSearchResult actually = RestAssured.given()
                .param("number", 3)
                .param("limitLicense", true)
                .param("query", "bread")
                .log()
                .uri()
                .expect()
                .statusCode(200)
//                .time(lessThanOrEqualTo(1500L))
                .body("totalResults", is(175))
                .body("results", hasSize(3))
                .body("results[0].title", is("Bread Omlette"))
                .log()
                .body()
                .when()
                .get("/recipes/complexSearch")
                .body()
//                .as(LinkedHashMap.class);
                .as(ApiSearchResult.class);

//              actually.getResults().get(0).getImage() и т.д. - RestAshured умеет работать с POJO - десериализация?

        String expected = readResourceAsString("expected.json"); // прочитали из файла

        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @ParameterizedTest
    @MethodSource("resources")
    void testImageBurgerRecognize1(String image) {
          RestAssured.given()
                  .param("imageUrl", image)
                  .expect()
                  .statusCode(200)
                  .body("status", is("success"))
                  .body("category", is("burger"))
                  .body("probability", greaterThan(0.6f))
                  .when()
                  .get("/food/images/classify");
    }

    public static Stream<Arguments> resources() {
        Arguments f1 = Arguments.of("https://cdn.discordapp.com/icons/525976020919123981/f2ccc3ec3e36988bfa65da0bdae715c8.jpg");
        Arguments f2 = Arguments.of("https://burger-king-kupon.ru/wp-content/uploads/2022/03/1648284144_48dc525c690ab68339a7226c1087654a.png");
        Arguments f3 = Arguments.of("https://bigoven-res.cloudinary.com/image/upload/t_recipe-256/hanger-steak-sandwich-with-bourbon-creamed-spinach-2204420.jpg");
        return Stream.of(f1, f2, f3);
    }


}
