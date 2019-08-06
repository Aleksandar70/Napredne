package com.msgnetconomy.appraisalsheet.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "projectevaluation")
public class UserEntity {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UserGroupEntity userGroup;
    private UserManagerEntity userManager;

    @Id
    @Column(name = "userID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return userId == that.userId &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, username, password);
    }

    @ManyToOne
    @JoinColumn(name = "user_groupID", referencedColumnName = "user_groupID")
    public UserGroupEntity getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroupEntity userGroup) {
        this.userGroup = userGroup;
    }

    @ManyToOne
    @JoinColumn(name = "user_managerID", referencedColumnName = "user_managerID")
    public UserManagerEntity getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManagerEntity userManager) {
        this.userManager = userManager;
    }
}
