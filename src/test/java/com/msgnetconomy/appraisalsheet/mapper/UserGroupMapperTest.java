package com.msgnetconomy.appraisalsheet.mapper;

import com.msgnetconomy.appraisalsheet.domain.UserGroupEntity;
import com.msgnetconomy.appraisalsheet.dto.UserGroupDto;
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
public class UserGroupMapperTest {

    public static final char USER_GROUP_ID = '1';
    public static final String NAME = "Employee";
    public static final String DESCRIPTION = "Basic";

    @Autowired
    private DozerBeanMapper mapper;

    @Before
    public void setUp() {

    }

    @Test
    public void mappingUserGroupEntityToUserGroupDtoTest() {
        UserGroupEntity userGroupEntity = new UserGroupEntity();
        userGroupEntity.setUserGroupId(USER_GROUP_ID);
        userGroupEntity.setName(NAME);
        userGroupEntity.setDescription(DESCRIPTION);

        UserGroupDto userGroupDto = mapper.map(userGroupEntity, UserGroupDto.class);

        assertEquals(userGroupEntity.getUserGroupId(), userGroupDto.getUserGroupId());
        assertEquals(userGroupEntity.getDescription(), userGroupDto.getDescription());
        assertEquals(userGroupEntity.getName(), userGroupDto.getName());
    }

    @Test
    public void mappingUserGroupDtoToUserGroupEntityTest() {
        UserGroupDto userGroupDto = new UserGroupDto();
        userGroupDto.setUserGroupId(USER_GROUP_ID);
        userGroupDto.setName(NAME);
        userGroupDto.setDescription(DESCRIPTION);

        UserGroupEntity userGroupEntity = mapper.map(userGroupDto, UserGroupEntity.class);

        assertEquals(userGroupEntity.getUserGroupId(), userGroupDto.getUserGroupId());
        assertEquals(userGroupEntity.getDescription(), userGroupDto.getDescription());
        assertEquals(userGroupEntity.getName(), userGroupDto.getName());
    }
}
