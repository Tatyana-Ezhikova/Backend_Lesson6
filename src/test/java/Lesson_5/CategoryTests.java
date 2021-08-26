package Lesson_5;

import Lesson_5.dto.Category;
import Lesson_5.dto.Product;
import Lesson_5.service.CategoryService;
import Lesson_5.util.RetrofitUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;


import java.io.IOException;
import java.util.List;

import static Lesson_5.enums.CategoryType.FOOD;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CategoryTests {

    static CategoryService categoryService;

    @BeforeAll
    static void beforeAll() {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @DisplayName("getCategory позитивный тест")
    @Test
    void getCategoryPositiveTest() throws IOException {

        Response<Category> response = categoryService
                .getCategory(FOOD.getId())
                .execute();

        assertThat(response.body().getId()).as("Id категории не равен 1").isEqualTo(1);
        assertThat(response.body().getTitle()).as("Тип продукта не Food").isEqualTo(FOOD.getTitle());
        assertThat(response.body().getProducts().size()).as("Количество продуктов в категории Food не равно 785").isEqualTo(785);
        assertThat(response.code()).as("Статус не 200").isEqualTo(200);

        List<Product> productsCategory = response.body().getProducts();
        for (Product products : productsCategory) {
            assertThat(products.getCategoryTitle()).as("У продукта категория не Food").isEqualTo("Food");

        }

    }

    @DisplayName("getCategory негативный тест на проверку 404 ошибки")
    @Test
    void getCategoryNegativeTest() throws IOException {

        Response<Category> response = categoryService
                .getCategory(0)
                .execute();
        assertThat(response.errorBody().string());
        assertThat(response.code()).as("Ошибка не 404").isEqualTo(404);
    }
}