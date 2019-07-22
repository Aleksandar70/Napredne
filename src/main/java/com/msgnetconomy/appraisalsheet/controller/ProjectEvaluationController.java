package com.msgnetconomy.appraisalsheet.controller;

import com.msgnetconomy.appraisalsheet.dto.ProjectEvaluationDto;
import com.msgnetconomy.appraisalsheet.service.ProjectEvaluationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ProjectEvaluationController {

    private static final String API = "http://localhost:4200";
    private static final String ADD_APPRAISAL_SHEET = "/add-appraisal-sheet";
    private static final String ARCHIVE_PAGE = "/archive";
    private static final String GET_ALL_APPRAISAL_SHEET = "/get-appraisal-sheet";
    private static final Logger logger = LoggerFactory.getLogger(ProjectEvaluationController.class);
    private static final String URL_LOCK_APPRAISAL_SHEET = "/lock-appraisal-sheet";
    public static final String ERROR_SAVING_APPRAISAL_SHEET = "An exception occurred while saving appraisal sheet!";

    @Autowired
    private ProjectEvaluationService projectEvaluationService;

    @PostMapping(value = ADD_APPRAISAL_SHEET)
    @CrossOrigin(origins = API)
    ProjectEvaluationDto addAppraisalSheet(@RequestBody ProjectEvaluationDto projectEvaluationDto, HttpServletResponse response) {
        try {
            ProjectEvaluationDto newAppSheet = projectEvaluationService.saveOrUpdateProjectEvaluation(projectEvaluationDto);
            response.setStatus(HttpServletResponse.SC_OK);
            return newAppSheet;
        } catch (Exception e) {
            logger.error(ERROR_SAVING_APPRAISAL_SHEET, e);
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return new ProjectEvaluationDto();
        }
    }

    @GetMapping(value = ARCHIVE_PAGE)
    @CrossOrigin(origins = API)
    List<ProjectEvaluationDto> findAppDocumentsByUser(@RequestParam String userName) {
        return projectEvaluationService.findAppDocumentsByUser(userName);
    }

    @GetMapping(value = GET_ALL_APPRAISAL_SHEET)
    @CrossOrigin(origins = API)
    List<ProjectEvaluationDto> findAllAppraisalSheet() {
        return projectEvaluationService.findAllAppraisalSheet();
    }

    @PostMapping(value = URL_LOCK_APPRAISAL_SHEET)
    @CrossOrigin(origins = API)
    void lockAppraisalSheet(@RequestBody ProjectEvaluationDto projectEvaluationDto) {
        projectEvaluationService.lockAppraisalSheet(projectEvaluationDto);
    }
}
