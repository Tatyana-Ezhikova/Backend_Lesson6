package Lesson_5.service;
import Lesson_5.dto.Category;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {

    @GET ("categories/{id}")
    Call<Category> getCategory (@Path ("id") int id);
}
