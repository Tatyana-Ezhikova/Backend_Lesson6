package Lesson_5.productTests;

import Lesson_5.dto.Product;
import Lesson_5.service.ProductService;
import Lesson_5.util.RetrofitUtils;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetNegativeTests {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();
    static int productId=10000;


    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }


    @SneakyThrows
    @DisplayName("getProduct негативный тест")
    @Test
    void GetProductTests() {
        Response<Product> response = productService
                .getProduct(productId).execute();
        assertThat(response.code()).as("Статус ответа не 404").isEqualTo(404);
        assertThat(response.errorBody().string());

    }
}
