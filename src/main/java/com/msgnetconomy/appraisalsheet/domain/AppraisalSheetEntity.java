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
import java.time.LocalDate;

@Entity
@Table(name = "appraisal_sheet", schema = "appraisal-sheet-mysql")
public class AppraisalSheetEntity {

    private int appraisalSheetID;
    private UserEntity user;
    private LocalDate date;
    private String employeeName;
    private String division;
    private String careerLevel;
    private String appraisalPeriod;
    private String manager;
    private String tasksBackdated;
    private String teamLeadFeedback;
    private String companyFeedback;
    private String targetsBackdated;
    private String roleRequirements;
    private String selfCompetence;
    private String socialCompetence;
    private String methodicalCompetence;
    private String roleRequirementsGoals;
    private String selfCompetenceGoals;
    private String companyOrientedGoals;
    private String economicGoal;
    private String developmentObjectives;
    private String developmentPotential;
    private String employeeExpectations;
    private boolean locked;

    @Id
    @Column(name = "appraisal_sheet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getAppraisalSheetID() {
        return appraisalSheetID;
    }

    public void setAppraisalSheetID(int appraisalSheetID) {
        this.appraisalSheetID = appraisalSheetID;
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
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
    @Column(name = "division")
    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
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
    @Column(name = "tasks_backdated")
    public String getTasksBackdated() {
        return tasksBackdated;
    }

    public void setTasksBackdated(String tasksBackdated) {
        this.tasksBackdated = tasksBackdated;
    }

    @Basic
    @Column(name = "team_lead_feedback")
    public String getTeamLeadFeedback() {
        return teamLeadFeedback;
    }

    public void setTeamLeadFeedback(String teamLeadFeedback) {
        this.teamLeadFeedback = teamLeadFeedback;
    }

    @Basic
    @Column(name = "company_feedback")
    public String getCompanyFeedback() {
        return companyFeedback;
    }

    public void setCompanyFeedback(String companyFeedback) {
        this.companyFeedback = companyFeedback;
    }

    @Basic
    @Column(name = "targets_backdated")
    public String getTargetsBackdated() {
        return targetsBackdated;
    }

    public void setTargetsBackdated(String targetsBackdated) {
        this.targetsBackdated = targetsBackdated;
    }

    @Basic
    @Column(name = "role_requirements")
    public String getRoleRequirements() {
        return roleRequirements;
    }

    public void setRoleRequirements(String roleRequirements) {
        this.roleRequirements = roleRequirements;
    }

    @Basic
    @Column(name = "self_competence")
    public String getSelfCompetence() {
        return selfCompetence;
    }

    public void setSelfCompetence(String selfCompetence) {
        this.selfCompetence = selfCompetence;
    }

    @Basic
    @Column(name = "social_competence")
    public String getSocialCompetence() {
        return socialCompetence;
    }

    public void setSocialCompetence(String socialCompetence) {
        this.socialCompetence = socialCompetence;
    }

    @Basic
    @Column(name = "methodical_competence")
    public String getMethodicalCompetence() {
        return methodicalCompetence;
    }

    public void setMethodicalCompetence(String methodicalCompetence) {
        this.methodicalCompetence = methodicalCompetence;
    }

    @Basic
    @Column(name = "role_requirements_goals")
    public String getRoleRequirementsGoals() {
        return roleRequirementsGoals;
    }

    public void setRoleRequirementsGoals(String roleRequirementsGoals) {
        this.roleRequirementsGoals = roleRequirementsGoals;
    }

    @Basic
    @Column(name = "self_competence_goals")
    public String getSelfCompetenceGoals() {
        return selfCompetenceGoals;
    }

    public void setSelfCompetenceGoals(String selfCompetenceGoals) {
        this.selfCompetenceGoals = selfCompetenceGoals;
    }

    @Basic
    @Column(name = "company_oriented_goals")
    public String getCompanyOrientedGoals() {
        return companyOrientedGoals;
    }

    public void setCompanyOrientedGoals(String companyOrientedGoals) {
        this.companyOrientedGoals = companyOrientedGoals;
    }

    @Basic
    @Column(name = "economic_goal")
    public String getEconomicGoal() {
        return economicGoal;
    }

    public void setEconomicGoal(String economicGoal) {
        this.economicGoal = economicGoal;
    }

    @Basic
    @Column(name = "development_objectives")
    public String getDevelopmentObjectives() {
        return developmentObjectives;
    }

    public void setDevelopmentObjectives(String developmentObjectives) {
        this.developmentObjectives = developmentObjectives;
    }

    @Basic
    @Column(name = "development_potential")
    public String getDevelopmentPotential() {
        return developmentPotential;
    }

    public void setDevelopmentPotential(String developmentPotential) {
        this.developmentPotential = developmentPotential;
    }

    @Basic
    @Column(name = "employee_expectations")
    public String getEmployeeExpectations() {
        return employeeExpectations;
    }

    public void setEmployeeExpectations(String employeeExpectations) {
        this.employeeExpectations = employeeExpectations;
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
