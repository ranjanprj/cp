/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cp.entities;

import com.cp.entities.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author PRanjan3
 */
public class UserProfileJpaController implements Serializable {

    public UserProfileJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CPUserProfile userProfile) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(userProfile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CPUserProfile userProfile) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            userProfile = em.merge(userProfile);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = userProfile.getId();
                if (findUserProfile(id) == null) {
                    throw new NonexistentEntityException("The userProfile with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CPUserProfile userProfile;
            try {
                userProfile = em.getReference(CPUserProfile.class, id);
                userProfile.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userProfile with id " + id + " no longer exists.", enfe);
            }
            em.remove(userProfile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CPUserProfile> findUserProfileEntities() {
        return findUserProfileEntities(true, -1, -1);
    }

    public List<CPUserProfile> findUserProfileEntities(int maxResults, int firstResult) {
        return findUserProfileEntities(false, maxResults, firstResult);
    }

    private List<CPUserProfile> findUserProfileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CPUserProfile.class));
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

    public CPUserProfile findUserProfile(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CPUserProfile.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserProfileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CPUserProfile> rt = cq.from(CPUserProfile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
