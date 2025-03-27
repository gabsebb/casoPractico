/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.PartidoPol;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Gabse
 */
public class PartidoPolJpaController implements Serializable {

    public PartidoPolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public PartidoPolJpaController() {
        emf=Persistence.createEntityManagerFactory("casoPracticoVotos_PU");
    }

    public void create(PartidoPol partidoPol) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(partidoPol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PartidoPol partidoPol) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            partidoPol = em.merge(partidoPol);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = partidoPol.getId_partidoPol();
                if (findPartidoPol(id) == null) {
                    throw new NonexistentEntityException("The partidoPol with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PartidoPol partidoPol;
            try {
                partidoPol = em.getReference(PartidoPol.class, id);
                partidoPol.getId_partidoPol();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The partidoPol with id " + id + " no longer exists.", enfe);
            }
            em.remove(partidoPol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PartidoPol> findPartidoPolEntities() {
        return findPartidoPolEntities(true, -1, -1);
    }

    public List<PartidoPol> findPartidoPolEntities(int maxResults, int firstResult) {
        return findPartidoPolEntities(false, maxResults, firstResult);
    }

    private List<PartidoPol> findPartidoPolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PartidoPol.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PartidoPol findPartidoPol(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PartidoPol.class, id);
        } finally {
            em.close();
        }
    }

    public int getPartidoPolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PartidoPol> rt = cq.from(PartidoPol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
