package com.msgnetconomy.appraisalsheet.services;

import com.msgnetconomy.appraisalsheet.dao.ProjectEvaluationDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectEvaluationServiceImplTest {

    @Mock
    private ProjectEvaluationDAO projectEvaluationDAO;

    @Before
    public void setUp() {
    }

    @Test
    public void saveOrUpdateProjectEvaluationExistByIdTrueTest() {
        boolean existsById = true;
        int projectEvaluationID = 1;
        verify(projectEvaluationDAO, times(1)).findById(projectEvaluationID);
    }

    @Test
    public void saveOrUpdateProjectEvaluationExistByIdFalseTest() {
        boolean existsById = true;
        int projectEvaluationID = 1;
        verify(projectEvaluationDAO, times(1)).findById(projectEvaluationID);
    }
}
