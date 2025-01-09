package dvd_rental_film.boundary;

import dvd_rental_film.boundary.json.ActorJson;
import dvd_rental_film.boundary.json.FilmJson;
import dvd_rental_film.control.*;
import dvd_rental_film.entity.Actor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@RestController
@RequestMapping("/actors")
public class Actors {

    private final ActorsService actorsService;
    private final FilmActorService filmActorService;
    private final FilmService filmService;
    private final LanguageService languageService;
    private final CategoryService categoryService;
    private final FilmCategoryService filmCategoryService;
    private final UrlProperties urlProperties;

    @Autowired
    public Actors(ActorsService actorsService, FilmActorService filmActorService, FilmService filmService,
                  LanguageService languageService, CategoryService categoryService,
                  FilmCategoryService filmCategoryService, UrlProperties urlProperties) {
        this.actorsService = actorsService;
        this.filmActorService = filmActorService;
        this.filmService = filmService;
        this.languageService = languageService;
        this.categoryService = categoryService;
        this.filmCategoryService = filmCategoryService;
        this.urlProperties = urlProperties;
    }

    @GetMapping
    public ResponseEntity<List<ActorJson>> getAll() {
        List<ActorJson> actorJsons = actorsService.getAll().stream()
                .map(a -> new ActorJson(
                        new Href(urlProperties.getFilmBase() + "actors/" + a.getActorId() + "/films"),
                        a.getFirstName(),
                        a.getActorId(),
                        a.getLastName()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(actorJsons);
    }

    @PostMapping
    public ResponseEntity<Void> createActor(@RequestBody ActorJson actorJson) {
        if (actorJson.getFirstName() == null || actorJson.getLastName() == null) {
            return ResponseEntity.badRequest().build();
        }
        Actor actor = new Actor();
        actor.setFirstName(actorJson.getFirstName());
        actor.setLastName(actorJson.getLastName());
        actor.setLastUpdate(Timestamp.from(Instant.now()));
        actorsService.createActor(actor);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(actorsService.countAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorJson> getById(@PathVariable int id) {
        Actor actor = actorsService.getByActorID(id);
        if (actor != null) {
            ActorJson actorJson = new ActorJson(
                    new Href(urlProperties.getFilmBase() + "actors/" + actor.getActorId() + "/films"),
                    actor.getFirstName(),
                    actor.getActorId(),
                    actor.getLastName()
            );
            return ResponseEntity.ok(actorJson);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody ActorJson actorJson) {
        Actor actor = actorsService.getByActorID(id);
        if (actor == null) {
            return ResponseEntity.notFound().build();
        } else if (actorJson.getFirstName() == null || actorJson.getLastName() == null) {
            return ResponseEntity.badRequest().build();
        } else {
            actor.setFirstName(actorJson.getFirstName());
            actor.setLastName(actorJson.getLastName());
            actor.setLastUpdate(Timestamp.from(Instant.now()));
            actorsService.updateActor(actor);
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Actor actor = actorsService.getByActorID(id);
        if (actor != null) {
            actorsService.deleteActor(actor);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/films")
    public ResponseEntity<List<FilmJson>> getAllActorFilms(@PathVariable int id) {
        // Ensure the actor exists
        if (actorsService.getByActorID(id) == null) {
            return ResponseEntity.notFound().build();
        }

        // Process the films associated with the actor
        List<FilmJson> films = filmActorService.getFilmIdListByActorId(id).stream()
                .map(filmId -> filmService.getByFilmID(filmId)
                        .orElseThrow(() -> new RuntimeException("Film not found with ID: " + filmId)))
                .map(film -> new FilmJson(
                        new Href(urlProperties.getFilmBase() + "films/" + film.getFilmId() + "/actors"),
                        filmCategoryService.getCategoryIdByFilmId(film.getFilmId()).stream()
                                .map(categoryService::getNameById)
                                .collect(Collectors.toList()),
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
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(films);
}
}

