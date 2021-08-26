package Lesson_4;

import Lesson_4.BaseTest;
import com.github.javafaker.Faker;
import img.Endpoints;
import img.Images;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static steps.CommonPostRequest.uploadCommonImage;

public class UpdateImageTests_Positive_with_Auth extends BaseTest {
    static final String filePath = Images.POSITIVE.path;
    String deletehash;
    String imgid;
    Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        deletehash = uploadCommonImage(reqSpec).getData().getDeletehash();
    }

    @Test
    void updateImgTest() {
        given()
                .spec(reqSpec)
                .multiPart("title", faker.harryPotter().character())
                .multiPart("description", faker.harryPotter().quote())
                .when()
                .post(Endpoints.GET_IMAGE_REQUEST,deletehash)
                .prettyPeek()
                .then()
                .spec(responseSpecificationUpdate);
    }

    @AfterEach
    void tearDown() {
        given()
                .spec(reqSpec)
                .delete(Endpoints.DELETE_IMAGE_REQUEST, username, deletehash)
                .prettyPeek()
                .then()
                .spec(responseSpecification);
    }
}
