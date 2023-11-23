package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.domain.Category;
import fontys.sem3.school.accesoryweb.domain.GetCategoriesResponse;
import fontys.sem3.school.accesoryweb.persistence.CategoryRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCategoriesUseCaseImplTest {
    @Mock
    private CategoryRepository categoryRepositoryMock;

    @InjectMocks
    private GetCategoriesUseCaseImpl getCategoriesUseCase;
    @Test
    void getCategories_shouldReturnAllCategoriesConverted(){
        CategoryEntity ringEntity = CategoryEntity.builder().id(1L).name("Ring").build();
        CategoryEntity earingEntity = CategoryEntity.builder().id(2L).name("Earing").build();
        when(categoryRepositoryMock.findAll()).thenReturn(List.of(ringEntity,earingEntity));
        getCategoriesUseCase = new GetCategoriesUseCaseImpl(categoryRepositoryMock);
        GetCategoriesResponse actualResult = getCategoriesUseCase.getCategories();
        Category ring = Category.builder().id(1L).name("Ring").build();
        Category earing = Category.builder().id(2L).name("Earing").build();
        GetCategoriesResponse expectedResult = GetCategoriesResponse.builder().categories(List.of(ring, earing)) .build();
        assertEquals(expectedResult, actualResult);
        verify(categoryRepositoryMock).findAll();
    }

}