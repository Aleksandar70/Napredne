package com.msgnetconomy.appraisalsheet.dao;

import com.msgnetconomy.appraisalsheet.domain.AppraisalSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppraisalSheetDAO extends JpaRepository<AppraisalSheetEntity, Integer> {

    @Query("SELECT ash FROM AppraisalSheetEntity ash WHERE ash.employeeName=?1 AND ash.appraisalPeriod=?2")
    AppraisalSheetEntity findByNameAndAppPeriod(String employeeName, String appraisalPeriod);

    @Query("SELECT ash FROM AppraisalSheetEntity ash WHERE ash.user.userId=?1")
    List<AppraisalSheetEntity> findAppDocumentsByUserId(Integer userId);

    @Query("SELECT ash FROM AppraisalSheetEntity ash WHERE ash.user.firstName=?1 AND ash.user.lastName=?2")
    AppraisalSheetEntity getAppraisalSheetByFirstNameAndLastName(String firstName, String lastName);
}
