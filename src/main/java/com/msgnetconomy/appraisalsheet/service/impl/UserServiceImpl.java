package com.msgnetconomy.appraisalsheet.service.impl;

import com.msgnetconomy.appraisalsheet.dao.UserDAO;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.dto.UserDto;
import com.msgnetconomy.appraisalsheet.service.ResetPasswordService;
import com.msgnetconomy.appraisalsheet.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private static final String COLON = ":";
    private static final String MESSAGE_UPDATE_ERROR = "User does not exist in the database";

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private ResetPasswordService resetPasswordService;

    @Override
    public UserDto findByUsername(String username) {
        return mapper.map(userDAO.findByUsername(username), UserDto.class);
    }

    @Override
    public String authenticate(UserDto userDto) throws MappingException {
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        UserDto userDB = mapper.map(userDAO.login(userEntity.getUsername(), userEntity.getPassword()), UserDto.class);
        StringBuilder token = new StringBuilder();
        token.append(userDB.getUsername()).append(COLON).append(userDB.getFirstName());
        return new String(Base64.encodeBase64(token.toString().getBytes()));
    }

    @Override
    public UserDto getCurrentUser(UserDto userDto) {
        return mapper.map(userDAO.login(userDto.getUsername(), userDto.getPassword()), UserDto.class);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        return mapper.map(userDAO.save(userEntity), UserDto.class);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userDAO.findAll()
                .stream()
                .map(entity -> mapper.map(entity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void removeUser(String username) throws MappingException {
        UserDto userDto = mapper.map(userDAO.findByUsername(username), UserDto.class);
        userDAO.delete(mapper.map(userDto, UserEntity.class));
    }

    @Override
    public void updateUser(UserEntity user) {
        int affectedRows;
        if (StringUtils.isEmpty(user.getPassword())) {
            affectedRows = userDAO.updateUser(user.getUserId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getUserGroup(), user.getUserManager());
        } else {
            affectedRows = userDAO.updateUser(user.getUserId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getUserGroup(), user.getUserManager());
        }
        if (affectedRows == 0) {
            throw new RuntimeException(MESSAGE_UPDATE_ERROR);
        }
    }

    @Override
    public void changePassword(UserEntity userEntity, String newPassword) {
//        userEntity.setPassword(encoder.encode(newPassword));
        userEntity.setPassword(newPassword);
        updateUser(userEntity);
        resetPasswordService.deleteByUserId(userEntity.getUserId());
    }
}
