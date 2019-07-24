package com.msgnetconomy.appraisalsheet.service;

import com.msgnetconomy.appraisalsheet.dto.UserDto;
import org.dozer.MappingException;

import java.util.List;

public interface UserService {

    UserDto findByUsername(String username);

    String authenticate(UserDto user) throws MappingException;

    UserDto getCurrentUser(UserDto user);

    UserDto saveUser(UserDto user);

    List<UserDto> findAllUsers();

    void removeUser(String username) throws MappingException;
}
