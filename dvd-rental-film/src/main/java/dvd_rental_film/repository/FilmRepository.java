package dvd_rental_film.repository;

import dvd_rental_film.entity.Film;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query("SELECT f FROM Film f")
    List<Film> findAllWithPagination(Pageable pageable);

}