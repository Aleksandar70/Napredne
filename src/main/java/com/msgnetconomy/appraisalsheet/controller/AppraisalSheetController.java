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
public class AppraisalSheetController {

    private static final String API = "http://localhost:4200";
    private static final String ADD_APPRAISAL_SHEET = "/add-appraisal-sheet";
    private static final String ARCHIVE_PAGE = "/archive-page";
    private static final String GET_ALL_APPRAISAL_SHEET = "/get-appraisal-sheet";
    private static final Logger logger = LoggerFactory.getLogger(AppraisalSheetController.class);
    private static final String URL_LOCK_APPRAISAL_SHEET = "/lock-appraisal-sheet";
    public static final String ERROR_SAVING_APPRAISAL_SHEET = "An exception occurred while saving appraisal sheet!";

    @Autowired
    private AppraisalSheetService appraisalSheetService;

    @PostMapping(value = ADD_APPRAISAL_SHEET)
    @CrossOrigin(origins = API)
    AppraisalSheetEntity addAppraisalSheet(@RequestBody AppraisalSheetEntity appraisalSheetEntity, HttpServletResponse response) {
        try {
            AppraisalSheetEntity newAppSheet = appraisalSheetService.saveOrUpdateAppraisalSheet(appraisalSheetEntity);
            response.setStatus(HttpServletResponse.SC_OK);
            return newAppSheet;
        } catch (Exception e) {
            logger.error(ERROR_SAVING_APPRAISAL_SHEET, e);
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return new AppraisalSheetEntity();
        }
    }

    @GetMapping(value = ARCHIVE_PAGE)
    @CrossOrigin(origins = API)
    List<AppraisalSheetEntity> findAppDocumentsByUser(@RequestParam String userName) {
        return appraisalSheetService.findAppDocumentsByUser(userName);
    }

    @GetMapping(value = GET_ALL_APPRAISAL_SHEET)
    @CrossOrigin(origins = API)
    List<AppraisalSheetEntity> findAllAppraisalSheet() {
        return appraisalSheetService.findAllAppraisalSheet();
    }

    @PostMapping(value = URL_LOCK_APPRAISAL_SHEET)
    @CrossOrigin(origins = API)
    void lockAppraisalSheet(@RequestBody AppraisalSheetEntity appraisalSheetEntity) {
        appraisalSheetService.lockAppraisalSheet(appraisalSheetEntity);
    }
}
