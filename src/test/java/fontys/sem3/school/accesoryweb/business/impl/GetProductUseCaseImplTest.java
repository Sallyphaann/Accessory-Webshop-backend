package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.domain.Product;
import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetProductUseCaseImplTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private GetProductUseCaseImpl getProductUseCase;

    @Test
    void getProductById_shouldReturnProduct(){
        Long productId = 1L;
        CategoryEntity category = CategoryEntity.builder()
                .id(1L)
                .name("hat").build();
        ProductEntity productEntity = ProductEntity.builder()
                .id(productId)
                .name("Blue Hat")
                .price(10.0)
                .quantity(5)
                .category(category)
                .build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        Optional<Product> result = getProductUseCase.getProductId(productId);
        assertEquals(productId, result.get().getId());
        verify(productRepository).findById(productId);


    }

}