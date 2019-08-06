package com.msgnetconomy.appraisalsheet.service.impl;

import com.msgnetconomy.appraisalsheet.config.AuthorityConstants;
import com.msgnetconomy.appraisalsheet.dao.ResetPasswordDAO;
import com.msgnetconomy.appraisalsheet.domain.PasswordResetTokenEntity;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.exception.PasswordResetInvalidTokenException;
import com.msgnetconomy.appraisalsheet.exception.PasswordResetTokenExpiredException;
import com.msgnetconomy.appraisalsheet.service.ResetPasswordService;
import com.msgnetconomy.appraisalsheet.service.UserService;
import com.msgnetconomy.appraisalsheet.util.CustomDateUtil;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private static final String MESSAGE_USER_NOT_FOUND = "User with a username \"%s\" could not be found.";

    @Value("${application.base.url}")
    private String applicationBaseUrl;

    @Value("${reset.password.token.expiration.time.days}")
    private int resetPasswordTokenExpirationTimeDays;

    @Autowired
    private ResetPasswordDAO resetPasswordDAO;

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @Override
    public void createPasswordResetTokenForUser(UserEntity userEntity, String token) {
        PasswordResetTokenEntity passwordResetTokenEntity = resetPasswordDAO.findByUserId(userEntity.getUserId());
        if (passwordResetTokenEntity != null) {
            updatePasswordResetToken(passwordResetTokenEntity, token);
        } else {
            savePasswordResetToken(userEntity, token);
        }

    }

    @Override
    public void deleteByUserId(int userId) {
        resetPasswordDAO.deleteByUserId(userId);
    }

    @Override
    public PasswordResetTokenEntity getPasswordResetTokenByToken(String token) {
        return resetPasswordDAO.findByToken(token).orElseThrow(PasswordResetInvalidTokenException::new);
    }

    @Override
    public void sendForgotPasswordEmail(String username) {
        UserEntity userEntity = mapper.map(userService.findByUsername(username), UserEntity.class);
        if (userEntity != null) {
            String token = UUID.randomUUID().toString();
            createPasswordResetTokenForUser(userEntity, token);
            mailSender.send(createResetTokenEmail(userEntity, token));
        } else {
            throw new UsernameNotFoundException(String.format(MESSAGE_USER_NOT_FOUND, username));
        }
    }

    @Override
    public void validatePasswordResetToken(int id, String token) {
        PasswordResetTokenEntity passwordResetTokenEntity = getPasswordResetTokenByToken(token);
        if (passwordResetTokenEntity == null || passwordResetTokenEntity.getUser().getUserId() != id) {
            throw new PasswordResetInvalidTokenException();
        }
        if (passwordResetTokenEntity.getExpiryDate().before(new Date())) {
            throw new PasswordResetTokenExpiredException();
        }
        UserEntity userEntity = passwordResetTokenEntity.getUser();
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userEntity, null, Collections.singletonList(new SimpleGrantedAuthority(AuthorityConstants.CHANGE_PASSWORD_AUTHORITY)));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private SimpleMailMessage createResetTokenEmail(UserEntity userEntity, String token) {
        SimpleMailMessage email = new SimpleMailMessage();
        String url = applicationBaseUrl + "/reset-password-page?id=" + userEntity.getUserId() + "&token=" + token;
        String body = "Please follow this link to reset your password:\n" + url;
        email.setSubject("Reset Password");
        email.setText(body);
        email.setTo(userEntity.getUsername());
        email.setFrom("rookies@msgnetconomy.net");
        return email;
    }

    private void updatePasswordResetToken(PasswordResetTokenEntity passwordResetTokenEntity, String token) {
        passwordResetTokenEntity.setToken(token);
        passwordResetTokenEntity.setExpiryDate(getExpiryDate());
        resetPasswordDAO.update(
                passwordResetTokenEntity.getPasswordResetTokenId(),
                passwordResetTokenEntity.getToken(),
                passwordResetTokenEntity.getExpiryDate()
        );
    }

    private void savePasswordResetToken(UserEntity userEntity, String token) {
        PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
        passwordResetTokenEntity.setUser(userEntity);
        passwordResetTokenEntity.setToken(token);
        passwordResetTokenEntity.setExpiryDate(getExpiryDate());
        resetPasswordDAO.save(passwordResetTokenEntity);
    }

    private Date getExpiryDate() {
        return CustomDateUtil.getTodayPlusDaysToAdd(resetPasswordTokenExpirationTimeDays);
    }
}
