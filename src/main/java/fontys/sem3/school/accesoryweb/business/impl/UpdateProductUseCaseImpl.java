package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.CategoryIdValidator;
import fontys.sem3.school.accesoryweb.business.UpdateProductUseCase;
import fontys.sem3.school.accesoryweb.business.exception.InvalidProductException;
import fontys.sem3.school.accesoryweb.domain.UpdateProductRequest;
import fontys.sem3.school.accesoryweb.persistence.CategoryRepository;
import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryIdValidator categoryIdValidator;
    @Override
    public void updateProduct(UpdateProductRequest request) {
        Optional<ProductEntity> productOptional = productRepository.findById(request.getId());
        if (productOptional.isEmpty()) {
            throw new InvalidProductException("PRODUCT_ID_INVALID");
        }

      categoryIdValidator.validateId(request.getCategoryId());

        ProductEntity product = productOptional.get();
        updateFields(request, product);

    }
    private void updateFields(UpdateProductRequest request, ProductEntity product) {
        CategoryEntity categoryEntity = categoryRepository.findById(request.getCategoryId()).orElseThrow();
        product.setCategory(categoryEntity);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        productRepository.save(product);
    }
}
