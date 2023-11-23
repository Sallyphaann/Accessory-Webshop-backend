package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.domain.Category;
import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;

final class CategoryConverter {
    private CategoryConverter(){

    }
    public static Category convert(CategoryEntity category){
        return Category.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

    }

}
