package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.domain.Category;
import fontys.sem3.school.accesoryweb.domain.Product;
import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductConverterTest {
    @Test
    void convert_shouldReturnConvertedProduct(){
        CategoryEntity category = CategoryEntity.builder().id(1L).name("Hat").build();
        Category convertedCategory = CategoryConverter.convert(category);
        ProductEntity productEntity = ProductEntity.builder()
                .id(1L)
                .name("red hat")
                .price(25.25)
                .quantity(10)
                .category(category).build();
        Product expectedProduct = Product.builder()
                .id(1L)
                .name("red hat")
                .price(25.25)
                .quantity(10)
                .category(convertedCategory).build();
        Product convertedProduct = ProductConverter.convert(productEntity);
        assertEquals(expectedProduct,convertedProduct);
    }
}