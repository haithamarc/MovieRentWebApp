package control;

import entity.Inventory;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
public class InventoryService {

    @PersistenceContext(unitName = "dvdrentalstore")
    private EntityManager em;

    public Class<Inventory> getEntityClass() {
        return Inventory.class;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public TypedQuery<Inventory> getAllQuery() {
        TypedQuery<Inventory> query = em.createQuery("SELECT i FROM Inventory i",Inventory.class);
        return query;
    }

    public Inventory getInventoryById(int inventory_id) {
        try {
            TypedQuery<Inventory> query = em.createNamedQuery("getInventoryById", Inventory.class).setParameter("inventory_id", inventory_id);
            return query.getSingleResult();

        } catch (NoResultException nre) {
            return null;
        }


    }

    public List<Inventory> getInventoryByFilmId(int film_id) {

        TypedQuery<Inventory> query = em.createNamedQuery("getInventoryByFilmId", Inventory.class).setParameter("film_id", film_id);
        return query.getResultList();



    }




}
