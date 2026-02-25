package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Profile;

public class ProfileDAO {
    
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JSFHibernatePU");
    
    public void save(Profile profile) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            if (profile.getId() == null) {
                em.persist(profile);
            } else {
                em.merge(profile);
            }
            em.getTransaction().commit();
            
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    public List<Profile> list() {
        EntityManager em = emf.createEntityManager();
        List<Profile> profileList = em.createQuery("FROM Profile", Profile.class).getResultList();
        em.close();
        return profileList;
    }

    public Profile findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Profile.class, id);
        } finally {
            em.close();
        }
    }
    
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            Profile profile = em.find(Profile.class, id);
            if (profile != null) {
                em.remove(profile);
            }
            em.getTransaction().commit();
            
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }
}