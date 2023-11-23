package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.persistence.CategorySumQuality;
import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetQuantityOfProductUseCaseImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private GetQuantityOfProductUseCaseImpl getQuantityOfProductUseCase;
    @Test
    void testGetQuantityOfProductByCategory() {
        // Mock the repository method
        CategoryEntity category1 = CategoryEntity.builder().id(1L).name("Category 1").build();
        CategoryEntity category2 = CategoryEntity.builder().id(2L).name("Category 2").build();
        Object[] result1 = {category1, 10L};
        Object[] result2 = {category2, 5L};
        List<Object[]> results = Arrays.asList(result1, result2);
        when(productRepository.getCategoryQuantities()).thenReturn(results);

        // Invoke the method
        List<CategorySumQuality> categoryQuantities = getQuantityOfProductUseCase.getQuantityOfProductByCategory();

        // Verify the repository method invocation
        // (optional if not required for this test case)

        // Verify the result
        assertEquals(2, categoryQuantities.size());

        CategorySumQuality quantity1 = categoryQuantities.get(0);
        assertEquals(category1, quantity1.getCategory());
        assertEquals(10L, quantity1.getQuantity());

        CategorySumQuality quantity2 = categoryQuantities.get(1);
        assertEquals(category2, quantity2.getCategory());
        assertEquals(5L, quantity2.getQuantity());
    }

}