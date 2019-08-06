package com.msgnetconomy.appraisalsheet.mapper;

import com.msgnetconomy.appraisalsheet.domain.UserManagerEntity;
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
public class UserManagerMapperTest {

    public static final char USER_MANAGER_ID = '1';
    public static final String MANAGER_USERNAME = "luka@gmail.com";
    public static final String USER_MANAGER_NAME = "Luka Malidzan";

    @Autowired
    private DozerBeanMapper mapper;

    @Before
    public void setUp() {

    }

    @Test
    public void mappingUserManagerEntityToUserManagerDtoTest() {
        UserManagerEntity userManagerEntity = new UserManagerEntity();
        userManagerEntity.setUserManagerId(USER_MANAGER_ID);
        userManagerEntity.setManagerUsername(MANAGER_USERNAME);
        userManagerEntity.setName(USER_MANAGER_NAME);

        UserManagerDto userManagerDto = mapper.map(userManagerEntity, UserManagerDto.class);

        assertEquals(userManagerEntity.getUserManagerId(), userManagerDto.getUserManagerId());
        assertEquals(userManagerEntity.getManagerUsername(), userManagerDto.getManagerUsername());
        assertEquals(userManagerEntity.getName(), userManagerDto.getName());
    }
}
