package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Task;

public class TaskDAO {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JSFHibernatePU");
    
    public void save(Task task) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            if (task.getId() == null) {
                em.persist(task);
            } else {
                em.merge(task);
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

    public List<Task> list() {
        EntityManager em = emf.createEntityManager();
        
        List<Task> taskList = em.createQuery("FROM Task", Task.class).getResultList();
        em.close();
        return taskList;
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            Task task = em.find(Task.class, id);
            if (task != null) {
                em.remove(task);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Task findById(Long id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Task.class, id);
        } finally {
            em.close();
        }
    }

     
    public List<Task> listInProgress() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Task t WHERE t.status = false", Task.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Task> filter(Long id, String title, Long responsibleId, Boolean status) {
        EntityManager em = emf.createEntityManager();
        try {
            
            StringBuilder jpql = new StringBuilder("SELECT t FROM Task t WHERE 1=1");
            
            if (id != null) jpql.append(" AND t.id = :id");
            if (title != null && !title.isEmpty()) jpql.append(" AND (t.title LIKE :title OR t.description LIKE :title)");
            if (responsibleId != null) jpql.append(" AND t.responsible.id = :respId");
            if (status != null) jpql.append(" AND t.status = :status");

            Query query = em.createQuery(jpql.toString(), Task.class);

            
            if (id != null) query.setParameter("id", id);
            if (title != null && !title.isEmpty()) query.setParameter("title", "%" + title + "%");
            if (responsibleId != null) query.setParameter("respId", responsibleId);
            if (status != null) query.setParameter("status", status);

            return query.getResultList();
        } finally {
            em.close();
        }
    }
}