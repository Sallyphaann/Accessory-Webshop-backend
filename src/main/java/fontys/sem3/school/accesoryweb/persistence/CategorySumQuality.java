package fontys.sem3.school.accesoryweb.persistence;

import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategorySumQuality {
    private CategoryEntity category;
    private Long quantity;


}
