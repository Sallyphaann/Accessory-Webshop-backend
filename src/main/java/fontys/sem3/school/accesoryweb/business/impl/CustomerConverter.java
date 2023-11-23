package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.domain.User;
import fontys.sem3.school.accesoryweb.persistence.entity.UserEntity;

final class CustomerConverter {
    private CustomerConverter(){

    }
    public static User convert(UserEntity user){
        return User.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

}
