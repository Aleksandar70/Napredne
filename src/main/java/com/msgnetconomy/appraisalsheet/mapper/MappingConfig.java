package com.msgnetconomy.appraisalsheet.mapper;

import com.msgnetconomy.appraisalsheet.domain.ProjectEvaluationEntity;
import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.domain.UserGroupEntity;
import com.msgnetconomy.appraisalsheet.domain.UserManagerEntity;
import com.msgnetconomy.appraisalsheet.dto.ProjectEvaluationDto;
import com.msgnetconomy.appraisalsheet.dto.UserDto;
import com.msgnetconomy.appraisalsheet.dto.UserGroupDto;
import com.msgnetconomy.appraisalsheet.dto.UserManagerDto;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {

    @Bean
    BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(UserGroupEntity.class, UserGroupDto.class);
                mapping(UserManagerEntity.class, UserManagerDto.class);
                mapping(UserEntity.class, UserDto.class)
                        .fields("userGroup", "userGroupDto")
                        .fields("userManager", "userManagerDto");
                mapping(ProjectEvaluationEntity.class, ProjectEvaluationDto.class)
                        .fields("user", "userDto");
            }
        };
    }

    @Bean
    public DozerBeanMapper beanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(beanMappingBuilder());
        return dozerBeanMapper;
    }
}
