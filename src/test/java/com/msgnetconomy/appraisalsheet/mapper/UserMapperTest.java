package com.msgnetconomy.appraisalsheet.mapper;

import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.domain.UserGroupEntity;
import com.msgnetconomy.appraisalsheet.domain.UserManagerEntity;
import com.msgnetconomy.appraisalsheet.dto.UserDto;
import com.msgnetconomy.appraisalsheet.dto.UserGroupDto;
import com.msgnetconomy.appraisalsheet.dto.UserManagerDto;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    public static final String USERNAME = "berda@gmail.com";
    public static final String PASSWORD = "123456";
    public static final String FIRST_NAME = "Aleksandar";
    public static final String LAST_NAME = "Berdovic";
    public static final char USER_ID = '1';
    public static final char USER_GROUP_ID = '1';
    public static final String NAME = "Employee";
    public static final String DESCRIPTION = "Basic";
    public static final char USER_MANAGER_ID = '1';
    public static final String MANAGER_USERNAME = "luka@gmail.com";
    public static final String USER_MANAGER_NAME = "Luka Malidzan";

    @Autowired
    private DozerBeanMapper mapper;

    @Before
    public void setUp() {

    }

    @Test
    public void mappingUserEntityToUserDtoTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(USERNAME);
        userEntity.setPassword(PASSWORD);
        userEntity.setFirstName(FIRST_NAME);
        userEntity.setLastName(LAST_NAME);
        userEntity.setUserId(USER_ID);

        UserGroupEntity userGroupEntity = new UserGroupEntity();
        userGroupEntity.setUserGroupId(USER_GROUP_ID);
        userGroupEntity.setName(NAME);
        userGroupEntity.setDescription(DESCRIPTION);

        UserManagerEntity userManagerEntity = new UserManagerEntity();
        userManagerEntity.setUserManagerId(USER_MANAGER_ID);
        userManagerEntity.setManagerUsername(MANAGER_USERNAME);
        userManagerEntity.setName(USER_MANAGER_NAME);

        userEntity.setUserGroup(userGroupEntity);
        userEntity.setUserManager(userManagerEntity);

        UserDto userDto = mapper.map(userEntity, UserDto.class);
        assertEquals(userEntity.getUsername(), userDto.getUsername());
        assertEquals(userEntity.getPassword(), userDto.getPassword());
        assertEquals(userEntity.getFirstName(), userDto.getFirstName());
        assertEquals(userEntity.getLastName(), userDto.getLastName());
        assertEquals(userEntity.getUserId(), userDto.getUserId());

        assertEquals(userEntity.getUserGroup().getUserGroupId(), userDto.getUserGroupDto().getUserGroupId());
        assertEquals(userEntity.getUserGroup().getDescription(), userDto.getUserGroupDto().getDescription());
        assertEquals(userEntity.getUserGroup().getName(), userDto.getUserGroupDto().getName());

        assertEquals(userEntity.getUserManager().getUserManagerId(), userDto.getUserManagerDto().getUserManagerId());
        assertEquals(userEntity.getUserManager().getManagerUsername(), userDto.getUserManagerDto().getManagerUsername());
        assertEquals(userEntity.getUserManager().getName(), userDto.getUserManagerDto().getName());
    }

    @Test
    public void mappingUserDtoToUserEntityTest() {
        UserDto userDto = new UserDto();
        userDto.setUsername(USERNAME);
        userDto.setPassword(PASSWORD);
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        userDto.setUserId(USER_ID);

        UserGroupDto userGroupDto = new UserGroupDto();
        userGroupDto.setUserGroupId(USER_GROUP_ID);
        userGroupDto.setName(NAME);
        userGroupDto.setDescription(DESCRIPTION);

        UserManagerDto userManagerDto = new UserManagerDto();
        userManagerDto.setUserManagerId(USER_MANAGER_ID);
        userManagerDto.setManagerUsername(MANAGER_USERNAME);
        userManagerDto.setName(USER_MANAGER_NAME);

        userDto.setUserGroupDto(userGroupDto);
        userDto.setUserManagerDto(userManagerDto);

        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        assertEquals(userEntity.getUsername(), userDto.getUsername());
        assertEquals(userEntity.getPassword(), userDto.getPassword());
        assertEquals(userEntity.getFirstName(), userDto.getFirstName());
        assertEquals(userEntity.getLastName(), userDto.getLastName());
        assertEquals(userEntity.getUserId(), userDto.getUserId());

        assertEquals(userEntity.getUserGroup().getUserGroupId(), userDto.getUserGroupDto().getUserGroupId());
        assertEquals(userEntity.getUserGroup().getDescription(), userDto.getUserGroupDto().getDescription());
        assertEquals(userEntity.getUserGroup().getName(), userDto.getUserGroupDto().getName());

        assertEquals(userEntity.getUserManager().getUserManagerId(), userDto.getUserManagerDto().getUserManagerId());
        assertEquals(userEntity.getUserManager().getManagerUsername(), userDto.getUserManagerDto().getManagerUsername());
        assertEquals(userEntity.getUserManager().getName(), userDto.getUserManagerDto().getName());
    }
}
