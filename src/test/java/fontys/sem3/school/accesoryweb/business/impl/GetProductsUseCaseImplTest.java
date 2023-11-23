package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.domain.Category;

import fontys.sem3.school.accesoryweb.domain.GetProductsResponse;
import fontys.sem3.school.accesoryweb.domain.Product;

import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetProductsUseCaseImplTest {
    @Mock
    private ProductRepository productRepositoryMock;
    @InjectMocks
    private GetProductsUseCaseImpl getProductsUseCase;
    @Test
    void getProducts_shouldReturnAllProductConverted(){
        ProductEntity diamondRingEntity = ProductEntity.builder().id(1L).name("Diamond Ring").quantity(2).price(1890.0).category(CategoryEntity.builder().id(3L).build()).build();
        ProductEntity goldRingEntity = ProductEntity.builder().id(1L).name("Gold Ring").quantity(2).price(189.0).category(CategoryEntity.builder().id(3L).build()).build();
        when(productRepositoryMock.findAll()).thenReturn(of(diamondRingEntity,goldRingEntity));
        getProductsUseCase = new GetProductsUseCaseImpl(productRepositoryMock);
        GetProductsResponse actualResult = getProductsUseCase.getProducts();
        Product diamondRing = Product.builder().id(1L).name("Diamond Ring").quantity(2).price(1890.0).category(Category.builder().id(3L).build()).build();
        Product goldRing = Product.builder().id(1L).name("Gold Ring").quantity(2).price(189.0).category(Category.builder().id(3L).build()).build();
        GetProductsResponse expectedResult = GetProductsResponse.builder().products(List.of(diamondRing,goldRing)).build();
        assertEquals(expectedResult, actualResult);
        verify(productRepositoryMock).findAll();
    }
    @Test
    void sum(){
        int a = 3 ;
        int b = 5 ;
        int actual = 8;
        int expect = a+b;
        assertEquals(expect,actual);
    }

}