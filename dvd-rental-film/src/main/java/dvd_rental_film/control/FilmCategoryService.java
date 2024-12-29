package dvd_rental_film.control;

import dvd_rental_film.entity.FilmCategory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class FilmCategoryService {
    @PersistenceContext(unitName = "dvdrentalfilm") // name aus persist.xml : unit
    private EntityManager em;
    public void  filmCategBinden (int fid,int aid){
        FilmCategory ac =new FilmCategory() ;
        ac.setCategoryId(aid);
        ac.setFilmId(fid);
        try {
            em.persist(ac);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getCategoryIdByFilmId(int fId) {
        TypedQuery<Integer> query = em.createQuery("select fc.categoryId from FilmCategory fc where fc.filmId = :filmId", Integer.class);
        query.setParameter("filmId", fId);
        return query.getResultList();
    }
}
