package boundary;

import entity.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;


@Path("/")
public class WelcomeApi {



    final Resource Resource1 = new Resource("Welcome to Store API!","GET","/resources");
    final Resource Resource2 = new Resource("Get Inventory by id","GET","/resources/inventories/{id}");
    final Resource Resource3 = new Resource("Get Inventory by filmId","GET","/resources/inventories/film/{filmId}");
    final Resource Resource4 = new Resource("Create Rental","POST","/resources/rentals");
    final Resource Resource5 = new Resource("Get Rental by id","GET","/resources/rentals/{id}");
    final Resource Resource6 = new Resource("Terminate Rental","PUT","/resources/rentals/{id}/returned");
    final Resource Resource7 = new Resource("Get Staff by id","GET","/resources/staff/{id}");
    final Resource Resource8 = new Resource("Get Store count","GET","/resources/stores/count");
    final Resource Resource9 = new Resource("get Store by id","GET","/resources/stores/{id}");
    final ArrayList<Resource> arr = new ArrayList<>(Arrays.asList(Resource1, Resource2, Resource3, Resource4, Resource5, Resource6, Resource7, Resource8, Resource9));

    public WelcomeApi() {

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response welcome() {

        GenericEntity<ArrayList<Resource>> entity = new GenericEntity<ArrayList<Resource>>(arr){};
        return Response.ok(entity).build();
    }
}
