package dvd_rental_film.boundary;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@ApplicationPath("/resources")
public class RestConfig extends Application {
    @GET
    public Response getResources() {
        List<String> resources = new ArrayList<>();
        resources.add("/films");
        resources.add("/categories");
        resources.add("/actors");
        return Response.ok(resources.toArray()).build();
    }

}
