package dvd_rental_film.repository;

import dvd_rental_film.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    // Custom query to find actors by film ID
    List<Actor> findByFilmId(int filmId);

    // Custom query to count all actors
    long count();
}