package dvd_rental_film.control;

import dvd_rental_film.entity.Category;
import dvd_rental_film.entity.Film;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
@Transactional
@Stateless
public class FilmService  {
    @PersistenceContext(unitName = "dvdrentalfilm") // name aus persist.xml : unit
    private EntityManager em;
    public Film getByFilmID(int id) {
        return em.find(Film.class, id);
    }

    public List<Film> getAll() {
        TypedQuery<Film> query = em.createNamedQuery("film.getAll", Film.class);
        return query.getResultList() ;
    }
    public Film mergeFilm(Film film) {
        return em.merge(film);
    }
    public void updateFilm(@NotNull Film film) {
        em.merge(film);
    }
    public int  countall(){
        return ((Number)em.createNamedQuery("film.countAll").getSingleResult()).intValue();
    }
    public void delete(Film film) {
        em.remove(em.merge(film));
    }
    public List<Category> getAllCateg(int id) {
        TypedQuery<Category> query = em.createNamedQuery("film.Category", Category.class);
        return query.getResultList() ;
    }

    public List<Film> getAllLimitOffset(int limit, int offset) {
        return em.createNamedQuery("film.getAll", Film.class).setFirstResult(offset).setMaxResults(limit).getResultList();
    }
}
