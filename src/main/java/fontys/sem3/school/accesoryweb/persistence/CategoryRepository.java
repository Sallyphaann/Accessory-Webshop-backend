package fontys.sem3.school.accesoryweb.persistence;

import fontys.sem3.school.accesoryweb.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryRepository extends JpaRepository<CategoryEntity,Long>{
    boolean existsById(Long id);


}
