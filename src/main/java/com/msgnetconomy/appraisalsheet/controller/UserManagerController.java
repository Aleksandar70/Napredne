package com.msgnetconomy.appraisalsheet.controller;

import com.msgnetconomy.appraisalsheet.dto.UserManagerDto;
import com.msgnetconomy.appraisalsheet.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserManagerController {

    private static final String API = "http://localhost:4200";
    private static final String GET_USER_MANAGERS = "/get-user-managers";

    @Autowired
    private UserManagerService userManagerService;

    @GetMapping(value = GET_USER_MANAGERS)
    @CrossOrigin(origins = API)
    public List<UserManagerDto> getAllUserManagers() {
        return userManagerService.getAllUserManagers();
    }
}
