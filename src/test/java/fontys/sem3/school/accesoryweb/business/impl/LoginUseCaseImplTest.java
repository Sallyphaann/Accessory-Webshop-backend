package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.AccessTokenEncoder;
import fontys.sem3.school.accesoryweb.business.exception.InvalidCredentialsException;
import fontys.sem3.school.accesoryweb.domain.LoginRequest;
import fontys.sem3.school.accesoryweb.domain.LoginResponse;
import fontys.sem3.school.accesoryweb.persistence.AccountRepository;
import fontys.sem3.school.accesoryweb.persistence.UserRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.AccountEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.RoleEnum;
import fontys.sem3.school.accesoryweb.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AccessTokenEncoder accessTokenEncoder;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    void login_shouldReturnAccessToken(){
        LoginRequest request = LoginRequest.builder()
                .email("Sally@gmail.com")
                .password("123456")
                .build();
        AccountEntity accountEntity = AccountEntity.builder()
                .id(1L)
                .username(request.getEmail())
                .password("encodedPassword")
                .build();
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .firstname("Sally")
                .lastname("Phan")
                .address("Utrecht")
                .phoneNumber("0623232333")
                .email("Sally@gmail.com")
                .role(RoleEnum.CUSTOMER)
                .build();
        when(userRepository.findByEmail(request.getEmail())).thenReturn(userEntity);
        when(accountRepository.findByUsername(request.getEmail())).thenReturn(accountEntity);
        when(passwordEncoder.matches(request.getPassword(),accountEntity.getPassword())).thenReturn(true);
        when(accessTokenEncoder.encode(any())).thenReturn("accessToken");
        LoginResponse expected = LoginResponse.builder().accessToken("accessToken").build();
        LoginResponse actual = loginUseCase.login(request);
        assertEquals(expected,actual);
        verify(accountRepository).findByUsername(request.getEmail());
        verify(userRepository).findByEmail(request.getEmail());
        verify(passwordEncoder).matches(request.getPassword(), accountEntity.getPassword());
        verify(accessTokenEncoder).encode(any());
    }
    @Test
    void login_shouldThrowCredentialsException_becauseUserIsNull(){
        LoginRequest request = LoginRequest.builder()
                .email("Sally@gmail.com")
                .password("123456")
                .build();
        when(userRepository.findByEmail(request.getEmail())).thenReturn(null);
        assertThrows(InvalidCredentialsException.class,()->loginUseCase.login(request));
    }
    @Test
    void login_shouldThrowException_becauseDidNotMatchPasssword(){
        LoginRequest request = LoginRequest.builder()
                .email("Sally@gmail.com")
                .password("123456")
                .build();
        AccountEntity accountEntity = AccountEntity.builder()
                .id(1L)
                .username(request.getEmail())
                .password("encodedPassword")
                .build();
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .firstname("Sally")
                .lastname("Phan")
                .address("Utrecht")
                .phoneNumber("0623232333")
                .email("Sally@gmail.com")
                .role(RoleEnum.CUSTOMER)
                .build();
        when(userRepository.findByEmail(request.getEmail())).thenReturn(userEntity);
        when(accountRepository.findByUsername(request.getEmail())).thenReturn(accountEntity);
        when(passwordEncoder.matches(request.getPassword(),accountEntity.getPassword())).thenReturn(false);
        assertThrows(InvalidCredentialsException.class,()->loginUseCase.login(request));
    }

}