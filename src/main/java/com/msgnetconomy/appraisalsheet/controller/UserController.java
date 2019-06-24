package com.msgnetconomy.appraisalsheet.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final String API = "http://localhost:4200";
    private static final String LOGIN = "/login";
    private static final String TOKEN = "token";
    private static final String UNAUTHORIZED = "unauthorized";
    private static final String USER = "user";
    private static final String ADD_NEW_USER = "/add-new-user";
    private static final String GET_USER_BY_USER_NAME = "/get-logged-in-user";
    private static final String LOGOUT = "/logout";

    @Autowired
    private UserService userService;

    @RequestMapping(value = LOGIN, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @CrossOrigin(origins = API)
    public ResponseEntity<String> login(@RequestBody UserEntity user) {
        JsonObject jsonObject = new JsonObject();
        String token = userService.authenticate(user);
        if (UNAUTHORIZED.equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(UNAUTHORIZED);
        }
        jsonObject.addProperty(TOKEN, token);
        jsonObject.add(USER, new Gson().toJsonTree(getCurrentUser(user)));
        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
    }

    public UserEntity getCurrentUser(@RequestBody UserEntity user) {
        return userService.getCurrentUser(user);
    }

    @RequestMapping(value = ADD_NEW_USER, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @CrossOrigin(origins = API)
    public ResponseEntity<String> saveUser(@RequestBody UserEntity user) {
        try {
            userService.saveUser(user);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = GET_USER_BY_USER_NAME)
    @CrossOrigin(origins = API)
    public UserEntity getCurrentUserByUserName(@RequestParam String userName) {
        return userService.findByUsername(userName);
    }
}
