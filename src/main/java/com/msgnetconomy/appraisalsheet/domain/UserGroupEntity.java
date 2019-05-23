package com.msgnetconomy.appraisalsheet.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_group", schema = "appraisal-sheet-mysql")
public class UserGroupEntity {
    private int userGroupId;
    private String name;
    private String description;

    @Id
    @Column(name = "user_groupID")
    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroupEntity that = (UserGroupEntity) o;
        return userGroupId == that.userGroupId &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGroupId, name, description);
    }
}
