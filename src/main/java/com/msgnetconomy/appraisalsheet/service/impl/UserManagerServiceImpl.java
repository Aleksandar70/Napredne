package com.msgnetconomy.appraisalsheet.service.impl;

import com.msgnetconomy.appraisalsheet.dao.UserManagerDAO;
import com.msgnetconomy.appraisalsheet.dto.UserManagerDto;
import com.msgnetconomy.appraisalsheet.service.UserManagerService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private UserManagerDAO userManagerDAO;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public List<UserManagerDto> getAllUserManagers() {
        return userManagerDAO.findAll().stream().map(entity -> mapper.map(entity, UserManagerDto.class)).collect(Collectors.toList());
    }
}
