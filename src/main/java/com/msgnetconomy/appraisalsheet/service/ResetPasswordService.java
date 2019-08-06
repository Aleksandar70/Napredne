package com.msgnetconomy.appraisalsheet.service;

import com.msgnetconomy.appraisalsheet.domain.PasswordResetTokenEntity;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;

public interface ResetPasswordService {

    void createPasswordResetTokenForUser(UserEntity userEntity, String token);

    void deleteByUserId(int userId);

    PasswordResetTokenEntity getPasswordResetTokenByToken(String token);

    void sendForgotPasswordEmail(String username);

    void validatePasswordResetToken(int id, String token);

}
