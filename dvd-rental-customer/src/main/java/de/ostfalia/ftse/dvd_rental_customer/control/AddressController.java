package de.ostfalia.ftse.dvd_rental_customer.control;

import de.ostfalia.ftse.dvd_rental_customer.entity.Address;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AddressController {

    @PersistenceContext
    private EntityManager em;

    public Address merge(Address a) {
        return em.merge(a);
    }

    public List<Address> get(int limit, int offset) {
        return em.createNamedQuery("getAll", Address.class).setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    public Address get(int id) {
        return em.find(Address.class, id);
    }
}
