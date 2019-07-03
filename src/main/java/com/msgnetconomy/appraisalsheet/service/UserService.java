package com.msgnetconomy.appraisalsheet.service;

import com.msgnetconomy.appraisalsheet.domain.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity findByUsername(String username);

    String authenticate(UserEntity user);

    UserEntity getCurrentUser(UserEntity user);

    UserEntity saveUser(UserEntity user);

    List<UserEntity> findAllUsers();
}
