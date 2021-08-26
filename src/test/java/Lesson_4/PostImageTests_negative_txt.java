package Lesson_4;

import Lesson_4.BaseTest;
import com.github.javafaker.Faker;
import img.Endpoints;
import img.Images;
import io.qameta.allure.Feature;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.FileEncodingUtils;

import static io.restassured.RestAssured.given;

@Feature("")
public class PostImageTests_negative_txt extends BaseTest {
    static final String filePath = Images.TXT.path;
    private String uploadedImageId;
    MultiPartSpecification multiPartSpec;
    Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        byte[] fileContent = FileEncodingUtils.getFileContent(filePath);
        multiPartSpec = new MultiPartSpecBuilder(fileContent)
                .controlName("image")
                .build();
    }

    @Test
    void uploadFileTest() {
        given()
                .spec(withAuthReqSpec)
                .expect()
                .when()
                .post(Endpoints.POST_IMAGE_REQUEST)
                .prettyPeek()
                .then()
                .spec(responseSpecNegative);
    }
}