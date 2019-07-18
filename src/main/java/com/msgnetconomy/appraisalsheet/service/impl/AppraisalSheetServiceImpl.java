package com.msgnetconomy.appraisalsheet.service.impl;

import com.msgnetconomy.appraisalsheet.dao.ProjectEvaluationDAO;
import com.msgnetconomy.appraisalsheet.dao.UserDAO;
import com.msgnetconomy.appraisalsheet.domain.AppraisalSheetEntity;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.service.AppraisalSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppraisalSheetServiceImpl implements AppraisalSheetService {

    @Autowired
    private ProjectEvaluationDAO projectEvaluationDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public AppraisalSheetEntity saveOrUpdateAppraisalSheet(AppraisalSheetEntity appraisalSheetEntity) {
        int appraisalSheetID = appraisalSheetEntity.getAppraisalSheetID();
        boolean existsById = projectEvaluationDAO.existsById(appraisalSheetID);
        AppraisalSheetEntity byNameAndAppPeriod = projectEvaluationDAO.findByNameAndAppPeriod(appraisalSheetEntity.getEmployeeName(), appraisalSheetEntity.getAppraisalPeriod());
        AppraisalSheetEntity oldSheet = new AppraisalSheetEntity();
        if (existsById) {
            oldSheet = (projectEvaluationDAO.findById(appraisalSheetID)).get();
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
            if (Objects.nonNull(appraisalSheetEntity.getProjectName())) {
                oldSheet.setProjectName(appraisalSheetEntity.getProjectName());
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
            if (Objects.nonNull(appraisalSheetEntity.getFinancialSituation())) {
                oldSheet.setFinancialSituation(appraisalSheetEntity.getFinancialSituation());
            }
            if (Objects.nonNull(appraisalSheetEntity.getTasksDifficult())) {
                oldSheet.setTasksDifficult(appraisalSheetEntity.getTasksDifficult());
            }
            if (Objects.nonNull(appraisalSheetEntity.getScope())) {
                oldSheet.setScope(appraisalSheetEntity.getScope());
            }
            if (Objects.nonNull(appraisalSheetEntity.getFunctionalSpecification())) {
                oldSheet.setFunctionalSpecification(appraisalSheetEntity.getFunctionalSpecification());
            }
            if (Objects.nonNull(appraisalSheetEntity.getHardToFollow())) {
                oldSheet.setHardToFollow(appraisalSheetEntity.getHardToFollow());
            }
            if (Objects.nonNull(appraisalSheetEntity.getIndependent())) {
                oldSheet.setIndependent(appraisalSheetEntity.getIndependent());
            }
            if (Objects.nonNull(appraisalSheetEntity.getSuggestions())) {
                oldSheet.setSuggestions(appraisalSheetEntity.getSuggestions());
            }
            if (Objects.nonNull(appraisalSheetEntity.getProjectInFiveMonths())) {
                oldSheet.setProjectInFiveMonths(appraisalSheetEntity.getProjectInFiveMonths());
            }
            if (Objects.nonNull(appraisalSheetEntity.getObstacles())) {
                oldSheet.setObstacles(appraisalSheetEntity.getObstacles());
            }
            if (Objects.nonNull(appraisalSheetEntity.getBest_sides_highlights())) {
                oldSheet.setBest_sides_highlights(appraisalSheetEntity.getBest_sides_highlights());
            }
            if (Objects.nonNull(appraisalSheetEntity.getHumanResources())) {
                oldSheet.setHumanResources(appraisalSheetEntity.getHumanResources());
            }
            if (Objects.nonNull(appraisalSheetEntity.getPeopleSatisfaction())) {
                oldSheet.setPeopleSatisfaction(appraisalSheetEntity.getPeopleSatisfaction());
            }
            if (Objects.nonNull(appraisalSheetEntity.getFeedbackFromClient())) {
                oldSheet.setFeedbackFromClient(appraisalSheetEntity.getFeedbackFromClient());
            }
            if (Objects.nonNull(appraisalSheetEntity.getImprovingProcess())) {
                oldSheet.setImprovingProcess(appraisalSheetEntity.getImprovingProcess());
            }
            if (Objects.nonNull(appraisalSheetEntity.getTime())) {
                oldSheet.setTime(appraisalSheetEntity.getTime());
            }
            oldSheet.setLocked(appraisalSheetEntity.isLocked());
            if (Objects.isNull(appraisalSheetEntity.getUser())) {
                oldSheet.setUser(findByFirstNameAndLastName(appraisalSheetEntity));
            }
            return projectEvaluationDAO.save(oldSheet);
        } else {
            if (Objects.isNull(appraisalSheetEntity.getUser())) {
                appraisalSheetEntity.setUser(findByFirstNameAndLastName(appraisalSheetEntity));
            }
            return projectEvaluationDAO.save(appraisalSheetEntity);
        }

    }

    @Override
    public List<AppraisalSheetEntity> findAppDocumentsByUser(String userName) {
        List<AppraisalSheetEntity> appraisalDocuments = new ArrayList<>();
        List<AppraisalSheetEntity> lockedDocuments = new ArrayList<>();
        int userId = projectEvaluationDAO.getUserIdByUsername(userName);
        int userGroupId = projectEvaluationDAO.getUserGroupIdByUsername(userName);
        if (userGroupId == 1) {
            appraisalDocuments = projectEvaluationDAO.findAppDocumentsByUserId(userId);
        }
        if (userGroupId == 2) {
            int managerOfTheUser = projectEvaluationDAO.getUserManagerIdByUsername(userName);
            appraisalDocuments = projectEvaluationDAO.findAppDocumentsByUserId(userId);
            List<UserEntity> usersOfTheSameManager = projectEvaluationDAO.getAllUsersOfTheSameManager(managerOfTheUser);
            Map<Integer, List<AppraisalSheetEntity>> usersDocuments = new HashMap<>();
            usersOfTheSameManager.stream().forEach(user -> usersDocuments.put(user.getUserId(), projectEvaluationDAO.getLockedAppDocumentsByUserId(user.getUserId())));
            for (Map.Entry<Integer, List<AppraisalSheetEntity>> documentsOfEachUser : usersDocuments.entrySet()) {
                appraisalDocuments.addAll(documentsOfEachUser.getValue());
            }
        }
        if (userGroupId == 3) {
            appraisalDocuments = projectEvaluationDAO.findAll();
            appraisalDocuments.forEach(value -> {
                if (value.isLocked()) {
                    lockedDocuments.add(value);
                }
            });
            return lockedDocuments;
        }
        return appraisalDocuments;
    }

    @Override
    public List<AppraisalSheetEntity> findAllAppraisalSheet() {
        return projectEvaluationDAO.findAll();
    }

    @Override
    public void lockAppraisalSheet(AppraisalSheetEntity appraisalSheetEntity) {
        projectEvaluationDAO.lockAppraisalSheet(appraisalSheetEntity.getAppraisalSheetID());
    }

    @Override
    public UserEntity findByFirstNameAndLastName(AppraisalSheetEntity appraisalSheetEntity) {
        return userDAO.findByFirstNameAndLastName(appraisalSheetEntity.getEmployeeName());
    }
}
