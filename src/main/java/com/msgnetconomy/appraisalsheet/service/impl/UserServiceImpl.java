package com.msgnetconomy.appraisalsheet.service.impl;

import com.msgnetconomy.appraisalsheet.dao.UserDAO;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.dto.UserDto;
import com.msgnetconomy.appraisalsheet.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private static final String COLON = ":";
    private static final String UNAUTHORIZED = "unauthorized";

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public UserDto findByUsername(String username) {
        return mapper.map(userDAO.findByUsername(username), UserDto.class);
    }

    @Override
    public String authenticate(UserDto userDto) {
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        UserDto userDB = mapper.map(userDAO.login(userEntity.getUsername(), userEntity.getPassword()), UserDto.class);
        if (Objects.isNull(userDB)) {
            return UNAUTHORIZED;
        }
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
    public void removeUser(String username) {
        UserDto userDto = mapper.map(userDAO.findByUsername(username), UserDto.class);
        if (Objects.nonNull(userDto)) {
            userDAO.delete(mapper.map(userDto, UserEntity.class));
        } else {
            throw new EntityNotFoundException("User does not exist");
        }
    }
}
