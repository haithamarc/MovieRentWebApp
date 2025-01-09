package dvd_rental_film.control;

import dvd_rental_film.entity.Language;

import dvd_rental_film.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    public String getNameById(int id) {
        return languageRepository.findById(id)
                .map(Language::getName)
                .orElse(null);  // Return null if no language is found
    }

    public int getIdByName(String name) {
        return languageRepository.findIdByName(name);  // Return 1 if no result is found
    }
}