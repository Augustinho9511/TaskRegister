package dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    private EntityTransaction et;

    @Test
    void testFindById() {
        Profile mockProfile = new Profile();
        mockProfile.setId(1L);
        
        when(em.find(Profile.class, 1L)).thenReturn(mockProfile);
        
        assertEquals(1L, mockProfile.getId());
    }
}