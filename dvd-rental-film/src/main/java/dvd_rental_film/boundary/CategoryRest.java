package dvd_rental_film.boundary;

import dvd_rental_film.control.CategoryService;
import dvd_rental_film.entity.Category;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categories")
public class CategoryRest {
    @Inject
    private CategoryService acs;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getall() {
        return Response.ok(acs.getAll().stream().map(Category::getName).toList()).build();
    }

}
