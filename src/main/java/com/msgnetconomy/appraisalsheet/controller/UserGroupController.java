package com.msgnetconomy.appraisalsheet.controller;

import com.msgnetconomy.appraisalsheet.domain.UserGroupEntity;
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
@RequestMapping("/rest")
public class UserGroupController {
    private static final String ADD_GET_USER_GROUP = "/add-get-user-group";
    private static final Logger logger = LoggerFactory.getLogger(UserGroupController.class);

    private UserGroupService userGroupService;

    @GetMapping(value = ADD_GET_USER_GROUP)
    public List<UserGroupEntity> getAllUserGroups() {
        return userGroupService.getAllUserGroups();
    }

    @PostMapping(value = ADD_GET_USER_GROUP)
    ResponseEntity<Authenticator.Success> addUserGroup(@RequestBody UserGroupEntity userGroupEntity) {
        try {
            userGroupService.addUserGroup(userGroupEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An exception occurred!", e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
