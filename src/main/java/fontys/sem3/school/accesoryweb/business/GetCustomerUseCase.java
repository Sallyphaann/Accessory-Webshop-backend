package fontys.sem3.school.accesoryweb.business;

import fontys.sem3.school.accesoryweb.domain.User;

import java.util.Optional;

public interface GetCustomerUseCase {

    Optional<User> getCustomerById(Long id);
}
