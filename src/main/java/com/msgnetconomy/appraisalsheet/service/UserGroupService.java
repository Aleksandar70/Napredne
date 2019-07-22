package com.msgnetconomy.appraisalsheet.service;

import com.msgnetconomy.appraisalsheet.dto.UserGroupDto;

import java.util.List;

public interface UserGroupService {

    List<UserGroupDto> getAllUserGroups();

    void addUserGroup(UserGroupDto userGroupDto);
}
