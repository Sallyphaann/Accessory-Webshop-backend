package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.exception.InvalidCustomerException;
import fontys.sem3.school.accesoryweb.business.exception.UnauthorizedDataAccessException;
import fontys.sem3.school.accesoryweb.domain.AccessToken;
import fontys.sem3.school.accesoryweb.domain.UpdateCustomerRequest;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCustomerUseCaseImplTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private AccessToken requestAccessToken;
    @InjectMocks
    private UpdateCustomerUseCaseImpl updateCustomerUseCase;

    @Test
    void updateCustomer_shouldReturnSuccess(){
        UpdateCustomerRequest request = UpdateCustomerRequest.builder()
                .id(1L)
                .firstname("Sally")
                .lastname("Phan")
                .address("Utrecht")
                .phoneNumber("0123456789")
                .build();

        UserEntity user = UserEntity.builder()
                .id(1L)
                .firstname("Diem")
                .lastname("Diem")
                .email("Diem@gmail.com")
                .address("Eindhoven")
                .phoneNumber("987456321")
                .role(RoleEnum.ADMIN)
                .build();
        when(mockUserRepository.findById(1L)).thenReturn(Optional.of(user));
        when(requestAccessToken.getUserId()).thenReturn(1L);
        UserEntity updatedUser = UserEntity.builder()
                .id(1L)
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .address(request.getAddress())
                .email("Diem@gmail.com")
                .role(RoleEnum.ADMIN)
                .phoneNumber(request.getPhoneNumber())
                .build();
        updateCustomerUseCase.updateCustomer(request);
        verify(mockUserRepository).save(updatedUser);





    }
    @Test
    void updateCustomer_shouldThrowInValidId(){
        UpdateCustomerRequest request = UpdateCustomerRequest.builder()
                .id(1L)
                .firstname("Sally")
                .lastname("Phan")
                .address("Utrecht")
                .phoneNumber("0123456789")
                .build();
        when(mockUserRepository.findById(request.getId())).thenReturn(Optional.empty());
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        String message = "CUSTOMER_ID_INVALID";
        InvalidCustomerException actualException = assertThrows(InvalidCustomerException.class,()->{
            updateCustomerUseCase.updateCustomer(request);
        });
        assertEquals(message,actualException.getReason());


        }
    @Test
    void updateCustomer_shouldThrowCustomerIdNotFromLoggedInUser() {
        UpdateCustomerRequest request = UpdateCustomerRequest.builder()
                .id(1L)
                .firstname("Sally")
                .lastname("Phan")
                .address("Utrecht")
                .phoneNumber("0123456789")
                .build();
        UserEntity user = UserEntity.builder()
                .id(1L)
                .firstname("Diem")
                .lastname("Diem")
                .email("Diem@gmail.com")
                .address("Eindhoven")
                .phoneNumber("987456321")
                .role(RoleEnum.ADMIN)
                .build();
        when(mockUserRepository.findById(request.getId())).thenReturn(Optional.of(user));
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(false);
        String message = "CUSTOMER_ID_NOT_FROM_LOGGED_IN_USER";
        UnauthorizedDataAccessException actualException = assertThrows(UnauthorizedDataAccessException.class, () -> {
            updateCustomerUseCase.updateCustomer(request);
        });
        assertEquals(message, actualException.getReason());
    }
}