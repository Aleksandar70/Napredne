package com.msgnetconomy.appraisalsheet.dao;

import com.msgnetconomy.appraisalsheet.domain.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.Optional;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface ResetPasswordDAO extends JpaRepository<PasswordResetTokenEntity, Integer> {

    @Query("SELECT prt FROM PasswordResetTokenEntity prt WHERE prt.token = ?1")
    Optional<PasswordResetTokenEntity> findByToken(String token);

    @Query("SELECT prt FROM PasswordResetTokenEntity prt WHERE prt.user.userId = ?1")
    PasswordResetTokenEntity findByUserId(int userId);

    @Transactional
    @Modifying
    @Query("UPDATE PasswordResetTokenEntity prt SET prt.token = ?2, prt.expiryDate = ?3 WHERE prt.passwordResetTokenId = ?1")
    void update(int passwordResetTokenId, String token, Date expiryDate);

    @Transactional
    @Modifying
    @Query("DELETE FROM PasswordResetTokenEntity prt WHERE prt.user.userId = ?1")
    void deleteByUserId(int userId);
}
