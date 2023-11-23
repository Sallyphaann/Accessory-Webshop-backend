package fontys.sem3.school.accesoryweb.business;

import fontys.sem3.school.accesoryweb.domain.GetProductsResponse;
import fontys.sem3.school.accesoryweb.domain.Product;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface GetProductsUseCase {
    GetProductsResponse getProducts();
    List<Product> searchProducts(String query);
    List<Product> filterProductsByCategory(String name);
}
