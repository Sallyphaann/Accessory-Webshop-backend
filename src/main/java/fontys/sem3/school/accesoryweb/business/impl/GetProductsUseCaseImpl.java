package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.GetProductsUseCase;
import fontys.sem3.school.accesoryweb.domain.GetProductsResponse;
import fontys.sem3.school.accesoryweb.domain.Product;
import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@AllArgsConstructor
public class GetProductsUseCaseImpl implements GetProductsUseCase {
    private ProductRepository productRepository;

    @Override
    public GetProductsResponse getProducts() {
        List<Product> products = productRepository.findAll().stream().map(ProductConverter::convert).toList();
        return GetProductsResponse.builder()
                .products(products).build();
    }

    @Override
    public List<Product> searchProducts(String query) {
        return productRepository.searchProducts(query).stream().map(ProductConverter::convert).toList();

    }

    @Override
    public List<Product> filterProductsByCategory(String category) {
        return productRepository.getProductByCategory(category).stream().map(ProductConverter::convert).toList();
    }


}
