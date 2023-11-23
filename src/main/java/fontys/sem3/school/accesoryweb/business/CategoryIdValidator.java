package fontys.sem3.school.accesoryweb.business;

import fontys.sem3.school.accesoryweb.business.exception.InvalidCategoryException;

public interface CategoryIdValidator {
    void validateId(Long categoryId) throws InvalidCategoryException;
}
