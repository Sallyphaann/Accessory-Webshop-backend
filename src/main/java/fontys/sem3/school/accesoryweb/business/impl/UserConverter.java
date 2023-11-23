package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.domain.Product;
import fontys.sem3.school.accesoryweb.domain.User;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import fontys.sem3.school.accesoryweb.persistence.entity.UserEntity;

final class UserConverter {
    private UserConverter(){

    }
    public static User convert(UserEntity user){
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .lastname(user.getLastname())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .firstname(user.getFirstname())
                .build();

    }
}
