package com.msgnetconomy.appraisalsheet.service;

import com.msgnetconomy.appraisalsheet.domain.AppraisalSheetEntity;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;

import java.util.List;

public interface AppraisalSheetService {

    AppraisalSheetEntity saveOrUpdateAppraisalSheet(AppraisalSheetEntity appraisalSheetEntity);

    UserEntity findByFirstNameAndLastName(AppraisalSheetEntity appraisalSheetEntity);

    List<AppraisalSheetEntity> findAppDocumentsByUser(String loggedInUser);

    List<AppraisalSheetEntity> findAllAppraisalSheet();

    void lockAppraisalSheet(AppraisalSheetEntity appraisalSheetEntity);
}
