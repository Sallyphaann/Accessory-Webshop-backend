package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.GetCustomerUseCase;
import fontys.sem3.school.accesoryweb.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.school.accesoryweb.domain.AccessToken;
import fontys.sem3.school.accesoryweb.domain.User;
import fontys.sem3.school.accesoryweb.persistence.UserRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class GetCustomerUseCaseImpl implements GetCustomerUseCase {
    private UserRepository userRepository;
    private AccessToken requestAccessToken;


    @Override
    public Optional<User> getCustomerById(Long customerId) {
        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name()) && !requestAccessToken.getUserId().equals(customerId)) {
            throw new UnauthorizedDataAccessException("CUSTOMER_ID_NOT_FROM_LOGGED_IN_USER");
        }
        /*if(!requestAccessToken.hasRole(RoleEnum.ADMIN.name())){
            if(!requestAccessToken.getUserId().equals(customerId)
            )
            {

            }
        }*/
        return userRepository.findById(customerId).map(CustomerConverter::convert);
    }

}
