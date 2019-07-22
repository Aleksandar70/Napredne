package com.msgnetconomy.appraisalsheet.dto;

import lombok.Data;

@Data
public class UserManagerDto {

    private int userManagerId;
    private String name;
    private String managerUsername;

    public UserManagerDto() {
    }

    public UserManagerDto(int userManagerId, String name, String managerUsername) {
        this.userManagerId = userManagerId;
        this.name = name;
        this.managerUsername = managerUsername;
    }
}
