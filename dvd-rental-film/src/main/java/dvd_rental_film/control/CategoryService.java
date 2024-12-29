package dvd_rental_film.control;

import dvd_rental_film.entity.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryService {
    @PersistenceContext(unitName = "dvdrentalfilm") // name aus persist.xml : unit
    private EntityManager em;

    public List<Category> getAll() {
        return    em.createNamedQuery("cat.getAll", Category.class).getResultList();
    }

    public Category getById(int id) {
        return em.find(Category.class, id);
    }

    public String getNameById(int id) {
        return em.find(Category.class, id).getName();
    }
}


