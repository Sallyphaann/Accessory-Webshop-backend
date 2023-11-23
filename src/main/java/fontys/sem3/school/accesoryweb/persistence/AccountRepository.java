package fontys.sem3.school.accesoryweb.persistence;

import fontys.sem3.school.accesoryweb.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity,Long> {
    AccountEntity findByUsername(String username);
}
