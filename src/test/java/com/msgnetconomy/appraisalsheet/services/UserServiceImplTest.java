package com.msgnetconomy.appraisalsheet.services;

import com.msgnetconomy.appraisalsheet.dto.UserDto;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private DozerBeanMapper mapper;

    @Before
    public void setUp() {
    }

    @Test(expected = MappingException.class)
    public void authenticateMappingExceptionTest() {
        mapper.map(null, UserDto.class);
    }
}
