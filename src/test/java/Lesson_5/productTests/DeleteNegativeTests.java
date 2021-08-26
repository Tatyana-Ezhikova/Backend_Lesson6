package Lesson_5.productTests;

import Lesson_5.service.ProductService;
import Lesson_5.util.RetrofitUtils;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeleteNegativeTests {
    static ProductService productService;
    static int productId=10000;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }


    @DisplayName("deleteProduct негативный тест")
    @Test
    void DeleteProductTests() {

        try {
            Response<ResponseBody> response =
                    productService.deleteProduct(productId)
                            .execute();
            assertThat(response.errorBody().string());
            assertThat(response.code()).as("Статус ответа не 204").isEqualTo(204);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}