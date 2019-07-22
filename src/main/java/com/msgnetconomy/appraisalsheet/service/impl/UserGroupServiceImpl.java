package com.msgnetconomy.appraisalsheet.service.impl;

import com.msgnetconomy.appraisalsheet.dao.UserGroupDAO;
import com.msgnetconomy.appraisalsheet.domain.UserGroupEntity;
import com.msgnetconomy.appraisalsheet.dto.UserGroupDto;
import com.msgnetconomy.appraisalsheet.service.UserGroupService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupDAO userGroupDAO;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public List<UserGroupDto> getAllUserGroups() {
        List<UserGroupEntity> userGroupEntities = (List<UserGroupEntity>) userGroupDAO.findAll();
        return userGroupEntities.stream().map(entity -> mapper.map(entity, UserGroupDto.class)).collect(Collectors.toList());
    }

    @Override
    public void addUserGroup(UserGroupDto userGroupDto) {
        userGroupDAO.save(mapper.map(userGroupDto, UserGroupEntity.class));
    }
}
