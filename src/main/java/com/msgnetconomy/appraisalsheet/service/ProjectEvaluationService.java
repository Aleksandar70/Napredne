package com.msgnetconomy.appraisalsheet.service;

import com.msgnetconomy.appraisalsheet.dto.ProjectEvaluationDto;
import com.msgnetconomy.appraisalsheet.dto.UserDto;

import java.util.List;

public interface ProjectEvaluationService {

    ProjectEvaluationDto saveOrUpdateProjectEvaluation(ProjectEvaluationDto projectEvaluationDto);

    UserDto findByFirstNameAndLastName(ProjectEvaluationDto projectEvaluationDto);

    List<ProjectEvaluationDto> findAppDocumentsByUser(String loggedInUser);

    List<ProjectEvaluationDto> findAllAppraisalSheet();

    void lockAppraisalSheet(ProjectEvaluationDto projectEvaluationDto);
}
