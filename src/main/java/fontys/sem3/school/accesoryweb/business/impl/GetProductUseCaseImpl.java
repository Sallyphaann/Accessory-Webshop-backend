package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.GetProductUsecase;
import fontys.sem3.school.accesoryweb.domain.Product;
import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class GetProductUseCaseImpl implements GetProductUsecase {
    private ProductRepository productRepository;
    @Override
    public Optional<Product> getProductId(Long id) {
        return productRepository.findById(id).map(ProductConverter::convert);
    }
}
