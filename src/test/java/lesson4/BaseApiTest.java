package lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;

public class BaseApiTest {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://api.spoonacular.com";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", "be91867c87be4cce82c4445e0240dc5b")
                .build();

    }

    public String readResourceAsString(String resourceName) {
        String path = getClass().getSimpleName() + FileSystems.getDefault().getSeparator() + resourceName;
//        String path = getClass().getSimpleName() + "/" + resourceName;
        try (InputStream inputStream = getClass().getResourceAsStream(path)) {assert inputStream != null; // если не так, будет брошено рантайм исключение
            byte[] data = inputStream.readAllBytes();
            return new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
