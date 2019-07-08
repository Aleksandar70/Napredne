package com.msgnetconomy.appraisalsheet.service.impl;

import com.msgnetconomy.appraisalsheet.dao.UserDAO;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.dto.UserDto;
import com.msgnetconomy.appraisalsheet.mapper.UserMapper;
import com.msgnetconomy.appraisalsheet.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private static final String COLON = ":";
    private static final String UNAUTHORIZED = "unauthorized";


    @Autowired
    private UserDAO userDAO;

    @Override
    public UserEntity findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public String authenticate(UserEntity user) {
        UserEntity userDB = userDAO.login(user.getUsername(), user.getPassword());
        if (Objects.isNull(userDB)) {
            return UNAUTHORIZED;
        }
        StringBuilder token = new StringBuilder();
        token.append(userDB.getUsername()).append(COLON).append(userDB.getFirstName());
        return new String(Base64.encodeBase64(token.toString().getBytes()));
    }

    @Override
//    @Transactional
    public UserEntity getCurrentUser(UserEntity user) {
        return userDAO.login(user.getUsername(), user.getPassword());
//        return UserMapper.INSTANCE.userToUserDto(userEntity);
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userDAO.save(user);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public void removeUser(String username) {
        UserEntity userEntity = userDAO.findByUsername(username);
        if (Objects.nonNull(userEntity)) {
            userDAO.delete(userEntity);
        } else {
            throw new EntityNotFoundException("User does not exist");
        }
    }
}
