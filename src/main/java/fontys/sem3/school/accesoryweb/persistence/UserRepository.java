package fontys.sem3.school.accesoryweb.persistence;
import fontys.sem3.school.accesoryweb.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByEmail(String email);
    UserEntity findByEmail(String email);

}
