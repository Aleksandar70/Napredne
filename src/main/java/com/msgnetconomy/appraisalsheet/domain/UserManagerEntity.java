package com.msgnetconomy.appraisalsheet.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_manager", schema = "appraisal-sheet-mysql")
public class UserManagerEntity {
    private int userManagerId;
    private String name;
    private String managerUsername;

    @Id
    @Column(name = "user_managerID")
    public int getUserManagerId() {
        return userManagerId;
    }

    public void setUserManagerId(Integer userManagerId) {
        this.userManagerId = userManagerId;
    }

    public void setUserManagerId(int userManagerId) {
        this.userManagerId = userManagerId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "manager_username")
    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserManagerEntity that = (UserManagerEntity) o;
        return userManagerId == that.userManagerId &&
                Objects.equals(name, that.name) &&
                Objects.equals(managerUsername, that.managerUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userManagerId, name, managerUsername);
    }
}
