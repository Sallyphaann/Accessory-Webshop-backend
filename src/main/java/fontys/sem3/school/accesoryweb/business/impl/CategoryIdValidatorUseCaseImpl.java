package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.CategoryIdValidator;
import fontys.sem3.school.accesoryweb.business.exception.InvalidCategoryException;
import fontys.sem3.school.accesoryweb.persistence.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryIdValidatorUseCaseImpl implements CategoryIdValidator {
    private final CategoryRepository categoryRepository;
    @Override
    public void validateId(Long categoryId) {
        if (categoryId == null || !categoryRepository.existsById(categoryId))
        {
            throw new InvalidCategoryException();

        }




    }
}
