package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.CreateAdminUseCase;
import fontys.sem3.school.accesoryweb.domain.CreateAdminRequest;
import fontys.sem3.school.accesoryweb.domain.CreateAdminResponse;

import fontys.sem3.school.accesoryweb.persistence.AccountRepository;

import fontys.sem3.school.accesoryweb.persistence.UserRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.RoleEnum;
import fontys.sem3.school.accesoryweb.persistence.entity.AccountEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CreateAdminUseCaseImpl implements CreateAdminUseCase {
    private final AccountRepository accountRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public CreateAdminResponse createAdmin(CreateAdminRequest request) {
        UserEntity savedAdmin = saveNewAdmin(request);
        saveNewAccount(savedAdmin,request.getPassword());
        return CreateAdminResponse.builder()
                .adminId(savedAdmin.getId()).build();

    }
    private void saveNewAccount(UserEntity admin, String password){
        String encodedPassword = passwordEncoder.encode(password);
        AccountEntity newUser = AccountEntity.builder()
                .username(admin.getEmail())
                .password(encodedPassword)
                .userId(admin)
                .build();

        accountRepository.save(newUser);

    }
    private UserEntity saveNewAdmin(CreateAdminRequest request){
        UserEntity newAdmin = UserEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .role(RoleEnum.ADMIN).build();
        return userRepository.save(newAdmin);
    }
}
/**/