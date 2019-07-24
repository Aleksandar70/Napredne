package com.msgnetconomy.appraisalsheet.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.msgnetconomy.appraisalsheet.dto.UserDto;
import com.msgnetconomy.appraisalsheet.service.UserService;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private static final String API = "http://localhost:4200";
    private static final String LOGIN = "/login";
    private static final String TOKEN = "token";
    private static final String UNAUTHORIZED = "unauthorized";
    private static final String USER = "user";
    private static final String ADD_NEW_USER = "/add-new-user";
    private static final String GET_USER_BY_USER_NAME = "/get-logged-in-user";
    private static final String GET_ALL_USERS = "/get-all-users";
    public static final String DELETE_USER_USERNAME = "/delete-user/{username}";

    @Autowired
    private UserService userService;

    @RequestMapping(value = LOGIN, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @CrossOrigin(origins = API)
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        JsonObject jsonObject = new JsonObject();
        try {
            String token = userService.authenticate(userDto);
            if (UNAUTHORIZED.equals(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(UNAUTHORIZED);
            }
            jsonObject.addProperty(TOKEN, token);
            jsonObject.add(USER, new Gson().toJsonTree(getCurrentUser(userDto)));
            return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
        } catch (MappingException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or password");
        }
    }

    public UserDto getCurrentUser(@RequestBody UserDto userDto) {
        return userService.getCurrentUser(userDto);
    }

    @RequestMapping(value = ADD_NEW_USER, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @CrossOrigin(origins = API)
    public ResponseEntity<String> saveUser(@RequestBody UserDto user) {
        try {
            userService.saveUser(user);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = GET_USER_BY_USER_NAME)
    @CrossOrigin(origins = API)
    public UserDto getCurrentUserByUserName(@RequestParam String userName) {
        return userService.findByUsername(userName);
    }

    @GetMapping(value = GET_ALL_USERS)
    @CrossOrigin(origins = API)
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @DeleteMapping(path = DELETE_USER_USERNAME)
    @CrossOrigin(origins = API)
    public ResponseEntity removeUser(@PathVariable String username) {
        try {
            userService.removeUser(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MappingException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist");
        }
    }
}
