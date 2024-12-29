package boundary;

import control.StoreService;
import entity.Store;
import entity.json.StoreJson;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("stores")
public class StoreApi {


    @Inject
    StoreService storeService;

    public StoreApi(){

    }

    /*a get method
      located under /resources/stores/{id}
      that receives a storeid as parameter
      get the Store Object from the id using storeservice
      produces a converted storeJson if a store is found or null if not
      as Json_Application
    */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StoreJson getStoreById(@PathParam("id") int id) {
        Store store = storeService.getStoreById(id);
        if (store != null) {
            return store.storeToStoreJson();
        } else {
            return null;
        }
    }



    /*a get method
      located under /resources/stores/
      produces the actual count of stores
      as Json_Application
    */
    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoreCount() {
        return Response.status(200).entity(storeService.getStoreCount()).build();


    }
}