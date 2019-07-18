package com.msgnetconomy.appraisalsheet.dao;

import com.msgnetconomy.appraisalsheet.domain.AppraisalSheetEntity;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProjectEvaluationDAO extends JpaRepository<AppraisalSheetEntity, Integer> {

    @Query("SELECT ash FROM AppraisalSheetEntity ash WHERE ash.employeeName=?1 AND ash.appraisalPeriod=?2")
    AppraisalSheetEntity findByNameAndAppPeriod(String employeeName, String appraisalPeriod);

    @Query("SELECT ash FROM AppraisalSheetEntity ash WHERE ash.user.userId=?1")
    List<AppraisalSheetEntity> findAppDocumentsByUserId(Integer userId);

    @Query("SELECT u.userId FROM UserEntity u WHERE u.username=?1")
    Integer getUserIdByUsername (String username);

    @Query("SELECT u.userGroup.userGroupId FROM UserEntity u WHERE u.username=?1")
    Integer getUserGroupIdByUsername (String username);

    @Query("SELECT um.userManagerId FROM UserManagerEntity um WHERE um.managerUsername=?1")
    Integer getUserManagerIdByUsername(String username);

    @Query("SELECT u FROM UserEntity u WHERE u.userManager.userManagerId=?1")
    List<UserEntity> getAllUsersOfTheSameManager(Integer userManagerId);

    @Query("SELECT ash FROM AppraisalSheetEntity ash WHERE ash.user.userId=?1 AND ash.locked=true")
    List<AppraisalSheetEntity> getLockedAppDocumentsByUserId(Integer userId);

    @Transactional
    @Modifying
    @Query("UPDATE AppraisalSheetEntity ash SET ash.locked=0 WHERE ash.appraisalSheetID=?1")
    void lockAppraisalSheet(Integer appraisalSheetID);
}
