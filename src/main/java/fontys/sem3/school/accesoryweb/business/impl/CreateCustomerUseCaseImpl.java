package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.business.CreateCustomerUseCase;
import fontys.sem3.school.accesoryweb.business.exception.EmailAlreadyExisitsException;
import fontys.sem3.school.accesoryweb.domain.CreateCustomerRequest;
import fontys.sem3.school.accesoryweb.domain.CreateCustomerResponse;
import fontys.sem3.school.accesoryweb.persistence.UserRepository;
import fontys.sem3.school.accesoryweb.persistence.AccountRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
       if(userRepository.existsByEmail(request.getEmail())){
           throw new EmailAlreadyExisitsException();
       }
       UserEntity savedCustomer = saveNewCustomer(request);
       saveNewUser(savedCustomer, request.getPassword());
       return CreateCustomerResponse.builder()
               .customerId(savedCustomer.getId()).build();
    }
    private void saveNewUser(UserEntity customer, String password){
        String encodedPassword = passwordEncoder.encode(password);
        AccountEntity newUser = AccountEntity.builder()
                .username(customer.getEmail())
                .password(encodedPassword)
                .userId(customer)
                .build();

        accountRepository.save(newUser);

    }
    private UserEntity saveNewCustomer(CreateCustomerRequest request){
        UserEntity newCustomer = UserEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .address(request.getAddress())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .role(RoleEnum.CUSTOMER).build();
        return userRepository.save(newCustomer);
    }
}
