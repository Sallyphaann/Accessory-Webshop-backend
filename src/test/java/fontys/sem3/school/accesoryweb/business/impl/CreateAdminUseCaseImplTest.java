package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.domain.CreateAdminRequest;
import fontys.sem3.school.accesoryweb.domain.CreateAdminResponse;
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
class CreateAdminUseCaseImplTest {
    @Mock
    private UserRepository mocKUserRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private CreateAdminUseCaseImpl createAdminUseCase;

    @Test
    void testCreateAdmin() {

        CreateAdminRequest request = CreateAdminRequest.builder()
                .firstname("Dat")
                .lastname("Phan")
                .email("dat@gmail.com")
                .address("Vietnam")
                .phoneNumber("0617188748")
                .password("123456").build();
        UserEntity newAdmin = UserEntity.builder()
                .firstname("Dat")
                .lastname("Phan")
                .email("dat@gmail.com")
                .phoneNumber("0617188748")
                .address("Vietnam")
                .role(RoleEnum.ADMIN).build();

        UserEntity savedAdmin = UserEntity.builder()
                .id(1L)
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .role(RoleEnum.ADMIN)
                .build();
        when(mocKUserRepository.save(newAdmin)).thenReturn(savedAdmin);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        CreateAdminResponse actual = createAdminUseCase.createAdmin(request);
        CreateAdminResponse expected = CreateAdminResponse.builder().adminId(1L).build();
        assertEquals(actual,expected);
        verify(passwordEncoder).encode(request.getPassword());
        verify(mocKUserRepository).save(newAdmin);
    }
}