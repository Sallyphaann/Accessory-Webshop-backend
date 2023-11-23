package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.DeleteProductUseCase;
import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class DeleteProductUseCaseImpl implements DeleteProductUseCase {
    private ProductRepository productRepository;
    @Override
    public void deleteProduct(long productId) {
        this.productRepository.deleteById(productId);

    }
}
