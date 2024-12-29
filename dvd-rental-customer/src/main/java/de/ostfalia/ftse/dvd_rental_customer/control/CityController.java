package de.ostfalia.ftse.dvd_rental_customer.control;

import de.ostfalia.ftse.dvd_rental_customer.entity.City;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CityController {

    @PersistenceContext
    private EntityManager em;

    public City getByCity(String city) {
        return em.createNamedQuery("getByCity", City.class).setParameter("city", city).getSingleResult();
    }
}
