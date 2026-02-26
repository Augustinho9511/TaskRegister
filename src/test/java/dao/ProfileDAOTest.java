package dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import model.Profile;

@ExtendWith(MockitoExtension.class)
public class ProfileDAOTest {

    @Mock
    private EntityManagerFactory emf;

    @Mock
    private EntityManager em;

    @Mock
    private EntityTransaction tx;

    @InjectMocks
    private ProfileDAO profileDAO;

    @Test
    void testFindById() {
        Profile mockProfile = new Profile();
        mockProfile.setId(1L);
        
        when(emf.createEntityManager()).thenReturn(em);
        when(em.find(Profile.class, 1L)).thenReturn(mockProfile);
        
        Profile result = profileDAO.findById(1L);
        
      
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(em).close(); 
    }

    @Test
    void testSaveNewProfile() {
        when(emf.createEntityManager()).thenReturn(em);
        when(em.getTransaction()).thenReturn(tx);

        Profile p = new Profile(); 
        profileDAO.save(p);

        verify(em).persist(p); 
        verify(tx).commit();
        verify(em).close();
    }
}