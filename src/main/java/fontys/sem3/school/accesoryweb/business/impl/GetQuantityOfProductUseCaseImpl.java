package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.GetQuantityOfProductUseCase;
import fontys.sem3.school.accesoryweb.persistence.CategorySumQuality;
import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetQuantityOfProductUseCaseImpl implements GetQuantityOfProductUseCase {
    private ProductRepository productRepository;



    @Override
    public List<CategorySumQuality> getQuantityOfProductByCategory() {
        List<CategorySumQuality> categoryQuantities = new ArrayList<>();
        List<Object[]> results = productRepository.getCategoryQuantities();
        for (Object[] result : results) {
            CategoryEntity category = (CategoryEntity) result[0];
            Long quantity = (Long) result[1];
            CategorySumQuality categorySumQuality = new CategorySumQuality(category, quantity);
            categoryQuantities.add(categorySumQuality);
        }
        return categoryQuantities;
    }
}
