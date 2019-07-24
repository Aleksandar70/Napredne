package com.msgnetconomy.appraisalsheet.mapper;

import com.msgnetconomy.appraisalsheet.domain.ProjectEvaluationEntity;
import com.msgnetconomy.appraisalsheet.dto.ProjectEvaluationDto;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectEvaluationMapperTest {

    @Autowired
    private DozerBeanMapper mapper;

    @Test
    public void mappingProjectEvaluationDtoToProjectEvaluationEntityTest() {
        ProjectEvaluationDto projectEvaluationDto = new ProjectEvaluationDto();

        ProjectEvaluationEntity projectEvaluationEntity = mapper.map(projectEvaluationDto, ProjectEvaluationEntity.class);

    }

    @Test
    public void mappingProjectEvaluationEntityToProjectEvaluationDtoTest() {
        ProjectEvaluationEntity projectEvaluationEntity = new ProjectEvaluationEntity();

        ProjectEvaluationDto projectEvaluationDto = mapper.map(projectEvaluationEntity, ProjectEvaluationDto.class);

    }
}
