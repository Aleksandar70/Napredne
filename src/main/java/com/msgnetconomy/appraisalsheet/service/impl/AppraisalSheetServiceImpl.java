package com.msgnetconomy.appraisalsheet.service.impl;

import com.msgnetconomy.appraisalsheet.dao.AppraisalSheetDAO;
import com.msgnetconomy.appraisalsheet.dao.UserDAO;
import com.msgnetconomy.appraisalsheet.domain.AppraisalSheetEntity;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.service.AppraisalSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AppraisalSheetServiceImpl implements AppraisalSheetService {

    @Autowired
    private AppraisalSheetDAO appraisalSheetDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public AppraisalSheetEntity saveOrUpdateAppraisalSheet(AppraisalSheetEntity appraisalSheetEntity) {
        int appraisalSheetID = appraisalSheetEntity.getAppraisalSheetID();
        boolean existsById = appraisalSheetDAO.existsById(appraisalSheetID);
        AppraisalSheetEntity byNameAndAppPeriod = appraisalSheetDAO.findByNameAndAppPeriod(appraisalSheetEntity.getEmployeeName(), appraisalSheetEntity.getAppraisalPeriod());
        AppraisalSheetEntity oldSheet = new AppraisalSheetEntity();
        if (existsById) {
            oldSheet = (appraisalSheetDAO.findById(appraisalSheetID)).get();
        }
        if (Objects.nonNull(byNameAndAppPeriod)) {
            oldSheet = byNameAndAppPeriod;
        }
        if (existsById || Objects.nonNull(byNameAndAppPeriod)) {
            if (Objects.nonNull(appraisalSheetEntity.getUser())) {
                oldSheet.setUser(appraisalSheetEntity.getUser());
            }
            if (Objects.nonNull(appraisalSheetEntity.getDate())) {
                oldSheet.setDate(appraisalSheetEntity.getDate());
            }
            if (Objects.nonNull(appraisalSheetEntity.getEmployeeName())) {
                oldSheet.setEmployeeName(appraisalSheetEntity.getEmployeeName());
            }
            if (Objects.nonNull(appraisalSheetEntity.getDivision())) {
                oldSheet.setDivision(appraisalSheetEntity.getDivision());
            }
            if (Objects.nonNull(appraisalSheetEntity.getCareerLevel())) {
                oldSheet.setCareerLevel(appraisalSheetEntity.getCareerLevel());
            }
            if (Objects.nonNull(appraisalSheetEntity.getAppraisalPeriod())) {
                oldSheet.setAppraisalPeriod(appraisalSheetEntity.getAppraisalPeriod());
            }
            if (Objects.nonNull(appraisalSheetEntity.getManager())) {
                oldSheet.setManager(appraisalSheetEntity.getManager());
            }
            if (Objects.nonNull(appraisalSheetEntity.getTasksBackdated())) {
                oldSheet.setTasksBackdated(appraisalSheetEntity.getTasksBackdated());
            }
            if (Objects.nonNull(appraisalSheetEntity.getTeamLeadFeedback())) {
                oldSheet.setTeamLeadFeedback(appraisalSheetEntity.getTeamLeadFeedback());
            }
            if (Objects.nonNull(appraisalSheetEntity.getCompanyFeedback())) {
                oldSheet.setCompanyFeedback(appraisalSheetEntity.getCompanyFeedback());
            }
            if (Objects.nonNull(appraisalSheetEntity.getTargetsBackdated())) {
                oldSheet.setTargetsBackdated(appraisalSheetEntity.getTargetsBackdated());
            }
            if (Objects.nonNull(appraisalSheetEntity.getRoleRequirements())) {
                oldSheet.setRoleRequirements(appraisalSheetEntity.getRoleRequirements());
            }
            if (Objects.nonNull(appraisalSheetEntity.getSelfCompetence())) {
                oldSheet.setSelfCompetence(appraisalSheetEntity.getSelfCompetence());
            }
            if (Objects.nonNull(appraisalSheetEntity.getSocialCompetence())) {
                oldSheet.setSocialCompetence(appraisalSheetEntity.getSocialCompetence());
            }
            if (Objects.nonNull(appraisalSheetEntity.getMethodicalCompetence())) {
                oldSheet.setMethodicalCompetence(appraisalSheetEntity.getMethodicalCompetence());
            }
            if (Objects.nonNull(appraisalSheetEntity.getRoleRequirementsGoals())) {
                oldSheet.setRoleRequirementsGoals(appraisalSheetEntity.getRoleRequirementsGoals());
            }
            if (Objects.nonNull(appraisalSheetEntity.getSelfCompetenceGoals())) {
                oldSheet.setSelfCompetenceGoals(appraisalSheetEntity.getSelfCompetenceGoals());
            }
            if (Objects.nonNull(appraisalSheetEntity.getCompanyOrientedGoals())) {
                oldSheet.setCompanyOrientedGoals(appraisalSheetEntity.getCompanyOrientedGoals());
            }
            if (Objects.nonNull(appraisalSheetEntity.getEconomicGoal())) {
                oldSheet.setEconomicGoal(appraisalSheetEntity.getEconomicGoal());
            }
            if (Objects.nonNull(appraisalSheetEntity.getDevelopmentObjectives())) {
                oldSheet.setDevelopmentObjectives(appraisalSheetEntity.getDevelopmentObjectives());
            }
            if (Objects.nonNull(appraisalSheetEntity.getDevelopmentPotential())) {
                oldSheet.setDevelopmentPotential(appraisalSheetEntity.getDevelopmentPotential());
            }
            if (Objects.nonNull(appraisalSheetEntity.getEmployeeExpectations())) {
                oldSheet.setEmployeeExpectations(appraisalSheetEntity.getEmployeeExpectations());
            }
            oldSheet.setLocked(appraisalSheetEntity.isLocked());
            if (Objects.isNull(appraisalSheetEntity.getUser())) {
                oldSheet.setUser(findByFirstNameAndLastName(appraisalSheetEntity));
            }
            return appraisalSheetDAO.save(oldSheet);
        } else {
            if (Objects.isNull(appraisalSheetEntity.getUser())) {
                appraisalSheetEntity.setUser(findByFirstNameAndLastName(appraisalSheetEntity));
            }
            return appraisalSheetDAO.save(appraisalSheetEntity);
        }

    }

    @Override
    public AppraisalSheetEntity findByNameAndAppPeriod(String employeeName, String appraisalPeriod) {
        return appraisalSheetDAO.findByNameAndAppPeriod(employeeName, appraisalPeriod);
    }

    @Override
    public List<AppraisalSheetEntity> getAllAppraisalSheets() {
        return appraisalSheetDAO.findAll();
    }

    @Override
    public UserEntity findByFirstNameAndLastName(AppraisalSheetEntity appraisalSheetEntity) {
        return userDAO.findByFirstNameAndLastName(appraisalSheetEntity.getEmployeeName());
    }

    @Override
    public AppraisalSheetEntity getAppraisalSheetByFirstNameAndLastName(String firstName, String lastName) {
        return appraisalSheetDAO.getAppraisalSheetByFirstNameAndLastName(firstName, lastName);
    }
}
