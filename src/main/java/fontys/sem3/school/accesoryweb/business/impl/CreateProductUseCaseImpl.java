package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.CategoryIdValidator;
import fontys.sem3.school.accesoryweb.business.CreateProductUseCase;
import fontys.sem3.school.accesoryweb.business.exception.NameAlreadyExistsException;
import fontys.sem3.school.accesoryweb.domain.CreateProductRequest;
import fontys.sem3.school.accesoryweb.domain.CreateProductResponse;
import fontys.sem3.school.accesoryweb.persistence.CategoryRepository;
import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class CreateProductUseCaseImpl  implements CreateProductUseCase {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryIdValidator categoryIdValidator;

    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new NameAlreadyExistsException();
        }

        categoryIdValidator.validateId(request.getCategoryId());

        ProductEntity savedProduct = saveNewProduct(request);

        return CreateProductResponse.builder()
                .productId(savedProduct.getId())
                .build();
    }
    private ProductEntity saveNewProduct(CreateProductRequest request) {
        CategoryEntity categoryEntity = categoryRepository.findById(request.getCategoryId()).orElseThrow();
        ProductEntity newProduct = ProductEntity.builder()
                .category(categoryEntity)
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
        return productRepository.save(newProduct);
    }
}
