package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.school.accesoryweb.domain.AccessToken;
import fontys.sem3.school.accesoryweb.domain.User;
import fontys.sem3.school.accesoryweb.persistence.UserRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.RoleEnum;
import fontys.sem3.school.accesoryweb.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCustomerUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private AccessToken requestAccessToken;
    @InjectMocks
    private GetCustomerUseCaseImpl getCustomerUseCase;

    @Test
    void getCustomerById_shouldReturnCustomer(){
        Long customerId = 1L;
        UserEntity userEntity = UserEntity.builder()
                    .id(customerId)
                    .firstname("Sally")
                    .lastname("Phan")
                    .email("sally@gmail.com")
                    .address("Utrecht")
                    .phoneNumber("06178962523")
                    .role(RoleEnum.ADMIN)
                    .build();

        User expectedUser = User.builder()
                    .id(userEntity.getId())
                    .firstname(userEntity.getFirstname())
                    .lastname(userEntity.getLastname())
                    .email(userEntity.getEmail())
                    .address(userEntity.getAddress())
                    .phoneNumber(userEntity.getPhoneNumber())
                    .build();

            when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
            when(userRepository.findById(customerId)).thenReturn(Optional.of(userEntity));
            Optional<User> result = getCustomerUseCase.getCustomerById(customerId);
            assertEquals(expectedUser, result.get());
        }


    @Test
    void getCustomerById_shouldThrowCustomerIdNotFromLoggedInUser() {
        Long customerId = 2L;
        when(requestAccessToken.getUserId()).thenReturn(3L);
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(false);
        String message = "CUSTOMER_ID_NOT_FROM_LOGGED_IN_USER";
        UnauthorizedDataAccessException actualException = assertThrows(UnauthorizedDataAccessException.class, () -> {
            getCustomerUseCase.getCustomerById(customerId);

        });
        assertEquals(message
                ,actualException.getReason());


    }

}