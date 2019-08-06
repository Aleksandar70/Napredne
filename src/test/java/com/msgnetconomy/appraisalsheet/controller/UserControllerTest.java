//package com.msgnetconomy.appraisalsheet.controller;
//
//import com.msgnetconomy.appraisalsheet.service.UserService;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.ResponseEntity;
//import com.msgnetconomy.appraisalsheet.dto.*;
//import com.msgnetconomy.appraisalsheet.facade.UserFacade;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.*;
//
//public class UserControllerTest {
//
//    private static final String MESSAGE_REGISTRATION_SUCCESS = "User registered successfully!";
//    private static final String MESSAGE_REGISTRATION_FAILED_DUPLICATE_USERNAME = "Fail to register user: Username is already in use!";
//    private static final String MESSAGE_REGISTRATION_FAILED = "Fail to register user!";
//    private static final String MESSAGE_UPDATE_SUCCESS = "User updated successfully!";
//    private static final String MESSAGE_UPDATE_ERROR = "User can not be updated!";
//    private static final String MESSAGE_DELETE_SUCCESS = "User deleted successfully!";
//    private static final String MESSAGE_DELETE_ERROR = "User can not be deleted!";
//
//    @InjectMocks
//    UserController userController;
//
//    @Mock
//    UserService userService;
//
//    @Test
//    public void getAllUsersTest() {
//        userService.findAllUsers();
//    }
//
//    @Mock
//    private UserFacade userFacade;
//
//    @Test
//    public void testLogin() {
//        LoginDTO loginDTO = new LoginDTO();
//        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
//        when(userFacade.authenticate(loginDTO)).thenReturn(jwtResponseDTO);
//        assertEquals(ResponseEntity.ok(jwtResponseDTO), userController.login(loginDTO));
//    }
//
//    @Test
//    public void testRegisterSuccess() {
//        UserDto userInfoDTO = new UserDto();
//        ResponseEntity response = userController.register(userInfoDTO);
//        verify(userFacade, times(1)).saveUser(userInfoDTO);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertTrue(response.getBody() instanceof ResponseMessage);
//        assertEquals(MESSAGE_REGISTRATION_SUCCESS, ((ResponseMessage) response.getBody()).getMessage());
//    }
//
//    @Test
//    public void testRegisterDuplicateUsername() {
//        UserDto userInfoDTO = new UserDto();
//        doThrow(DataIntegrityViolationException.class).when(userFacade).saveUser(userInfoDTO);
//        ResponseEntity response = userController.register(userInfoDTO);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertTrue(response.getBody() instanceof ResponseMessage);
//        assertEquals(MESSAGE_REGISTRATION_FAILED_DUPLICATE_USERNAME, ((ResponseMessage) response.getBody()).getMessage());
//    }
//
//    @Test
//    public void testRegisterError() {
//        UserDto userInfoDTO = new UserDto();
//        doThrow(RuntimeException.class).when(userFacade).saveUser(userInfoDTO);
//        ResponseEntity response = userController.register(userInfoDTO);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertTrue(response.getBody() instanceof ResponseMessage);
//        assertEquals(MESSAGE_REGISTRATION_FAILED, ((ResponseMessage) response.getBody()).getMessage());
//    }
//
//    @Test
//    public void testGetAllManagers() {
//        List<UserDto> managers = Collections.singletonList(new UserDto());
//        when(userFacade.getAllManagers()).thenReturn(managers);
//        ResponseEntity response = userController.getAllManagers();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(managers, response.getBody());
//    }
//
//    @Test
//    public void testGetAllManagersEmpty() {
//        List managers = Collections.EMPTY_LIST;
//        when(userFacade.getAllManagers()).thenReturn(managers);
//        ResponseEntity response = userController.getAllManagers();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(managers, response.getBody());
//    }
//
//    @Test
//    public void testGetAllManagersError() {
//        doThrow(RuntimeException.class).when(userFacade).getAllManagers();
//        ResponseEntity response = userController.getAllManagers();
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertEquals(Collections.EMPTY_LIST, response.getBody());
//    }
//
//    @Test
//    public void testGetAllUsers() {
//        List<UserDto> users = Collections.singletonList(new UserDTO());
//        when(userFacade.getAllUsers()).thenReturn(users);
//        ResponseEntity response = userController.getAllUsers();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(users, response.getBody());
//    }
//
//    @Test
//    public void testGetAllUsersEmpty() {
//        List users = Collections.EMPTY_LIST;
//        when(userFacade.getAllUsers()).thenReturn(users);
//        ResponseEntity response = userController.getAllUsers();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(users, response.getBody());
//    }
//
//    @Test
//    public void testGetAllUsersError() {
//        doThrow(RuntimeException.class).when(userFacade).getAllUsers();
//        ResponseEntity response = userController.getAllUsers();
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertEquals(Collections.EMPTY_LIST, response.getBody());
//    }
//
//    @Test
//    public void testGetUserByID() {
//        UserDTO userDTO = new UserDTO();
//        when(userFacade.getUserByID(1)).thenReturn(userDTO);
//        ResponseEntity response = userController.getUserByID(1);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(userDTO, response.getBody());
//    }
//
//    @Test
//    public void testGetUserByIDError() {
//        doThrow(RuntimeException.class).when(userFacade).getUserByID(1);
//        ResponseEntity response = userController.getUserByID(1);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    public void testUpdadeUser() {
//        UserInfoDTO userInfoDTO = new UserInfoDTO();
//        ResponseEntity response = userController.updateUser(1, userInfoDTO);
//        verify(userFacade, times(1)).updateUser(userInfoDTO);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertTrue(response.getBody() instanceof ResponseMessage);
//        assertEquals(MESSAGE_UPDATE_SUCCESS, ((ResponseMessage) response.getBody()).getMessage());
//    }
//
//    @Test
//    public void testUpdateUserError() {
//        UserInfoDTO userInfoDTO = new UserInfoDTO();
//        doThrow(RuntimeException.class).when(userFacade).updateUser(userInfoDTO);
//        ResponseEntity response = userController.updateUser(1, userInfoDTO);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertTrue(response.getBody() instanceof ResponseMessage);
//        assertEquals(MESSAGE_UPDATE_ERROR, ((ResponseMessage) response.getBody()).getMessage());
//    }
//
//    @Test
//    public void testDeleteUser() {
//        ResponseEntity response = userController.deleteUser(1);
//        verify(userFacade, times(1)).deleteUser(1);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertTrue(response.getBody() instanceof ResponseMessage);
//        assertEquals(MESSAGE_DELETE_SUCCESS, ((ResponseMessage) response.getBody()).getMessage());
//    }
//
//    @Test
//    public void testDeleteUserError() {
//        doThrow(RuntimeException.class).when(userFacade).deleteUser(1);
//        ResponseEntity response = userController.deleteUser(1);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertTrue(response.getBody() instanceof ResponseMessage);
//        assertEquals(MESSAGE_DELETE_ERROR, ((ResponseMessage) response.getBody()).getMessage());
//    }
//}
