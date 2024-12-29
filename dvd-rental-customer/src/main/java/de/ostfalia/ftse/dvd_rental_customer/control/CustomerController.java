package de.ostfalia.ftse.dvd_rental_customer.control;

import de.ostfalia.ftse.dvd_rental_customer.entity.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerController {

    @PersistenceContext
    private EntityManager em;

    public void create(Customer c) {
        em.persist(c);
    }

    public long count() {
        return em.createNamedQuery("count", Long.class).getSingleResult();
    }

    public Customer get(int id) {
        return em.find(Customer.class, id);
    }
}
