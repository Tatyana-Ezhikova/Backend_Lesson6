package Lesson_5.productTests;

import Lesson_5.dto.Product;
import Lesson_5.enums.CategoryType;
import Lesson_5.service.ProductService;
import Lesson_5.util.RetrofitUtils;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PutNegativeTests {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();
    static int productId;
    static int productPrice;
    static String productTitle;
    static String categoryTitle;


    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }


    @SneakyThrows
    @DisplayName("putProduct негативный тест")
    @Test
    void PutProductNegativeTests() {

        product = new Product()
                .withId(10000)
                .withCategoryTitle(CategoryType.FOOD.getTitle())
                .withPrice((int) (Math.random() * 1000 + 1))
                .withTitle(faker.food().ingredient());

        Response<Product> response = productService
                .putProduct(product).execute();

        assertThat(response.code()).as("Статус не 404").isEqualTo(404);

    }

}