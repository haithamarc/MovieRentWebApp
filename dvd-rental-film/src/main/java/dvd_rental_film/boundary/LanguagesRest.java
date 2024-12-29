package dvd_rental_film.boundary;


import dvd_rental_film.control.LanguageService;
import dvd_rental_film.entity.Language;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("languages")
public class LanguagesRest {
    @Inject
    private LanguageService lns;
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAll() {
        return Response.ok(lns.getAll().stream().map(Language::getName).toList()).build();
    }
}
