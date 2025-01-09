package dvd_rental_film.boundary;

import dvd_rental_film.control.LanguageService;
import dvd_rental_film.entity.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/languages")
public class LanguagesRest {

    private final LanguageService languageService;

    @Autowired
    public LanguagesRest(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getAll() {
        List<String> languages = languageService.getAll()
                .stream()
                .map(Language::getName)
                .collect(Collectors.toList());
        return ResponseEntity.ok(languages);
    }
}
