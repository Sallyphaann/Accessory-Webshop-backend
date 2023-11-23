package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.AccessTokenEncoder;
import fontys.sem3.school.accesoryweb.business.LoginUseCase;
import fontys.sem3.school.accesoryweb.business.exception.InvalidCredentialsException;
import fontys.sem3.school.accesoryweb.domain.AccessToken;
import fontys.sem3.school.accesoryweb.domain.LoginRequest;
import fontys.sem3.school.accesoryweb.domain.LoginResponse;
import fontys.sem3.school.accesoryweb.persistence.AccountRepository;
import fontys.sem3.school.accesoryweb.persistence.UserRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.AccountEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.RoleEnum;
import fontys.sem3.school.accesoryweb.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;
    private final UserRepository userRepository;
    @Override
    public LoginResponse login(LoginRequest request) {
        AccountEntity account = accountRepository.findByUsername(request.getEmail());
        UserEntity user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(request.getPassword(), account.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(account,user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }
    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);

    }
    private String generateAccessToken(AccountEntity account,UserEntity user) {
        Long userId = account.getUsername() != null ? account.getId() : null;
        RoleEnum role = user.getRole();

        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getFirstname())
                        .roles(role.toString())
                        .userId(userId)
                        .build());
    }
}
