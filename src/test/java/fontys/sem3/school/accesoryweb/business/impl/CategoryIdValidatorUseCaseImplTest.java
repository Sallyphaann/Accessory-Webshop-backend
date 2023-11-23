package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.exception.InvalidCategoryException;
import fontys.sem3.school.accesoryweb.persistence.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryIdValidatorUseCaseImplTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryIdValidatorUseCaseImpl categoryIdValidatorUseCase;

    @Test
    void validateId_validId_noExceptionThrown() {
        Long categoryId = 1L;
        when(categoryRepository.existsById(categoryId)).thenReturn(true);
        categoryIdValidatorUseCase.validateId(categoryId);
    }
    @Test
    void validateId_nullId_throwInvalidCategoryException() {
        Long categoryId = null;
        assertThrows(InvalidCategoryException.class, () -> categoryIdValidatorUseCase.validateId(categoryId));
    }

    @Test
    void validateId_invalidId_throwInvalidCategoryException() {
        Long categoryId = 2L;
        when(categoryRepository.existsById(categoryId)).thenReturn(false);
        assertThrows(InvalidCategoryException.class, () -> categoryIdValidatorUseCase.validateId(categoryId));
    }
}