package com.msgnetconomy.appraisalsheet.services;

import com.msgnetconomy.appraisalsheet.dao.ProjectEvaluationDAO;
import com.msgnetconomy.appraisalsheet.domain.ProjectEvaluationEntity;
import com.msgnetconomy.appraisalsheet.service.impl.ProjectEvaluationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProjectEvaluationServiceImplTest {

    @InjectMocks
    ProjectEvaluationServiceImpl projectEvaluationServiceImpl;

    @Mock
    private ProjectEvaluationDAO projectEvaluationDAO;

    @Before
    public void setUp() {
    }

    @Test
    public void saveOrUpdateProjectEvaluationExistByIdTrueTest() {
        boolean existsById = true;
        ProjectEvaluationEntity projectEvaluationEntity = new ProjectEvaluationEntity();
        projectEvaluationEntity.setProjectEvaluationID(1);
        verify(projectEvaluationDAO, times(1)).findById(projectEvaluationEntity.getProjectEvaluationID());
    }

    @Test
    public void saveOrUpdateProjectEvaluationExistByIdFalseTest() {
        boolean existsById = true;
        int projectEvaluationID = 1;
        verify(projectEvaluationDAO, times(1)).findById(projectEvaluationID);
    }
}
