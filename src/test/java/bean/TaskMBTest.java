package bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import dao.ProfileDAO;
import dao.TaskDAO;
import model.Profile;
import model.Task;

@ExtendWith(MockitoExtension.class)
public class TaskMBTest {

    @Mock
    private TaskDAO taskDAO;

    @Mock
    private ProfileDAO profileDAO;

    @Mock
    private FacesContext facesContext;

    @Mock
    private ExternalContext externalContext;

    @Mock
    private Flash flash;

    @InjectMocks
    private TaskMB taskMB;

    @Test
    void testSaveTaskWithProfile() {
        Task mockTask = new Task();
        Profile mockProfile = new Profile();
        mockProfile.setId(10L); 
        
        taskMB.setTask(mockTask);
        taskMB.setProfile(mockProfile);

        Profile perfilNoBanco = new Profile();
        when(profileDAO.findById(10L)).thenReturn(perfilNoBanco);

        String urlDeRetorno = taskMB.saveTask();

        assertFalse(mockTask.getStatus()); 
        assertEquals(perfilNoBanco, mockTask.getResponsible()); 
        verify(taskDAO).save(mockTask); 
        assertEquals("listagem?faces-redirect=true", urlDeRetorno); 
    }
    
    @Test
    void testInitMethodWithJSFFacesContext() {
        try (MockedStatic<FacesContext> mockedFacesContext = mockStatic(FacesContext.class)) {
            mockedFacesContext.when(FacesContext::getCurrentInstance).thenReturn(facesContext);
            when(facesContext.getExternalContext()).thenReturn(externalContext);
            when(externalContext.getFlash()).thenReturn(flash);
            when(flash.get("tarefaEditada")).thenReturn(null);

            taskMB.init();

            assertNotNull(taskMB.getTask());
            assertNotNull(taskMB.getProfile());
            verify(profileDAO).list();
        }
    }
}