package dvd_rental_film.boundary;

import dvd_rental_film.boundary.UrlProperties;
import dvd_rental_film.boundary.json.ActorJson;
import dvd_rental_film.boundary.json.FilmJson;
import dvd_rental_film.control.*;
import dvd_rental_film.entity.Actor;
import dvd_rental_film.entity.Category;
import dvd_rental_film.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/films")
public class FilmView{

    @Autowired
    private ActorsService actorsService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmActorService filmActorService;

    @Autowired
    private FilmCategoryService filmCategoryService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UrlProperties urlProperties;

    @PostMapping
    public ResponseEntity<Void> createFilm(@RequestBody FilmJson filmJson) {
        if (filmJson.getRentalRate() == null || filmJson.getRentalDuration() == null || filmJson.getReplacementCost() == null || filmJson.getTitle() == null) {
            return ResponseEntity.badRequest().build();
        }

        Film film = new Film();
        film.setDescription(filmJson.getDescription());
        film.setLanguageId(languageService.getIdByName(filmJson.getLanguage()));
        film.setLength(filmJson.getLength());
        film.setRating(filmJson.getRating());
        film.setReleaseYear(filmJson.getReleaseYear());
        film.setRentalDuration(filmJson.getRentalDuration());
        film.setRentalRate(filmJson.getRentalRate());
        film.setReplacementCost(filmJson.getReplacementCost());
        film.setTitle(filmJson.getTitle());

        Film createdFilm = filmService.mergeFilm(film);
        return ResponseEntity.created(URI.create(urlProperties.getFilmBase() + "resources/films/" + createdFilm.getFilmId())).build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(filmService.countAll());
    }

    @GetMapping()
    public ResponseEntity<List<FilmJson>> getAll(@RequestParam(defaultValue = "100") int limit, @RequestParam(defaultValue = "0") int offset) {
        System.out.println("Haitham ");
        List<Film> filmstest = filmService.getAll();
        for (Film film : filmstest) {
            System.out.println("Haitham "+film.getFilmId());
        }
        List<FilmJson> films = filmService.getAll().stream().map(film -> new FilmJson(
                new Href(urlProperties.getFilmBase() + "resources/films/" + film.getFilmId() + "/actors"),
                filmCategoryService.getCategoryIdByFilmId(film.getFilmId()).stream().map(categoryService::getNameById).collect(Collectors.toList()),
                film.getDescription(),
                film.getFilmId(),
                languageService.getNameById(film.getLanguageId()),
                film.getLength(),
                film.getRating(),
                film.getReleaseYear(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getReplacementCost(),
                film.getTitle()
        )).collect(Collectors.toList());
        return ResponseEntity.ok(films);
    }

    /*
        @GetMapping()
    public ResponseEntity<List<FilmJson>> getAll(@RequestParam(defaultValue = "100") int limit, @RequestParam(defaultValue = "0") int offset) {
        System.out.println("Haitham ");
        List<Film> filmstest = filmService.getAll();
        for (Film film : filmstest) {
            System.out.println("Haitham "+film.getFilmId());
        }
        List<FilmJson> films = filmService.getAllLimitOffset(limit, offset).stream().map(film -> new FilmJson(
                new Href(urlProperties.getFilmBase() + "resources/films/" + film.getFilmId() + "/actors"),
                filmCategoryService.getCategoryIdByFilmId(film.getFilmId()).stream().map(categoryService::getNameById).collect(Collectors.toList()),
                film.getDescription(),
                film.getFilmId(),
                languageService.getNameById(film.getLanguageId()),
                film.getLength(),
                film.getRating(),
                film.getReleaseYear(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getReplacementCost(),
                film.getTitle()
        )).collect(Collectors.toList());
        return ResponseEntity.ok(films);
    }
     */

    @GetMapping("/{id}")
    public ResponseEntity<FilmJson> getById(@PathVariable int id) {
        Film film = filmService.getByFilmID(id)
                .orElseThrow(() -> new RuntimeException("Film not found with ID: " + id));
        if (film == null) {
            return ResponseEntity.notFound().build();
        }

        FilmJson filmJson = new FilmJson(
                new Href(urlProperties.getFilmBase() + "resources/films/" + film.getFilmId() + "/actors"),
                filmCategoryService.getCategoryIdByFilmId(film.getFilmId()).stream().map(categoryService::getNameById).collect(Collectors.toList()),
                film.getDescription(),
                film.getFilmId(),
                languageService.getNameById(film.getLanguageId()),
                film.getLength(),
                film.getRating(),
                film.getReleaseYear(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getReplacementCost(),
                film.getTitle()
        );

        return ResponseEntity.ok(filmJson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Film film = filmService.getByFilmID(id)
                .orElseThrow(() -> new RuntimeException("Film not found with ID: " + id));
        if (film == null) {
            return ResponseEntity.notFound().build();
        }

        filmService.deleteFilm(film);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody FilmJson filmJson) {
        Film film = filmService.getByFilmID(id)
                .orElseThrow(() -> new RuntimeException("Film not found with ID: " + id));
        if (film == null) {
            return ResponseEntity.notFound().build();
        }

        if (filmJson.getTitle() != null) {
            film.setTitle(filmJson.getTitle());
        }
        if (filmJson.getDescription() != null) {
            film.setDescription(filmJson.getDescription());
        }
        if (filmJson.getRentalRate() != null) {
            film.setRentalRate(filmJson.getRentalRate());
        }
        if (filmJson.getReplacementCost() != null) {
            film.setReplacementCost(filmJson.getReplacementCost());
        }

        filmService.updateFilm(film);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/actors")
    public ResponseEntity<List<ActorJson>> getActorsByFilmId(@PathVariable int id) {
        if (filmService.getByFilmID(id) == null) {
            return ResponseEntity.notFound().build();
        }

        List<ActorJson> actors = filmActorService.getActorIdListByFilmId(id).stream()
                .map(actorsService::getByActorID)
                .map(actor -> new ActorJson(
                        new Href(urlProperties.getFilmBase() + "resources/actors/" + actor.getActorId() + "/films"),
                        actor.getFirstName(),
                        actor.getActorId(),
                        actor.getLastName()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(actors);
    }

    @PutMapping("/{id}/actors/{actorId}")
    public ResponseEntity<Void> addActor(@PathVariable int id, @PathVariable int actorId) {
        Film film = filmService.getByFilmID(id)
                .orElseThrow(() -> new RuntimeException("Film not found with ID: " + id));
        Actor actor = actorsService.getByActorID(actorId);

        if (film == null || actor == null) {
            return ResponseEntity.notFound().build();
        }

        filmActorService.bindFilmAndActor(id, actorId);
        return ResponseEntity.created(URI.create(urlProperties.getFilmBase() + "resources/films/" + id + "/actors")).build();
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<List<String>> getCategoriesByFilmId(@PathVariable int id) {
        if (filmService.getByFilmID(id) == null) {
            return ResponseEntity.notFound().build();
        }

        List<String> categories = filmCategoryService.getCategoryIdByFilmId(id).stream()
                .map(categoryService::getNameById)
                .collect(Collectors.toList());

        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}/categories/{categoryId}")
    public ResponseEntity<Void> addCategoryToFilm(@PathVariable int id, @PathVariable int categoryId) {
        Film film = filmService.getByFilmID(id)
                .orElseThrow(() -> new RuntimeException("Film not found with ID: " + id));
        Category category = categoryService.getById(categoryId);

        if (film == null || category == null) {
            return ResponseEntity.notFound().build();
        }

        filmCategoryService.bindFilmToCategory(id, categoryId);
        return ResponseEntity.created(URI.create(urlProperties.getFilmBase() + "resources/films/" + id + "/categories")).build();
    }
}
