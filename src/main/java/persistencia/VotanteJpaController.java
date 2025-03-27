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
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Usuario;
import logica.Votante;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Gabse
 */
public class VotanteJpaController implements Serializable {

    public VotanteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public VotanteJpaController() {
        emf = Persistence.createEntityManagerFactory("casoPracticoVotos_PU");
    }

    public void create(Votante votante) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(votante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Votante votante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            votante = em.merge(votante);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = votante.getId_votante();
                if (findVotante(id) == null) {
                    throw new NonexistentEntityException("The votante with id " + id + " no longer exists.");
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
            Votante votante;
            try {
                votante = em.getReference(Votante.class, id);
                votante.getId_votante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The votante with id " + id + " no longer exists.", enfe);
            }
            em.remove(votante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Votante> findVotanteEntities() {
        return findVotanteEntities(true, -1, -1);
    }

    public List<Votante> findVotanteEntities(int maxResults, int firstResult) {
        return findVotanteEntities(false, maxResults, firstResult);
    }

    private List<Votante> findVotanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Votante.class));
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

    public Votante findVotante(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Votante.class, id);
        } finally {
            em.close();
        }
    }

    public int getVotanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Votante> rt = cq.from(Votante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    List<Object[]> getVotanteConUsuarios() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT v, u FROM Votante v LEFT JOIN v.usuario u",
                    Object[].class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    Votante getVotantePorIdUsuario(int id_usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT v FROM Votante v WHERE v.usuario.id_usuario = :idUsuario",
                    Votante.class)
                    .setParameter("idUsuario", id_usuario)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    Votante getVotyUser(int id_votante) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT v FROM Votante v JOIN FETCH v.usuario WHERE v.id_votante = :id",
                    Votante.class)
                    .setParameter("id", id_votante)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    void actualizarVotante(Votante votante, Usuario usuario) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(votante);
            em.merge(usuario);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new Exception("Error al actualizar: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    void eliminarVotanteYUsuario(int id_votante, int id_usuario) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Votante votante = em.find(Votante.class, id_votante);
            if (votante != null) {
                em.remove(votante);
            }
            Usuario usuario = em.find(Usuario.class, id_usuario);
            if (usuario != null) {
                em.remove(usuario);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new Exception("Error al eliminar: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    void crearVotanteConUsuario(String nombre, String apellido, String cedula, String provincia, String ciudad, String genero, String username, String password, String rol) {
         EntityManager em = getEntityManager();
    EntityTransaction transaction = em.getTransaction();
    
    try {
        transaction.begin();
       
        if(em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :username", Usuario.class)
           .setParameter("username", username)
           .getResultList().size() > 0) {
            throw new RuntimeException("Ya existe un usuario con esta cédula");
        }
        
        Usuario usuario = new Usuario(username, password, rol);
        em.persist(usuario);
 
        Votante votante = new Votante(nombre, apellido, cedula, provincia, ciudad, genero, usuario);
        em.persist(votante);
        
        transaction.commit();
    } catch (Exception e) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        throw e;
    } finally {
        if (em != null) {
            em.close();
        }
    }
    }

}
