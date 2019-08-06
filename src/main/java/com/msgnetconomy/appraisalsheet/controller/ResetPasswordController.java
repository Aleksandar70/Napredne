package com.msgnetconomy.appraisalsheet.controller;

import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.dto.ForgotPasswordDto;
import com.msgnetconomy.appraisalsheet.dto.ResetPasswordDto;
import com.msgnetconomy.appraisalsheet.dto.ResponseMessage;
import com.msgnetconomy.appraisalsheet.exception.PasswordResetInvalidTokenException;
import com.msgnetconomy.appraisalsheet.exception.PasswordResetTokenExpiredException;
import com.msgnetconomy.appraisalsheet.service.ResetPasswordService;
import com.msgnetconomy.appraisalsheet.service.UserService;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResetPasswordController {

    private static final Logger LOG = LoggerFactory.getLogger(ResetPasswordController.class);
    public static final String RESET_PASSWORD_PAGE_URL = "/reset-password-page";
    private static final String RESET_PASSWORD_PAGE = "reset-password";
    private static final String MODEL_ATTR_MESSAGE = "message";
    private static final String MODEL_ATTR_ERROR = "error";
    private static final String MODEL_ATTR_RESET_PASSWORD_FORM = "resetPasswordForm";
    private static final String API = "http://localhost:4200";

    @Value("${message.validate.password.reset.token.error}")
    private String messageValidatePasswordResetTokenError;
    @Value("${message.token.invalid}")
    private String messageTokenInvalid;
    @Value("${message.token.expired}")
    private String messageTokenExpired;
    @Value("${message.forgot.password.send.email.success}")
    private String messageForgotPasswordSendEmailSuccess;
    @Value("${message.forgot.password.send.email.error}")
    private String messageForgotPasswordSendEmailError;
    @Value("${message.reset.password.success}")
    private String messageResetPasswordSuccess;
    @Value("${message.reset.password.error}")
    private String messageResetPasswordError;

    @Autowired
    private UserService userService;

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private ResetPasswordService resetPasswordService;

    @PostMapping(value = "/reset-password-email")
    @ResponseBody
    @CrossOrigin(origins = API)
    ResponseEntity<ResponseMessage> forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDTO) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            resetPasswordService.sendForgotPasswordEmail(forgotPasswordDTO.getUsername());
            responseMessage.setMessage(String.format(messageForgotPasswordSendEmailSuccess, forgotPasswordDTO.getUsername()));
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            LOG.error("Username not found exception occurred while sending forgot password email to the user!", e);
            responseMessage.setMessage(e.getMessage());
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException e) {
            LOG.error("An exception occurred while sending forgot password email to the user!", e);
            responseMessage.setMessage(String.format(messageForgotPasswordSendEmailError, forgotPasswordDTO.getUsername()));
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = RESET_PASSWORD_PAGE_URL)
    @CrossOrigin(origins = API)
    String resetPasswordPage(@RequestParam int id, @RequestParam String token, Model model) {
        try {
            resetPasswordService.validatePasswordResetToken(id, token);
            model.addAttribute(MODEL_ATTR_RESET_PASSWORD_FORM, new ResetPasswordDto());
        } catch (PasswordResetInvalidTokenException e) {
            LOG.error("Invalid token exception", e);
            model.addAttribute(MODEL_ATTR_ERROR, messageTokenInvalid);
        } catch (PasswordResetTokenExpiredException e) {
            LOG.error("Token expired exception", e);
            model.addAttribute(MODEL_ATTR_ERROR, messageTokenExpired);
        } catch (RuntimeException e) {
            LOG.error("An exception occurred while validating password reset token for a user", e);
            model.addAttribute(MODEL_ATTR_ERROR, messageValidatePasswordResetTokenError);
        }
        return RESET_PASSWORD_PAGE;
    }

    @PostMapping(value = "/" + RESET_PASSWORD_PAGE)
    @CrossOrigin(origins = API)
    public String savePassword(ResetPasswordDto resetPasswordDTO, Model model) {
        UserEntity userEntity = mapper.map(userService.findByUsername("aleksandar.berdovic@gmail.com"), UserEntity.class);//puca
//        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            userService.changePassword(userEntity, resetPasswordDTO.getNewPassword());
            model.addAttribute(MODEL_ATTR_MESSAGE, messageResetPasswordSuccess);
        } catch (RuntimeException e) {
            LOG.error("An exception occurred while resetting password for the user.", e);
            model.addAttribute(MODEL_ATTR_ERROR, messageResetPasswordError);
        }
        return RESET_PASSWORD_PAGE;
    }
}
