package fontys.sem3.school.accesoryweb.business;

import fontys.sem3.school.accesoryweb.domain.CreateProductRequest;
import fontys.sem3.school.accesoryweb.domain.CreateProductResponse;

public interface CreateProductUseCase {
    CreateProductResponse createProduct(CreateProductRequest request);
}
