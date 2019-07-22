package com.msgnetconomy.appraisalsheet.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectEvaluationDto {

    private int projectEvaluationID;
    private UserDto userDto;
    private Date date;
    private String employeeName;
    private String projectName;
    private String careerLevel;
    private String appraisalPeriod;
    private String manager;
    private String financialSituation;
    private String tasksDifficult;
    private String scope;
    private String functionalSpecification;
    private String hardToFollow;
    private String independent;
    private String suggestions;
    private String projectInFiveMonths;
    private String obstacles;
    private String best_sides_highlights;
    private String humanResources;
    private String peopleSatisfaction;
    private String feedbackFromClient;
    private String improvingProcess;
    private String time;
    private boolean locked;

    public ProjectEvaluationDto(int projectEvaluationID, UserDto userDto, Date date, String employeeName, String projectName, String careerLevel, String appraisalPeriod, String manager, String financialSituation, String tasksDifficult, String scope, String functionalSpecification, String hardToFollow, String independent, String suggestions, String projectInFiveMonths, String obstacles, String best_sides_highlights, String humanResources, String peopleSatisfaction, String feedbackFromClient, String improvingProcess, String time, boolean locked) {
        this.projectEvaluationID = projectEvaluationID;
        this.userDto = userDto;
        this.date = date;
        this.employeeName = employeeName;
        this.projectName = projectName;
        this.careerLevel = careerLevel;
        this.appraisalPeriod = appraisalPeriod;
        this.manager = manager;
        this.financialSituation = financialSituation;
        this.tasksDifficult = tasksDifficult;
        this.scope = scope;
        this.functionalSpecification = functionalSpecification;
        this.hardToFollow = hardToFollow;
        this.independent = independent;
        this.suggestions = suggestions;
        this.projectInFiveMonths = projectInFiveMonths;
        this.obstacles = obstacles;
        this.best_sides_highlights = best_sides_highlights;
        this.humanResources = humanResources;
        this.peopleSatisfaction = peopleSatisfaction;
        this.feedbackFromClient = feedbackFromClient;
        this.improvingProcess = improvingProcess;
        this.time = time;
        this.locked = locked;
    }

    public ProjectEvaluationDto() {
    }
}
