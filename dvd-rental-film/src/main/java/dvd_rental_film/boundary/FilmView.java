package dvd_rental_film.boundary;

import dvd_rental_film.boundary.json.ActorJson;
import dvd_rental_film.boundary.json.FilmJson;
import dvd_rental_film.control.*;
import dvd_rental_film.entity.Actor;
import dvd_rental_film.entity.Category;
import dvd_rental_film.entity.Film;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Path("films")
public class FilmView {
    @Inject
    private ActorsService acs;
    @Inject
    private FilmService fs;
    @Inject
    private FilmActorService fac;
    @Inject
    private FilmCategoryService fcs;
    @Inject
    private LanguageService ls;

    @Inject
    private CategoryService cs;
    @Inject
    private UrlProperties urlProperties;

    @POST
    public Response createFilm(FilmJson filmJson) {
        if(filmJson.rentalRate == null || filmJson.rentalDuration == null || filmJson.replacementCost == null || filmJson.title == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Film film = new Film();
        film.setDescription(filmJson.description);
        film.setLanguageId(ls.getIdByName(filmJson.language));
        film.setLength(filmJson.length);
        film.setRating(filmJson.rating);
        film.setReleaseYear(filmJson.releaseYear);
        film.setRentalDuration(filmJson.rentalDuration);
        film.setRentalRate(filmJson.rentalRate);
        film.setReplacementCost(filmJson.replacementCost);
        film.setTitle(filmJson.title);
        film = fs.mergeFilm(film);
        return Response.ok().header("Location", urlProperties.getFilmBase() + "resources/films/" + film.getFilmId()).build();
    }

    @Path("count")
    @GET
    public Response count() {
        return Response.ok(fs.countall()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset) {
        return Response.ok(fs.getAllLimitOffset(limit, offset).stream().map(film -> new FilmJson(
                new Href(urlProperties.getFilmBase() + "resources/films/" + film.getFilmId() + "/actors" ),
                fcs.getCategoryIdByFilmId(film.getFilmId()).stream().map(cId -> cs.getNameById(cId)).toList(),
                film.getDescription(),
                film.getFilmId(),
                ls.getNameById(film.getLanguageId()),
                film.getLength(),
                film.getRating(),
                film.getReleaseYear(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getReplacementCost(),
                film.getTitle()
        )).toList()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByID(@PathParam("id") int id) {
        Film film = fs.getByFilmID(id);
        if(film != null) {
            FilmJson filmJson = new FilmJson(
                    new Href(urlProperties.getFilmBase() + "resources/films/" + film.getFilmId() + "/actors"),
                    fcs.getCategoryIdByFilmId(film.getFilmId()).stream().map(cId -> cs.getNameById(cId)).toList(),
                    film.getDescription(),
                    film.getFilmId(),
                    ls.getNameById(film.getLanguageId()),
                    film.getLength(),
                    film.getRating(),
                    film.getReleaseYear(),
                    film.getRentalDuration(),
                    film.getRentalRate(),
                    film.getReplacementCost(),
                    film.getTitle()
            );
            return Response.ok(filmJson).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("{id}")
    @DELETE
    public Response delete(@PathParam("id") int id) {
        Film film = fs.getByFilmID(id);
        if(film == null) return Response.status(Response.Status.NOT_FOUND).build();
        fs.delete(film);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    @Path("{id}")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int i, FilmJson filmJson) {
        Film film = fs.getByFilmID(i);
        if(film == null) return Response.status(Response.Status.NOT_FOUND).build();
        if(filmJson.title != null) film.setTitle(filmJson.title);
        if(filmJson.description != null) film.setDescription(filmJson.description);
        if(filmJson.rentalRate != null) film.setRentalRate(filmJson.rentalRate);
        if(filmJson.replacementCost != null) film.setReplacementCost(filmJson.replacementCost);
        fs.updateFilm(film);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    @GET
    @Path("{id}/actors")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActorByfilmID(@PathParam("id") int id) {
        List<ActorJson> actorJsons = fac.getActorIdListByFilmId(id).stream().map(aId -> acs.getByActorID(aId)).map(actor -> new ActorJson(
                new Href(urlProperties.getFilmBase() + "resources/actors/" + actor.getActorId() + "/films"),
                actor.getFirstName(),
                actor.getActorId(),
                actor.getLastName()
        )).toList();
        return fs.getByFilmID(id) != null ? Response.ok(actorJsons).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("{id}/actors/{actorId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addActor(@PathParam("id") int id, @PathParam("actorId") int actorId) {
        Film film = fs.getByFilmID(id);
        Actor actor = acs.getByActorID(actorId);
        if(actor == null || film == null) return Response.status(Response.Status.NOT_FOUND).build();
        fac.filmActorBinden(id, actorId);
        return Response.status(Response.Status.CREATED).header("Location", urlProperties.getFilmBase() + "resources/films/" + id + "/actors").build();
    }

    @GET
    @Path("{id}/categories")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategByfilmID(@PathParam("id") int id) {
        List<String> categories = fcs.getCategoryIdByFilmId(id).stream().map(cId -> cs.getNameById(cId)).toList();
        return fs.getByFilmID(id) != null ? Response.ok(categories).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("{id}/categories/{category}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response filmCateg(@PathParam("id") int id,@PathParam("category") int categoryid) {
        Film film = fs.getByFilmID(id);
        Category category = cs.getById(categoryid);
        if(film == null || category == null) return Response.status(Response.Status.NOT_FOUND).build();
        fcs.filmCategBinden(id, categoryid);
        return Response.status(Response.Status.CREATED).header("Location", urlProperties.getFilmBase() + "resources/films/" + id + "/categories").build();
    }

}
