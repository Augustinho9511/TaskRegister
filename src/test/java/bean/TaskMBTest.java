package bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dao.ProfileDAO;
import dao.TaskDAO;
import model.Task;
import model.Profile;

@ExtendWith(MockitoExtension.class)
public class TaskMBTest {

    @InjectMocks
    private TaskMB taskMB;

    @Mock
    private TaskDAO taskDAO;

    @Mock
    private ProfileDAO profileDAO;

    @BeforeEach
    void setUp() {
        taskMB.setTask(new Task());
        taskMB.setProfile(new Profile());
    }

    @Test
    void testSaveTaskSuccess() {
        String outcome = taskMB.saveTask();

        
        assertEquals("listagem?faces-redirect=true", outcome);
        
        verify(taskDAO, times(1)).save(any(Task.class));
    }

    @Test
    void testInitNewTask() {
        assertNotNull(taskMB.getTask());
        assertNotNull(taskMB.getProfile());
    }
}