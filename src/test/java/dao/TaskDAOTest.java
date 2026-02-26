package dao;

import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import model.Task;

@ExtendWith(MockitoExtension.class)
public class TaskDAOTest {

    @Mock
    private EntityManagerFactory emf;

    @Mock
    private EntityManager em;

    @Mock
    private EntityTransaction tx;

    @InjectMocks
    private TaskDAO taskDAO;

    @Test
    void testDeleteTask() {
        when(emf.createEntityManager()).thenReturn(em);
        when(em.getTransaction()).thenReturn(tx);
        
        Task mockTask = new Task();
        mockTask.setId(5L);
        when(em.find(Task.class, 5L)).thenReturn(mockTask);

        taskDAO.delete(5L);

        verify(em).remove(mockTask); 
        verify(tx).commit();
        verify(em).close();
    }
}