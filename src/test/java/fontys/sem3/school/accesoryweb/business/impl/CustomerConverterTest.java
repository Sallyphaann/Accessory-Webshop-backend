package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.domain.User;
import fontys.sem3.school.accesoryweb.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerConverterTest {
    @Test
    void convert_validUserEntity_returnsConvertedUser() {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .firstname("Sally")
                .lastname("phan")
                .email("Sally@gmail.com")
                .address("Utrecht")
                .phoneNumber("061578943")
                .build();

        User expectedUser = User.builder()
                .id(1L)
                .firstname("Sally")
                .lastname("phan")
                .email("Sally@gmail.com")
                .address("Utrecht")
                .phoneNumber("061578943")
                .build();

        User convertedUser = CustomerConverter.convert(userEntity);

        assertEquals(expectedUser, convertedUser);
    }

}