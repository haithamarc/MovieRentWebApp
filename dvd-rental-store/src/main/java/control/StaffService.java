package control;

import entity.Staff;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class StaffService {
    @PersistenceContext(unitName = "dvdrentalstore")
    private EntityManager em;

    public Class<Staff> getEntityClass() {
        return Staff.class;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public TypedQuery<Staff> getAllQuery() {
        TypedQuery<Staff> query = em.createQuery("SELECT s FROM Staff s",Staff.class);
        return query;
    }


    public Staff getStaffById(int staff_id) {

        TypedQuery<Staff> query = em.createNamedQuery("getStaffById", Staff.class).setParameter("staff_id", staff_id);
        try{
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

    }





}

