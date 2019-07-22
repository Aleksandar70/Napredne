package com.msgnetconomy.appraisalsheet.dto;

import lombok.Data;

@Data
public class UserDto {

    private int userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private UserGroupDto userGroupDto;
    private UserManagerDto userManagerDto;

    public UserDto(int userId, String username, String password, String firstName, String lastName, UserGroupDto userGroupDto, UserManagerDto userManagerDto) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userGroupDto = userGroupDto;
        this.userManagerDto = userManagerDto;
    }

    public UserDto() {
    }
}
