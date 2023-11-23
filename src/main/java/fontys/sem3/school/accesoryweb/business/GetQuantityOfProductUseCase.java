package fontys.sem3.school.accesoryweb.business;

import fontys.sem3.school.accesoryweb.persistence.CategorySumQuality;

import java.util.List;


public interface GetQuantityOfProductUseCase {

    List<CategorySumQuality> getQuantityOfProductByCategory();
}
