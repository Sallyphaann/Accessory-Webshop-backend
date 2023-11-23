package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.GetCategoriesUseCase;
import fontys.sem3.school.accesoryweb.domain.Category;
import fontys.sem3.school.accesoryweb.domain.GetCategoriesResponse;
import fontys.sem3.school.accesoryweb.persistence.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetCategoriesUseCaseImpl implements GetCategoriesUseCase {
    private final CategoryRepository categoryRepository;



    @Override
    public GetCategoriesResponse getCategories() {
        List<Category> categories = categoryRepository.findAll().stream().map(CategoryConverter::convert).toList();
        return GetCategoriesResponse.builder()
                .categories(categories).build();
    }
}
