package lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
//import java.util.ArrayList;
//import java.util.List;
import java.util.stream.Stream;

import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static org.hamcrest.Matchers.*;

public class ComplexSearchApiTest  extends BaseApiTest {

    @Test
    void searchSpanishFoodTest() {
        String actually = RestAssured.given() //request спецификация
                .param("number",3)
                .param("cuisine","Spanish")
                .log()
                .uri()
                .expect()//переход к проверкам
                .statusCode(200) // 1-ая проверка
                .time(lessThanOrEqualTo(3000L)) // 2-ая проверка
                .body("totalResults", is(27)) // is - экземпляр matcher (сопоставитель). Проверяет равно ли totalResults 27
                .body("results", hasSize(3))
                 .when() // переход к запросу
                .get("/recipes/complexSearch")
                .body()
                .prettyPrint();  // возвращает json

        String expected = readResourceAsString("expected.json"); // прочитали из файла

        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

//    @ParameterizedTest
//    void testImageRecognize() {
//        String dir = getClass().getSimpleName() + FileSystems.getDefault().getSeparator();
//        String fileName1 = dir + "1.jpg";
//        URL resource1 = getClass().getResource(fileName1);
//        assert resource1 != null;
//        String path1 = resource1.getFile();
//        File image1 = new File(path1);
//        RestAssured.given()
//                    .multiPart(image1)
//                    .expect()
//                    .statusCode(200)
//                    .body("category", is("paella"))
//                    .body("probability", greaterThan(0.5f))
//                    .when()
//                    .post("/food/images/classify");
//    }

    @ParameterizedTest
    @MethodSource("resources1")
    void testImageRecognize1(String image) {
          RestAssured.given()
//                  .log()
//                  .all()
                  .param("imageUrl", image)
                  .expect()
                  .statusCode(200)
                  .body("status", is("success"))
                  .body("category", is("burger"))
                  .body("probability", greaterThan(0.6f))
//                  .log()
//                  .all()
                  .when()
                  .get("/food/images/classify");
    }

    public static Stream<Arguments> resources1() {
        Arguments f1 = Arguments.of("https://cdn.discordapp.com/icons/525976020919123981/f2ccc3ec3e36988bfa65da0bdae715c8.jpg");
        Arguments f2 = Arguments.of("https://burger-king-kupon.ru/wp-content/uploads/2022/03/1648284144_48dc525c690ab68339a7226c1087654a.png");
        Arguments f3 = Arguments.of("https://bigoven-res.cloudinary.com/image/upload/t_recipe-256/hanger-steak-sandwich-with-bourbon-creamed-spinach-2204420.jpg");
        return Stream.of(f1, f2, f3);
    }

//   /  сепаратор на Линуксе и на Маке
//   \\ сепаратор на Винде

}
