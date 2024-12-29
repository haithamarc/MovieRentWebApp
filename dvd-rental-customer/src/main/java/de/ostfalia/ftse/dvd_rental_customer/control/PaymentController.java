package de.ostfalia.ftse.dvd_rental_customer.control;

import de.ostfalia.ftse.dvd_rental_customer.entity.Customer;
import de.ostfalia.ftse.dvd_rental_customer.entity.Payment;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PaymentController {

    @PersistenceContext
    private EntityManager em;

    public void create(Payment p) {
        em.persist(p);
    }

    public Payment get(int id) {
        return em.find(Payment.class, id);
    }

    public List<Payment> getByCustomer(Customer c) {
        return em.createNamedQuery("getByCustomer", Payment.class).setParameter("c", c).getResultList();
    }

    public void delete(Payment p) {
        em.remove(em.merge(p));
    }
}
