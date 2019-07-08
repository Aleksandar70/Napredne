package com.msgnetconomy.appraisalsheet.dto;

import lombok.Data;

@Data
public class UserDto {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UserGroupDto userGroup;
    private UserManagerDto userManager;

    public UserDto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserGroupDto getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroupDto userGroup) {
        this.userGroup = userGroup;
    }

    public UserManagerDto getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManagerDto userManager) {
        this.userManager = userManager;
    }
}
