package com.msgnetconomy.appraisalsheet.controller;

import com.msgnetconomy.appraisalsheet.dto.UserGroupDto;
import com.msgnetconomy.appraisalsheet.service.UserGroupService;
import com.sun.net.httpserver.Authenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserGroupController {

    private static final String API = "http://localhost:4200";
    private static final String ADD_GET_USER_GROUP = "/add-get-user-group";
    private static final Logger logger = LoggerFactory.getLogger(UserGroupController.class);
    public static final String EXCEPTION = "An exception occurred!";

    @Autowired
    private UserGroupService userGroupService;

    @GetMapping(value = ADD_GET_USER_GROUP)
    @CrossOrigin(origins = API)
    public List<UserGroupDto> getAllUserGroups() {
        return userGroupService.getAllUserGroups();
    }

    @PostMapping(value = ADD_GET_USER_GROUP)
    @CrossOrigin(origins = API)
    ResponseEntity<Authenticator.Success> addUserGroup(@RequestBody UserGroupDto userGroupDto) {
        try {
            userGroupService.addUserGroup(userGroupDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(EXCEPTION, e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
