package com.msgnetconomy.appraisalsheet.service;

import com.msgnetconomy.appraisalsheet.domain.AppraisalSheetEntity;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;

import java.util.List;

public interface AppraisalSheetService {

    AppraisalSheetEntity saveOrUpdateAppraisalSheet(AppraisalSheetEntity appraisalSheetEntity);

    AppraisalSheetEntity findByNameAndAppPeriod(String employeeName, String appraisalPeriod);

    List<AppraisalSheetEntity> getAllAppraisalSheets();

    UserEntity findByFirstNameAndLastName(AppraisalSheetEntity appraisalSheetEntity);

    AppraisalSheetEntity getAppraisalSheetByFirstNameAndLastName(String firstName, String lastName);
}
