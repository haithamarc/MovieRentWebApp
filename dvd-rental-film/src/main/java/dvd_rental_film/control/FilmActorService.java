package dvd_rental_film.control;

import dvd_rental_film.entity.FilmActor;
import dvd_rental_film.repository.FilmActorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmActorService {

    private final FilmActorRepository filmActorRepository;

    public FilmActorService(FilmActorRepository filmActorRepository) {
        this.filmActorRepository = filmActorRepository;
    }

    /**
     * Retrieves a list of Film IDs associated with a specific Actor ID.
     *
     * @param actorId the Actor ID
     * @return a list of Film IDs
     */
    @Transactional(readOnly = true)
    public List<Integer> getFilmIdListByActorId(int actorId) {
        return filmActorRepository.findFilmIdsByActorId(actorId);
    }

    /**
     * Retrieves a list of Actor IDs associated with a specific Film ID.
     *
     * @param filmId the Film ID
     * @return a list of Actor IDs
     */
    @Transactional(readOnly = true)
    public List<Integer> getActorIdListByFilmId(int filmId) {
        return filmActorRepository.findActorIdsByFilmId(filmId);
    }

    /**
     * Binds a Film and Actor by creating an association in the FilmActor table.
     *
     * @param filmId  the ID of the film
     * @param actorId the ID of the actor
     */
    @Transactional
    public void bindFilmAndActor(int filmId, int actorId) {
        FilmActor filmActor = new FilmActor();
        filmActor.setFilmId(filmId);
        filmActor.setActorId(actorId);
        filmActorRepository.save(filmActor);
    }
}
