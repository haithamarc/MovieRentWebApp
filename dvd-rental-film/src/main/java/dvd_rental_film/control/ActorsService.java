package dvd_rental_film.control;

import dvd_rental_film.entity.Actor;
import dvd_rental_film.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorsService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorsService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor getByActorID(int id) {
        return actorRepository.findById(id).orElse(null);  // Return null if actor is not found
    }

    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    public List<Actor> getByFilmId(int filmId) {
        return actorRepository.findByFilmId(filmId);
    }

    public void createActor(Actor actor) {
        actorRepository.save(actor);  // Will persist or update actor if exists
    }

    public void updateActor(Actor actor) {
        actorRepository.save(actor);  // This will automatically update if the actor exists
    }

    public void deleteActor(Actor actor) {
        actorRepository.delete(actor);  // Delete the actor from the database
    }

    public long countAll() {
        return actorRepository.count();  // Get the count of all actors
    }

    public List<Actor> getAllByFilmId(int filmId) {
        return actorRepository.findByFilmId(filmId);
    }
}