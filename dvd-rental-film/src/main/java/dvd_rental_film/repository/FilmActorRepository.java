package dvd_rental_film.repository;

import dvd_rental_film.entity.FilmActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmActorRepository extends JpaRepository<FilmActor, Integer> {

    @Query("SELECT e.filmId FROM FilmActor e WHERE e.actorId = :actorId")
    List<Integer> findFilmIdsByActorId(@Param("actorId") int actorId);

    @Query("SELECT e.actorId FROM FilmActor e WHERE e.filmId = :filmId")
    List<Integer> findActorIdsByFilmId(@Param("filmId") int filmId);
}