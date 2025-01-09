package dvd_rental_film.control;

import dvd_rental_film.entity.FilmCategory;
import dvd_rental_film.repository.FilmCategoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmCategoryService {

    private final FilmCategoryRepository filmCategoryRepository;

    public FilmCategoryService(FilmCategoryRepository filmCategoryRepository) {
        this.filmCategoryRepository = filmCategoryRepository;
    }

    @Transactional
    public void bindFilmToCategory(int filmId, int categoryId) {
        FilmCategory filmCategory = new FilmCategory();
        filmCategory.setCategoryId(categoryId);
        filmCategory.setFilmId(filmId);
        filmCategoryRepository.save(filmCategory);
    }

    public List<Integer> getCategoryIdByFilmId(int filmId) {
        List<FilmCategory> filmCategories = filmCategoryRepository.findByFilmId(filmId);
        return filmCategories.stream()
                .map(FilmCategory::getCategoryId)
                .collect(Collectors.toList());
    }
}