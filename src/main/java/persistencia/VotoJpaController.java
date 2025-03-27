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
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.PartidoPol;
import logica.Votante;
import logica.Voto;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Gabse
 */
public class VotoJpaController implements Serializable {

    public VotoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public VotoJpaController() {
        emf = Persistence.createEntityManagerFactory("casoPracticoVotos_PU");
    }

    public void create(Voto voto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PartidoPol partidoPol = voto.getPartidoPol();
            if (partidoPol != null) {
                partidoPol = em.getReference(partidoPol.getClass(), partidoPol.getId_partidoPol());
                voto.setPartidoPol(partidoPol);
            }
            em.persist(voto);
            if (partidoPol != null) {
                partidoPol.getLista_voto().add(voto);
                partidoPol = em.merge(partidoPol);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Object[]> obtenerConteoVotosPorPartido() {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT p.nombre, COUNT(v) FROM Voto v JOIN v.partidoPol p GROUP BY p.nombre";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        return query.getResultList();
    }

    public List<Object[]> obtenerConteoPorGenero(String genero) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT p.nombre, COUNT(v) FROM Voto v JOIN v.partidoPol p JOIN v.votante vot "
                    + "WHERE vot.generoVotante = :genero GROUP BY p.nombre";
            return em.createQuery(jpql, Object[].class)
                    .setParameter("genero", genero)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void edit(Voto voto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Voto persistentVoto = em.find(Voto.class, voto.getId_voto());
            PartidoPol partidoPolOld = persistentVoto.getPartidoPol();
            PartidoPol partidoPolNew = voto.getPartidoPol();
            if (partidoPolNew != null) {
                partidoPolNew = em.getReference(partidoPolNew.getClass(), partidoPolNew.getId_partidoPol());
                voto.setPartidoPol(partidoPolNew);
            }
            voto = em.merge(voto);
            if (partidoPolOld != null && !partidoPolOld.equals(partidoPolNew)) {
                partidoPolOld.getLista_voto().remove(voto);
                partidoPolOld = em.merge(partidoPolOld);
            }
            if (partidoPolNew != null && !partidoPolNew.equals(partidoPolOld)) {
                partidoPolNew.getLista_voto().add(voto);
                partidoPolNew = em.merge(partidoPolNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = voto.getId_voto();
                if (findVoto(id) == null) {
                    throw new NonexistentEntityException("The voto with id " + id + " no longer exists.");
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
            Voto voto;
            try {
                voto = em.getReference(Voto.class, id);
                voto.getId_voto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The voto with id " + id + " no longer exists.", enfe);
            }
            PartidoPol partidoPol = voto.getPartidoPol();
            if (partidoPol != null) {
                partidoPol.getLista_voto().remove(voto);
                partidoPol = em.merge(partidoPol);
            }
            em.remove(voto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Voto> findVotoEntities() {
        return findVotoEntities(true, -1, -1);
    }

    public List<Voto> findVotoEntities(int maxResults, int firstResult) {
        return findVotoEntities(false, maxResults, firstResult);
    }

    private List<Voto> findVotoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Voto.class));
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

    public Voto findVoto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Voto.class, id);
        } finally {
            em.close();
        }
    }

    public int getVotoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Voto> rt = cq.from(Voto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    boolean existeVoto(int id_votante) {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery(
                    "SELECT COUNT(v) FROM Voto v WHERE v.votante.id_votante = :idVotante",
                    Long.class)
                    .setParameter("idVotante", id_votante)
                    .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    void guardarVoto(int id_votante, int id_partido) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Votante votante = em.find(Votante.class, id_votante);
            PartidoPol partido = em.find(PartidoPol.class, id_partido);

            Voto nuevoVoto = new Voto();
            nuevoVoto.setVotante(votante);
            nuevoVoto.setPartidoPol(partido);

            em.persist(nuevoVoto);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Object[]> obtenerConteoPorProvincia(String provincia) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT p.nombre, COUNT(v) FROM Voto v "
                    + "JOIN v.partidoPol p "
                    + "JOIN v.votante vot "
                    + "WHERE vot.provinciaVotante = :provincia "
                    + "GROUP BY p.nombre";
            return em.createQuery(jpql, Object[].class)
                    .setParameter("provincia", provincia)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    List<Object[]> obtenerConteoPorCiudad(String ciudadSeleccionada) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT p.nombre, COUNT(v) FROM Voto v "
                    + "JOIN v.partidoPol p "
                    + "JOIN v.votante vot "
                    + "WHERE vot.ciudadVotante = :ciudad "
                    + "GROUP BY p.nombre";
            return em.createQuery(jpql, Object[].class)
                    .setParameter("ciudad", ciudadSeleccionada)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}
