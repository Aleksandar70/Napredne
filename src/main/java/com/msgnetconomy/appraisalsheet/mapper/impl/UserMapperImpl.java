package com.msgnetconomy.appraisalsheet.mapper.impl;

import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.dto.UserDto;
import com.msgnetconomy.appraisalsheet.mapper.UserMapper;

import javax.annotation.Generated;
import java.util.Objects;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userToUserDto(UserEntity user) {
        if (Objects.isNull(user)) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}
