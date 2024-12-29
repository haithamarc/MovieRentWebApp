package dvd_rental_film.control;

import dvd_rental_film.entity.Actor;
import dvd_rental_film.entity.FilmActor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class FilmActorService {
    @PersistenceContext(unitName = "dvdrentalfilm") // name aus persist.xml : unit
    private EntityManager em;
    public Actor getByActorID(int id) {
        TypedQuery<Actor> query = em.createNamedQuery("actor.getByActorID", Actor.class);
        query.setParameter("actor_id", id);
        return query.getSingleResult();
    }
    public List<Integer> getFilmIdListByActorId(int id) {
        TypedQuery<Integer> query = em.createQuery("select e.filmId from FilmActor e where e.actorId = :actorId", Integer.class);
        query.setParameter("actorId", id);
        return query.getResultList();
    }

    public List<Integer> getActorIdListByFilmId(int id) {
        TypedQuery<Integer> query = em.createQuery("select e.actorId from FilmActor e where e.filmId = :filmId", Integer.class);
        query.setParameter("filmId", id);
        return query.getResultList();
    }

    public void  filmActorBinden (int fid,int aid){
        FilmActor ac = new FilmActor() ;
        ac.setActorId(aid);
        ac.setFilmId(fid);
        try {
            em.persist(ac);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
