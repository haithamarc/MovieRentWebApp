package dvd_rental_film.control;

import dvd_rental_film.entity.Language;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class LanguageService {
    @PersistenceContext(unitName = "dvdrentalfilm") // name aus persist.xml : unit
    private EntityManager em;

    public List<Language> getAll() {
        TypedQuery<Language> query = em.createNamedQuery("language.getAll", Language.class);
        return query.getResultList() ;
    }

    public String getNameById(int id) {
        return em.find(Language.class, id).getName();
    }

    public int getIdByName(String name) {
        try {
            return em.createQuery("select l.id from Language l where l.name = :name", Integer.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch(NoResultException e) {
            return 1;
        }
    }
}
