package dvd_rental_film.boundary;

import dvd_rental_film.boundary.json.ActorJson;
import dvd_rental_film.boundary.json.FilmJson;
import dvd_rental_film.control.*;
import dvd_rental_film.entity.Actor;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Path("/actors")
public class Actors {
    @Inject
    private ActorsService acs;

    @Inject
    private FilmActorService facs;

    @Inject
    private FilmService fs;

    @Inject
    private LanguageService ls;

    @Inject
    private CategoryService cs;

    @Inject
    private FilmCategoryService fcs;

    @Inject
    private UrlProperties urlProperties;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getall() {
        List<ActorJson> actorJsons = acs.getAll().stream().map(a -> new ActorJson(
                new Href(urlProperties.getFilmBase() + "resources/actors/" + a.getActorId() + "/films"),
                a.getFirstName(),
                a.getActorId(),
                a.getLastName()
        )).toList();
        return Response.ok(actorJsons).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createActor(ActorJson actorJson) {
        if(actorJson.firstName == null || actorJson.lastName == null) return Response.status(Response.Status.BAD_REQUEST).build();
        Actor actor = new Actor();
        actor.setFirstName(actorJson.firstName);
        actor.setLastName(actorJson.lastName);
        actor.setLastUpdate(Timestamp.from(Instant.now()));
        acs.createActor(actor);
        return Response.ok().build();
    }

    @Path("count")
    @GET
    public Response count() {
        return Response.ok(acs.countall()).build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Actor actor = acs.getByActorID(id);
        if(actor != null) {
            ActorJson actorJson = new ActorJson(
                    new Href(urlProperties.getFilmBase() + "resources/actors/" + actor.getActorId() + "/films"),
                    actor.getFirstName(),
                    actor.getActorId(),
                    actor.getLastName()
            );
            return Response.ok(actorJson).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, ActorJson actorJson){
        Actor act = acs.getByActorID(id);
        if(act == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else if(actorJson.firstName == null || actorJson.lastName == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            act.setFirstName(actorJson.firstName);
            act.setLastName(actorJson.lastName);
            act.setLastUpdate(Timestamp.from(Instant.now()));
            acs.updateActor(act);
            return Response.ok().build();
        }
    }

    @Path("{id}")
    @DELETE
    public Response delete(@PathParam("id") int id) {
        Actor actor = acs.getByActorID(id);
        if(actor != null) {
            acs.delete(actor);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("{id}/films")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getallActorfilms(@PathParam("id") int id) {
        List<FilmJson> films = facs.getFilmIdListByActorId(id).stream().map(fId -> fs.getByFilmID(fId)).map(film -> new FilmJson(
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
        )).toList();
        return acs.getByActorID(id) != null ? Response.ok(films).build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}