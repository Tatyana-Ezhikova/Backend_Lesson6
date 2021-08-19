package steps;

import dto.PostImageResponse;
import img.Endpoints;
import img.Images;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import utils.FileEncodingUtils;

import static io.restassured.RestAssured.given;
@UtilityClass
public class CommonPostRequest {

    private static final String INPUT_IMAGE_FILE_PATH = Images.POSITIVE.path;

    public static PostImageResponse uploadCommonImage(RequestSpecification spec) {
        RequestSpecification multiPart = spec
                .multiPart(
                        new MultiPartSpecBuilder(FileEncodingUtils.getFileContent(INPUT_IMAGE_FILE_PATH))
                                .controlName("image")
                                .build());
        return given()
                .spec(multiPart)
                .when()
                .post(Endpoints.POST_IMAGE_REQUEST)
                .prettyPeek()
                .then()
                .extract()
                .body()
                .as(PostImageResponse.class);
    }
}