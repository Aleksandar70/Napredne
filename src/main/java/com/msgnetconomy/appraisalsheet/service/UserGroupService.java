package com.msgnetconomy.appraisalsheet.service;

import com.msgnetconomy.appraisalsheet.domain.UserGroupEntity;

import java.util.List;

public interface UserGroupService {

    List<UserGroupEntity> getAllUserGroups();

    void addUserGroup(UserGroupEntity userGroupEntity);
}
