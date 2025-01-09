package dvd_rental_film.control;

import dvd_rental_film.entity.Film;
import dvd_rental_film.repository.FilmRepository;
import dvd_rental_film.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Film> getByFilmID(int id) {
        return filmRepository.findById(id);
    }

    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    public Film saveOrUpdateFilm(Film film) {
        return filmRepository.save(film);
    }

    public Film mergeFilm(Film film) {
        return filmRepository.save(film);
    }

    public Film updateFilm(Film film) {
        Film currentFilm = filmRepository.findById(film.getFilmId())
                .orElseThrow(() -> new RuntimeException("Film not found with ID: " + film.getFilmId()));

        currentFilm.setTitle(film.getTitle());
        currentFilm.setDescription(film.getDescription());
        currentFilm.setReleaseYear(film.getReleaseYear());
        currentFilm.setCategories(film.getCategories());

        return filmRepository.save(currentFilm);
    }

    public void deleteFilm(Film film) {
        filmRepository.delete(film);
    }

    public long countAll() {
        return filmRepository.count();
    }


    public List<Film> getAllLimitOffset(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit); // offset is the page number
        return filmRepository.findAllWithPagination(pageable);
    }
}
