package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.domain.Category;
import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryConverterTest {
    @Test
    void convert_shouldReturnConvertedCategory(){
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .id(1L)
                .name("red hat").build();
        Category expectedCategory = Category.builder().id(1L)
                .name("red hat").build();

        Category convertedCategory = CategoryConverter.convert(categoryEntity);
        assertEquals(expectedCategory,convertedCategory);
    }

}