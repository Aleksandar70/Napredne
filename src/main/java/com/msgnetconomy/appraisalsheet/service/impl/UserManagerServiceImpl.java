package com.msgnetconomy.appraisalsheet.service.impl;

import com.msgnetconomy.appraisalsheet.dao.UserManagerDAO;
import com.msgnetconomy.appraisalsheet.domain.UserManagerEntity;
import com.msgnetconomy.appraisalsheet.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private UserManagerDAO userManagerDAO;

    @Override
    public List<UserManagerEntity> getAllUserManagers() {
        return userManagerDAO.findAll();
    }
}
