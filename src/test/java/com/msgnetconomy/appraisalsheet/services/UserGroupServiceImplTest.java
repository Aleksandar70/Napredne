package com.msgnetconomy.appraisalsheet.services;

import com.msgnetconomy.appraisalsheet.domain.UserGroupEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserGroupServiceImplTest {

    @Autowired
    private DozerBeanMapper mapper;

    @Test(expected = MappingException.class)
    public void addUserGroupTest() {
        mapper.map(null, UserGroupEntity.class);
    }
}
