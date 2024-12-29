package boundary;

import control.InventoryService;

import entity.Inventory;
import entity.json.InventoryJson;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;




/*
 Inventory API resources found under /inventories/

*/


@Path("inventories")
public class InventoryApi {

        @Inject
        InventoryService inventoryService;

        public InventoryApi() {
        }

        /*a get method
        located under /resources/inventories/{id}
        that receives an inventoryid as parameter
        get the Inventory Object using inventoryservice
        produces a converted inventoryJson if inventory found or null if not
        as Json_Application
         */
        @GET
        @Path("{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public InventoryJson getInventoryById(@PathParam("id") Integer id) {
                Inventory inventory = inventoryService.getInventoryById(id);
                if (inventory != null) {
                        return inventory.inventoryToInventoryJson();
                } else {
                        return null;
                }


        }
        /*
        a get method
        located under /resources/inventories/film/{filmId}
        receives filmId as parameter
        get an inventorylist of all inventory matching that film id using inventoryservice
        produces a list of inventoryjson or an empty list if no result found
        as an application_json
         */
        @GET
        @Path("film/{filmId}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getInventoryByFilmId(@PathParam("filmId") Integer filmId) {
                List<Inventory> inventoryList = inventoryService.getInventoryByFilmId(filmId);
                ArrayList<InventoryJson> inventoryJsonList = new ArrayList<InventoryJson>();
                if (!inventoryList.isEmpty()) {
                        for (Inventory inventory : inventoryList) {
                                inventoryJsonList.add(inventory.inventoryToInventoryJson());
                        }
                }
                GenericEntity<ArrayList<InventoryJson>> entity = new GenericEntity<ArrayList<InventoryJson>>(inventoryJsonList){};
                return Response.ok(entity).build();


        }



}


