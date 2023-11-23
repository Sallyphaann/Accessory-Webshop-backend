package fontys.sem3.school.accesoryweb.business;

import fontys.sem3.school.accesoryweb.domain.Product;

import java.util.Optional;

public interface GetProductUsecase {
    Optional<Product> getProductId(Long id);
}
