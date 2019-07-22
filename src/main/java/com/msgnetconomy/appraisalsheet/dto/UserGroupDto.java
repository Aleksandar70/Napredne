package com.msgnetconomy.appraisalsheet.dto;

import lombok.Data;

@Data
public class UserGroupDto {

    private int userGroupId;
    private String name;
    private String description;

    public UserGroupDto() {
    }

    public UserGroupDto(int userGroupId, String name, String description) {
        this.userGroupId = userGroupId;
        this.name = name;
        this.description = description;
    }
}
