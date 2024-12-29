package dvd_rental_film.control;

import dvd_rental_film.entity.Actor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;


@Stateless
public class ActorsService {
    @PersistenceContext(unitName = "dvdrentalfilm") // name aus persist.xml : unit
    private EntityManager em;



    public Class<Actor> getEntityClass() {
        return Actor.class;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public Actor getByActorID(int id) {
        return em.find(Actor.class, id);
    }

    public List<Actor> getAll() {
        TypedQuery<Actor> query = em.createNamedQuery("actor.getAll", Actor.class);
        return query.setMaxResults(100).getResultList();
    }
    public List<Actor> getByFilmId(int id) {
        TypedQuery<Actor> query = em.createNamedQuery("actor.film", Actor.class);
        query.setParameter("id", id);
        return query.getResultList() ;
    }
    public void createActor(Actor ac) {
        em.persist(ac);
    }
    public void updateActor(@NotNull Actor ac) {
        em.merge(ac);
    }

    public void delete(Actor actor) {
        em.remove(em.merge(actor));
    }
    public int  countall(){
        return ((Number)em.createNamedQuery("actor.countAll").getSingleResult()).intValue();
    }
    public List<Actor> getAlloffilm(int id ) {
        TypedQuery<Actor> query = em.createNamedQuery("actor.film", Actor.class);
        query.setParameter("id", id);
        return query.getResultList() ;
    }
}