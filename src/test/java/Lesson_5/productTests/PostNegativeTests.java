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

public class PostNegativeTests {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withCategoryTitle(CategoryType.FOOD.getTitle())
                .withPrice((int) (Math.random() * 1000 + 1))
                .withTitle(faker.food().ingredient())
                .withId((int) (Math.random() * 1000 + 1));
    }

    @SneakyThrows
    @DisplayName("postProduct негативный тест")
    @Test
    void postProductNegativeTest() {

        Response<Product> response = productService
                .postProduct(product).execute();

        assertThat(response.code()).as("Статус не 400").isEqualTo(400);
    }

}
