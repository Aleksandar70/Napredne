package com.msgnetconomy.appraisalsheet.controller;

import com.msgnetconomy.appraisalsheet.domain.AppraisalSheetEntity;
import com.msgnetconomy.appraisalsheet.service.AppraisalSheetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class AppraisalSheetController {
    private static final String ADD_APPRAISAL_SHEET = "/add-appraisal-sheet";
    private static final String ARCHIVE_PAGE = "/archive-page";
    private static final Logger logger = LoggerFactory.getLogger(AppraisalSheetController.class);
    private static final String GET_APPRAISAL_SHEET = "/get-appraisal-sheet";

    private AppraisalSheetService appraisalSheetService;

    @PostMapping(value = ADD_APPRAISAL_SHEET)
    AppraisalSheetEntity addAppraisalSheet(@RequestBody AppraisalSheetEntity appraisalSheetEntity, HttpServletResponse response) {
        try {
            AppraisalSheetEntity newAppSheet = appraisalSheetService.saveOrUpdateAppraisalSheet(appraisalSheetEntity);
            response.setStatus(HttpServletResponse.SC_OK);
            return newAppSheet;
        } catch (Exception e) {
            logger.error("An exception occurred while saving appraisal sheet!", e);
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return new AppraisalSheetEntity();
        }
    }

    @GetMapping(value = ARCHIVE_PAGE)
    List<AppraisalSheetEntity> getAllAppraisalSheets() {
        return appraisalSheetService.getAllAppraisalSheets();
    }

    @GetMapping(value = GET_APPRAISAL_SHEET)
    AppraisalSheetEntity getAppraisalSheetByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return appraisalSheetService.getAppraisalSheetByFirstNameAndLastName(firstName, lastName);
    }
}
