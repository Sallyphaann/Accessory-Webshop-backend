package fontys.sem3.school.accesoryweb.persistence;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    boolean existsByName(String name);


    @Query("SELECT p from ProductEntity p WHERE p.name LIKE CONCAT('%', :query, '%')")
    List<ProductEntity> searchProducts(@Param("query") String query);
    @Query("SELECT p from ProductEntity p join p.category c where c.name = :categoryName")
    List<ProductEntity> getProductByCategory(String categoryName);
    @Query("SELECT p.category,(SUM(p.quantity))" +
            "FROM ProductEntity p " +
            "GROUP BY p.category")
    List<Object[]> getCategoryQuantities();









}
