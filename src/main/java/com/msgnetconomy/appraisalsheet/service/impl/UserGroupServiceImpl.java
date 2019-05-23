package com.msgnetconomy.appraisalsheet.service.impl;

import com.msgnetconomy.appraisalsheet.dao.UserGroupDAO;
import com.msgnetconomy.appraisalsheet.domain.UserGroupEntity;
import com.msgnetconomy.appraisalsheet.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupDAO userGroupDAO;

    @Override
    public List<UserGroupEntity> getAllUserGroups() {
        return (List<UserGroupEntity>) userGroupDAO.findAll();
    }

    @Override
    public void addUserGroup(UserGroupEntity userGroupEntity) {
        userGroupDAO.save(userGroupEntity);
    }
}
