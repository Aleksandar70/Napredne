package com.msgnetconomy.appraisalsheet.controller;

import com.msgnetconomy.appraisalsheet.service.UserService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Test
    public void getAllUsersTest() {
        userService.findAllUsers();
    }
}
