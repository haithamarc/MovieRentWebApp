package control;

import entity.Store;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class StoreService {
    @PersistenceContext(unitName = "dvdrentalstore")
    private EntityManager em;

    public Class<Store> getEntityClass() {
        return Store.class;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public TypedQuery<Store> getAllQuery() {
        TypedQuery<Store> query = em.createQuery("SELECT s FROM Store s",Store.class);
        return query;
    }


    public Store getStoreById(int store_id) {

        TypedQuery<Store> query = em.createNamedQuery("getStoreById", Store.class).setParameter("store_id", store_id);
        try{
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

    }

    public int getStoreCount(){
        int count = ((Number)em.createNamedQuery("getStoreCount").getSingleResult()).intValue();
        return count;
    }






}
