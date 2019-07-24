package com.msgnetconomy.appraisalsheet.service.impl;

import com.msgnetconomy.appraisalsheet.dao.ProjectEvaluationDAO;
import com.msgnetconomy.appraisalsheet.dao.UserDAO;
import com.msgnetconomy.appraisalsheet.domain.ProjectEvaluationEntity;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.dto.ProjectEvaluationDto;
import com.msgnetconomy.appraisalsheet.dto.UserDto;
import com.msgnetconomy.appraisalsheet.service.ProjectEvaluationService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectEvaluationServiceImpl implements ProjectEvaluationService {

    @Autowired
    private ProjectEvaluationDAO projectEvaluationDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public ProjectEvaluationDto saveOrUpdateProjectEvaluation(ProjectEvaluationDto projectEvaluationDto) {
        ProjectEvaluationEntity projectEvaluation = mapper.map(projectEvaluationDto, ProjectEvaluationEntity.class);
        int projectEvaluationID = projectEvaluationDto.getProjectEvaluationID();
        boolean existsById = projectEvaluationDAO.existsById(projectEvaluationID);
        ProjectEvaluationEntity byNameAndAppPeriod = projectEvaluationDAO.findByNameAndAppPeriod(projectEvaluation.getEmployeeName(),
                projectEvaluation.getAppraisalPeriod());
        ProjectEvaluationDto oldSheet = null;
        if (existsById) {
            oldSheet = mapper.map((projectEvaluationDAO.findById(projectEvaluationID)).get(), ProjectEvaluationDto.class);
        } else if (Objects.nonNull(byNameAndAppPeriod)) {
            oldSheet = mapper.map(byNameAndAppPeriod, ProjectEvaluationDto.class);
        }
        if (existsById || Objects.nonNull(byNameAndAppPeriod)) {
            updateProjectEval(oldSheet, projectEvaluationDto);
            return mapper.map(projectEvaluationDAO.save(mapper.map(oldSheet, ProjectEvaluationEntity.class)), ProjectEvaluationDto.class);
        } else {
            if (Objects.isNull(projectEvaluationDto.getUserDto())) {
                projectEvaluationDto.setUserDto(findByFirstNameAndLastName(projectEvaluationDto));
            }
            return mapper.map(projectEvaluationDAO.save(mapper.map(projectEvaluationDto, ProjectEvaluationEntity.class)), ProjectEvaluationDto.class);
        }
    }

    private void updateProjectEval(ProjectEvaluationDto oldSheet, ProjectEvaluationDto projectEvaluationDto) {
        if (Objects.nonNull(projectEvaluationDto.getUserDto())) {
            oldSheet.setUserDto(projectEvaluationDto.getUserDto());
        }
        if (Objects.nonNull(projectEvaluationDto.getDate())) {
            oldSheet.setDate(projectEvaluationDto.getDate());
        }
        if (Objects.nonNull(projectEvaluationDto.getEmployeeName())) {
            oldSheet.setEmployeeName(projectEvaluationDto.getEmployeeName());
        }
        if (Objects.nonNull(projectEvaluationDto.getProjectName())) {
            oldSheet.setProjectName(projectEvaluationDto.getProjectName());
        }
        if (Objects.nonNull(projectEvaluationDto.getCareerLevel())) {
            oldSheet.setCareerLevel(projectEvaluationDto.getCareerLevel());
        }
        if (Objects.nonNull(projectEvaluationDto.getAppraisalPeriod())) {
            oldSheet.setAppraisalPeriod(projectEvaluationDto.getAppraisalPeriod());
        }
        if (Objects.nonNull(projectEvaluationDto.getManager())) {
            oldSheet.setManager(projectEvaluationDto.getManager());
        }
        if (Objects.nonNull(projectEvaluationDto.getFinancialSituation())) {
            oldSheet.setFinancialSituation(projectEvaluationDto.getFinancialSituation());
        }
        if (Objects.nonNull(projectEvaluationDto.getTasksDifficult())) {
            oldSheet.setTasksDifficult(projectEvaluationDto.getTasksDifficult());
        }
        if (Objects.nonNull(projectEvaluationDto.getScope())) {
            oldSheet.setScope(projectEvaluationDto.getScope());
        }
        if (Objects.nonNull(projectEvaluationDto.getFunctionalSpecification())) {
            oldSheet.setFunctionalSpecification(projectEvaluationDto.getFunctionalSpecification());
        }
        if (Objects.nonNull(projectEvaluationDto.getHardToFollow())) {
            oldSheet.setHardToFollow(projectEvaluationDto.getHardToFollow());
        }
        if (Objects.nonNull(projectEvaluationDto.getIndependent())) {
            oldSheet.setIndependent(projectEvaluationDto.getIndependent());
        }
        if (Objects.nonNull(projectEvaluationDto.getSuggestions())) {
            oldSheet.setSuggestions(projectEvaluationDto.getSuggestions());
        }
        if (Objects.nonNull(projectEvaluationDto.getProjectInFiveMonths())) {
            oldSheet.setProjectInFiveMonths(projectEvaluationDto.getProjectInFiveMonths());
        }
        if (Objects.nonNull(projectEvaluationDto.getObstacles())) {
            oldSheet.setObstacles(projectEvaluationDto.getObstacles());
        }
        if (Objects.nonNull(projectEvaluationDto.getBest_sides_highlights())) {
            oldSheet.setBest_sides_highlights(projectEvaluationDto.getBest_sides_highlights());
        }
        if (Objects.nonNull(projectEvaluationDto.getHumanResources())) {
            oldSheet.setHumanResources(projectEvaluationDto.getHumanResources());
        }
        if (Objects.nonNull(projectEvaluationDto.getPeopleSatisfaction())) {
            oldSheet.setPeopleSatisfaction(projectEvaluationDto.getPeopleSatisfaction());
        }
        if (Objects.nonNull(projectEvaluationDto.getFeedbackFromClient())) {
            oldSheet.setFeedbackFromClient(projectEvaluationDto.getFeedbackFromClient());
        }
        if (Objects.nonNull(projectEvaluationDto.getImprovingProcess())) {
            oldSheet.setImprovingProcess(projectEvaluationDto.getImprovingProcess());
        }
        if (Objects.nonNull(projectEvaluationDto.getTime())) {
            oldSheet.setTime(projectEvaluationDto.getTime());
        }
        oldSheet.setLocked(projectEvaluationDto.isLocked());
        if (Objects.isNull(projectEvaluationDto.getUserDto())) {
            oldSheet.setUserDto(findByFirstNameAndLastName(projectEvaluationDto));
        }
    }

    @Override
    public List<ProjectEvaluationDto> findAppDocumentsByUser(String userName) {
        int userId = projectEvaluationDAO.getUserIdByUsername(userName);
        int userGroupId = projectEvaluationDAO.getUserGroupIdByUsername(userName);
        return populateAppraisalDocuments(userName, userId, userGroupId);
    }

    private List<ProjectEvaluationDto> populateAppraisalDocuments(String userName, int userId, int userGroupId) {
        List<ProjectEvaluationDto> appraisalDocuments = new ArrayList<>();
        if (userGroupId == 1) {
            appraisalDocuments = projectEvaluationDAO.findAppDocumentsByUserId(userId)
                    .stream()
                    .map(entity -> mapper.map(entity, ProjectEvaluationDto.class))
                    .collect(Collectors.toList());
        } else if (userGroupId == 2) {
            int managerOfTheUser = projectEvaluationDAO.getUserManagerIdByUsername(userName);
            appraisalDocuments = projectEvaluationDAO.findAppDocumentsByUserId(userId)
                    .stream()
                    .map(entity -> mapper.map(entity, ProjectEvaluationDto.class))
                    .collect(Collectors.toList());
            List<UserEntity> usersOfTheSameManager = projectEvaluationDAO.getAllUsersOfTheSameManager(managerOfTheUser);
            Map<Integer, List<ProjectEvaluationEntity>> usersDocuments = new HashMap<>();
            usersOfTheSameManager.stream().forEach(user -> usersDocuments.put(user.getUserId(), projectEvaluationDAO.getLockedAppDocumentsByUserId(user.getUserId())));
            for (Map.Entry<Integer, List<ProjectEvaluationEntity>> documentsOfEachUser : usersDocuments.entrySet()) {
                appraisalDocuments.addAll(documentsOfEachUser.getValue().stream().map(entity -> mapper.map(entity, ProjectEvaluationDto.class)).collect(Collectors.toList()));
            }
        } else if (userGroupId == 3) {
            appraisalDocuments = projectEvaluationDAO.findAll().stream().map(entity -> mapper.map(entity, ProjectEvaluationDto.class)).collect(Collectors.toList());
            appraisalDocuments = appraisalDocuments.stream().filter(document -> document.isLocked()).collect(Collectors.toList());
        }
        return appraisalDocuments;
    }

    @Override
    public List<ProjectEvaluationDto> findAllAppraisalSheet() {
        return projectEvaluationDAO.findAll()
                .stream()
                .map(entity -> mapper.map(entity, ProjectEvaluationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void lockAppraisalSheet(ProjectEvaluationDto projectEvaluationDto) {
        ProjectEvaluationEntity projectEvaluation = mapper.map(projectEvaluationDto, ProjectEvaluationEntity.class);
        projectEvaluationDAO.lockAppraisalSheet(projectEvaluation.getProjectEvaluationID());
    }

    @Override
    public UserDto findByFirstNameAndLastName(ProjectEvaluationDto projectEvaluationDto) {
        ProjectEvaluationEntity projectEvaluation = mapper.map(projectEvaluationDto, ProjectEvaluationEntity.class);
        return mapper.map(userDAO.findByFirstNameAndLastName(projectEvaluation.getEmployeeName()), UserDto.class);
    }
}
