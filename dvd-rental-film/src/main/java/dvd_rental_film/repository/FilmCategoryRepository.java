package dvd_rental_film.repository;

import dvd_rental_film.entity.FilmCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmCategoryRepository extends JpaRepository<FilmCategory, Integer> {
    List<FilmCategory> findByFilmId(int filmId);
}