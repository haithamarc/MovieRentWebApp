package de.ostfalia.ftse.dvd_rental_customer.control;

import de.ostfalia.ftse.dvd_rental_customer.entity.Country;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CountryController {

    @PersistenceContext
    private EntityManager em;

    public Country getByCountry(String country) {
        return em.createNamedQuery("getByCountry", Country.class).setParameter("country", country).getSingleResult();
    }
}
