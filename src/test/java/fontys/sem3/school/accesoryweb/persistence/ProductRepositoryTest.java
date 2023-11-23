package fontys.sem3.school.accesoryweb.persistence;

import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void testGetProductByCategory() {
        createTestProduct(25.25, 1, "Earing");
        createTestProduct(36.36, 8, "neck");
        ProductEntity expectedProductOne = createTestProduct(26.26, 1, "Hat");
        ProductEntity expectedProducTwo = createTestProduct(52.52, 9, "Hat");
        List<ProductEntity> actual = productRepository.getProductByCategory("Hat");
        List<ProductEntity> expected = List.of(expectedProductOne, expectedProducTwo);
        assertEquals(expected, actual); }

    private ProductEntity createTestProduct(Double price,int quantity,String categoryName) {
        CategoryEntity category = createCategoryIfNeeded(categoryName);
        return entityManager.merge( ProductEntity.builder()
                        .name("yellow earing")
                        .price(price)
                        .quantity(quantity)
                        .category(category)
                        .build());
    }
    private CategoryEntity createCategoryIfNeeded(String categoryName) {
        return entityManager.createQuery("from CategoryEntity  where name = :name", CategoryEntity.class)
                .setParameter("name", categoryName)
                .getResultStream()
                .findFirst()
                .orElseGet(() -> entityManager.merge(
                        CategoryEntity.builder()
                                .name(categoryName)
                                .build()));
    }
}