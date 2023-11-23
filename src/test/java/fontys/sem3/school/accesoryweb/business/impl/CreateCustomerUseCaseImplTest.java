package fontys.sem3.school.accesoryweb.business.impl;


import fontys.sem3.school.accesoryweb.business.exception.EmailAlreadyExisitsException;
import fontys.sem3.school.accesoryweb.domain.CreateCustomerRequest;
import fontys.sem3.school.accesoryweb.domain.CreateCustomerResponse;
import fontys.sem3.school.accesoryweb.persistence.AccountRepository;
import fontys.sem3.school.accesoryweb.persistence.UserRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.RoleEnum;
import fontys.sem3.school.accesoryweb.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseImplTest {
    @Mock
    private  UserRepository userRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private CreateCustomerUseCaseImpl createCustomerUseCase;
    @Test
    void createCustomer_shouldReturnId(){
        CreateCustomerRequest request = CreateCustomerRequest.builder()
                .firstname("Dat")
                .lastname("Phan")
                .email("dat@gmail.com")
                .address("Vietnam")
                .phoneNumber("0617188748")
                .password("123456").build();
        UserEntity newCustomer = UserEntity.builder()
                .firstname("Dat")
                .lastname("Phan")
                .email("dat@gmail.com")
                .phoneNumber("0617188748")
                .address("Vietnam")
                .role(RoleEnum.CUSTOMER).build();
        UserEntity savedCustomer = UserEntity.builder()
                .id(5L)
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .role(RoleEnum.CUSTOMER)
                .build();
        when(userRepository.save(newCustomer)).thenReturn(savedCustomer);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        CreateCustomerResponse actual = createCustomerUseCase.createCustomer(request);
        CreateCustomerResponse expected = CreateCustomerResponse.builder().customerId(5L).build();
        assertEquals(actual,expected);
        verify(passwordEncoder).encode(request.getPassword());
        verify(userRepository).save(newCustomer);
    }
    @Test
    void createCustomer_shouldThrowExceptionBecauseExistsByEmail(){
        CreateCustomerRequest request = CreateCustomerRequest.builder()
                .firstname("Dat")
                .lastname("Phan")
                .email("dat@gmail.com")
                .address("Vietnam")
                .phoneNumber("0617188748")
                .password("123456").build();
        when(userRepository.existsByEmail("dat@gmail.com")).thenReturn(true);
        EmailAlreadyExisitsException actualException = assertThrows(EmailAlreadyExisitsException.class,()->{
            createCustomerUseCase.createCustomer(request);});
        assertEquals("EMAIL_ALREADY_EXISTS",actualException.getReason());

    }
    // test
}