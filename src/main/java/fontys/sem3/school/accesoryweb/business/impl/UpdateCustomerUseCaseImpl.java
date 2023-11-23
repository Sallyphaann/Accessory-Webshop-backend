package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.UpdateCustomerUseCase;
import fontys.sem3.school.accesoryweb.business.exception.InvalidCustomerException;
import fontys.sem3.school.accesoryweb.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.school.accesoryweb.domain.AccessToken;
import fontys.sem3.school.accesoryweb.domain.UpdateCustomerRequest;
import fontys.sem3.school.accesoryweb.persistence.UserRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.RoleEnum;
import fontys.sem3.school.accesoryweb.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {
    private final UserRepository userRepository;
    private AccessToken requestAccessToken;
    @Override
    public void updateCustomer(UpdateCustomerRequest request) {
        Optional<UserEntity> userOptional = userRepository.findById(request.getId());
        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name())) {
            if (!userOptional.isPresent() || !Objects.equals(requestAccessToken.getUserId(), userOptional.get().getId())) {
                throw new UnauthorizedDataAccessException("CUSTOMER_ID_NOT_FROM_LOGGED_IN_USER");
            }
        }
        if(userOptional.isEmpty()){
            throw new InvalidCustomerException("CUSTOMER_ID_INVALID");
        }
        UserEntity user = userOptional.get();
        updateFields(request, user);


    }




    private void updateFields(UpdateCustomerRequest request, UserEntity customer){
        customer.setFirstname(request.getFirstname());
        customer.setLastname(request.getLastname());
        customer.setAddress(request.getAddress());
        customer.setPhoneNumber(request.getPhoneNumber());
    userRepository.save(customer);
    }
}
