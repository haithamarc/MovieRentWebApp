package dvd_rental_film.repository;

import dvd_rental_film.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {

    // Query to get ID by name
    @Query("select l.id from Language l where l.name = :name")
    Integer findIdByName(String name);
}