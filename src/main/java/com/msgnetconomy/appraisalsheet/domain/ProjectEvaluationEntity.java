package com.msgnetconomy.appraisalsheet.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "project_evaluation", schema = "appraisal-sheet-mysql")
public class ProjectEvaluationEntity {

    private int projectEvaluationID;
    private UserEntity user;
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

    @Id
    @Column(name = "project_evaluation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getProjectEvaluationID() {
        return projectEvaluationID;
    }

    public void setProjectEvaluationID(int projectEvaluationID) {
        this.projectEvaluationID = projectEvaluationID;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userID")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "employee_name")
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Basic
    @Column(name = "project_name")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "career_level")
    public String getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(String careerLevel) {
        this.careerLevel = careerLevel;
    }

    @Basic
    @Column(name = "appraisal_period")
    public String getAppraisalPeriod() {
        return appraisalPeriod;
    }

    public void setAppraisalPeriod(String appraisalPeriod) {
        this.appraisalPeriod = appraisalPeriod;
    }

    @Basic
    @Column(name = "manager")
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Basic
    @Column(name = "financial_situation")
    public String getFinancialSituation() {
        return financialSituation;
    }

    public void setFinancialSituation(String financialSituation) {
        this.financialSituation = financialSituation;
    }

    @Basic
    @Column(name = "tasks_difficult")
    public String getTasksDifficult() {
        return tasksDifficult;
    }

    public void setTasksDifficult(String tasksDifficult) {
        this.tasksDifficult = tasksDifficult;
    }

    @Basic
    @Column(name = "scope")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "functional_specification")
    public String getFunctionalSpecification() {
        return functionalSpecification;
    }

    public void setFunctionalSpecification(String functionalSpecification) {
        this.functionalSpecification = functionalSpecification;
    }

    @Basic
    @Column(name = "hard_to_follow")
    public String getHardToFollow() {
        return hardToFollow;
    }

    public void setHardToFollow(String hardToFollow) {
        this.hardToFollow = hardToFollow;
    }

    @Basic
    @Column(name = "independent")
    public String getIndependent() {
        return independent;
    }

    public void setIndependent(String independent) {
        this.independent = independent;
    }

    @Basic
    @Column(name = "suggestions")
    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    @Basic
    @Column(name = "project_in_five_months")
    public String getProjectInFiveMonths() {
        return projectInFiveMonths;
    }

    public void setProjectInFiveMonths(String projectInFiveMonths) {
        this.projectInFiveMonths = projectInFiveMonths;
    }

    @Basic
    @Column(name = "obstacles")
    public String getObstacles() {
        return obstacles;
    }

    public void setObstacles(String obstacles) {
        this.obstacles = obstacles;
    }

    @Basic
    @Column(name = "best_sides_highlights")
    public String getBest_sides_highlights() {
        return best_sides_highlights;
    }

    public void setBest_sides_highlights(String best_sides_highlights) {
        this.best_sides_highlights = best_sides_highlights;
    }

    @Basic
    @Column(name = "human_resources")
    public String getHumanResources() {
        return humanResources;
    }

    public void setHumanResources(String humanResources) {
        this.humanResources = humanResources;
    }

    @Basic
    @Column(name = "people_satisfaction")
    public String getPeopleSatisfaction() {
        return peopleSatisfaction;
    }

    public void setPeopleSatisfaction(String peopleSatisfaction) {
        this.peopleSatisfaction = peopleSatisfaction;
    }

    @Basic
    @Column(name = "feedback_from_client")
    public String getFeedbackFromClient() {
        return feedbackFromClient;
    }

    public void setFeedbackFromClient(String feedbackFromClient) {
        this.feedbackFromClient = feedbackFromClient;
    }

    @Basic
    @Column(name = "improving_process")
    public String getImprovingProcess() {
        return improvingProcess;
    }

    public void setImprovingProcess(String improvingProcess) {
        this.improvingProcess = improvingProcess;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "locked", columnDefinition = "TINYINT(1)")
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
