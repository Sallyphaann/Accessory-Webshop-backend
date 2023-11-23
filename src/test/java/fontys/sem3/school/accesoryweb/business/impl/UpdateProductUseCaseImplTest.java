package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.CategoryIdValidator;
import fontys.sem3.school.accesoryweb.business.exception.InvalidProductException;
import fontys.sem3.school.accesoryweb.domain.Product;
import fontys.sem3.school.accesoryweb.domain.UpdateProductRequest;
import fontys.sem3.school.accesoryweb.persistence.CategoryRepository;
import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.webjars.NotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateProductUseCaseImplTest {
    @Mock
    private ProductRepository  productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryIdValidator categoryIdValidator;
    @InjectMocks
    private UpdateProductUseCaseImpl updateProductUseCase;
    @Test
    void updateProduct_shouldReturnSuccess(){

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .id(1L)
                .name("Hat").build();
        UpdateProductRequest request = UpdateProductRequest.builder()
                .id(1L)
                .name("yellow hat")
                .price(30.00)
                .quantity(2)
                .categoryId(categoryEntity.getId()).build();
        ProductEntity product = ProductEntity.builder()
                .id(1L)
                .name("red hat")
                .price(25.25)
                .quantity(5)
                .category(categoryEntity).build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));
        ProductEntity updatedProduct = ProductEntity.builder().id(1L).name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(categoryEntity).build();

        updateProductUseCase.updateProduct(request);

        verify(productRepository).save(updatedProduct);
    }
    @Test
    void updatedProduct_shouldThrowExceptionBecauseCannotFindProduct(){

        UpdateProductRequest request = UpdateProductRequest.builder().id(1L).build();
        when(productRepository.findById(request.getId())).thenReturn(Optional.empty());
        String message = "PRODUCT_ID_INVALID";
        InvalidProductException actualException = assertThrows(InvalidProductException.class, () -> {
            updateProductUseCase.updateProduct(request);
        });
        assertEquals(message
                ,actualException.getReason());
//test

    }


}