package control;

import entity.Rental;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RentalService {


    @PersistenceContext(unitName = "dvdrentalstore")
    private EntityManager em;

    public Class<Rental> getEntityClass() {
        return Rental.class;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public TypedQuery<Rental> getAllQuery() {
        TypedQuery<Rental> query = em.createQuery("SELECT r FROM Rental r",Rental.class);
        return query;
    }


    public Rental getRentalById(int rental_id) {

        TypedQuery<Rental> query = em.createNamedQuery("getRentalById", Rental.class).setParameter("rental_id", rental_id);

            try{
                return query.getSingleResult();
            } catch (NoResultException nre) {
                return null;
            }

    }

    public boolean terminateRentalById(int rental_id) {
        try {
            em.createNamedQuery("terminateRentalById",Rental.class).setParameter("rental_id",rental_id).executeUpdate();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean insertRental(Rental rental){
        try {

            em.persist(rental);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }






}
