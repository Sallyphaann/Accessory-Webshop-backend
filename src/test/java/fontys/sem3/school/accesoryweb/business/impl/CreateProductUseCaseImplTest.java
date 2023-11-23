package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.CategoryIdValidator;
import fontys.sem3.school.accesoryweb.business.exception.NameAlreadyExistsException;
import fontys.sem3.school.accesoryweb.domain.CreateProductRequest;
import fontys.sem3.school.accesoryweb.domain.CreateProductResponse;
import fontys.sem3.school.accesoryweb.persistence.CategoryRepository;
import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateProductUseCaseImplTest {
    @Mock
    private ProductRepository mockProductRepository;
    @Mock
    private CategoryRepository mockCategoryRepository;
    @Mock
    private CategoryIdValidator mockCategoryIdValidator;
    @InjectMocks
    private CreateProductUseCaseImpl createProductUseCase;
    @Test
    void createProduct_shouldReturnProductId(){
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .id(1L)
                .name("Hat").build();
        CreateProductRequest request = CreateProductRequest.builder()
                .name("red hat")
                .price(225.5)
                .quantity(2)
                .categoryId(categoryEntity.getId()).build();
        when(mockCategoryRepository.findById(request.getCategoryId())).thenReturn(Optional.of(categoryEntity));
        ProductEntity newProduct = ProductEntity.builder()
                .name("red hat")
                .price(225.5)
                .quantity(2)
                .category(categoryEntity).build();
        // this line using to create fake data for product
        ProductEntity savedProduct = ProductEntity.builder()
                .id(3L)
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(categoryEntity).build();
        when(mockProductRepository.existsByName("red hat")).thenReturn(false);
        when(mockProductRepository.save(newProduct)).thenReturn(savedProduct);
        CreateProductResponse actual = createProductUseCase.createProduct(request);
        CreateProductResponse expected = CreateProductResponse.builder().productId(3L).build();
        assertEquals(actual,expected);
        verify(mockCategoryIdValidator).validateId(request.getCategoryId());
        verify(mockProductRepository).existsByName("red hat");
        verify(mockProductRepository).save(newProduct);

    }



    @Test
    void createProduct_shouldThrowExceptionBecauseProductWasExist(){
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .id(1L)
                .name("Hat").build();
        ProductEntity product = ProductEntity.builder().id(1L).name("red hat")
                .price(25.25)
                .quantity(5)
                .category(categoryEntity).build();
        CreateProductRequest request = CreateProductRequest.builder()
                .name("red hat")
                .price(250d)
                .quantity(4)
                .categoryId(1L)
                .build();
        when(mockProductRepository.existsByName("red hat")).thenReturn(true);
        NameAlreadyExistsException actualException = assertThrows(NameAlreadyExistsException.class,()->
        {createProductUseCase.createProduct(request);});
        assertEquals("NAME_ALREADY_EXISTS",actualException.getReason());
    }

}