package com.msgnetconomy.appraisalsheet.dto;

public class UserManagerDto {

    private int userManagerId;
    private String name;
    private String managerUsername;

    public int getUserManagerId() {
        return userManagerId;
    }

    public void setUserManagerId(int userManagerId) {
        this.userManagerId = userManagerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }
}
