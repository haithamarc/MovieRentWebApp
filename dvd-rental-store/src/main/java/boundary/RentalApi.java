package boundary;

import control.InventoryService;
import control.RentalService;
import control.StaffService;
import entity.Inventory;
import entity.Rental;
import entity.json.RentalFromPost;
import entity.Staff;
import entity.json.RentalJson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;

@Path("rentals")
public class RentalApi {

    @Inject
    RentalService rentalService;

    @Inject
    InventoryService inventoryService;

    @Inject
    StaffService staffService;


    public RentalApi() {

    }
    /*a get method
      located under /resources/rentals/{id}
      that receives a rentalid as parameter
      get the Rental Object from the id using rentalservice
      produces two responses
      -200 : a rentalJSon from the rentalObject if rental was found
      -404 : 404 notFound if rental not found
    */
    @GET
    @Path("{id}")
    public Response getRentalById(@PathParam("id") Integer id) {
        Rental rental = rentalService.getRentalById(id);
        String message = "Rental not found";
        if (rental != null) {
            RentalJson rentalJson = rental.rentalToRentalJson();
            return Response.status(200).entity(rentalJson).type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(message).type(MediaType.TEXT_PLAIN).build();

        }


    }
    /*
    a put method
    located under /resources/rentals/{id}/returned
    receive a rentalid as parameter
    try to terminate rental and produces three possible responses:
    -200 : rental is terminated
    -404 : rental not found
    -422 : rental already terminated
     */

    @Path("{id}/returned")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response terminateRentalById(@PathParam("id") int id) {
        String message = "Rental with id " + id + " is Terminated";
        String message2 = "Rental with id " + id + " is not found";
        String message3 = "Rental with id " + id + " is already terminated";

        Rental rental = rentalService.getRentalById(id);

        Response r1 = Response.status(200).entity(message).type(MediaType.TEXT_PLAIN).build();
        Response r2 = Response.status(404).entity(message2).type(MediaType.TEXT_PLAIN).build();
        Response r3 = Response.status(422).entity(message3).type(MediaType.TEXT_PLAIN).build();
        Response resultResponse = null;

        if (rental == null) {
            resultResponse =  r2;
        } else if (rental.getReturn_date() != null) {
            resultResponse = r3;
        } else {
            boolean result = rentalService.terminateRentalById(id);
            if (result) {
                resultResponse=r1;
            }

        }
        return resultResponse;


    }

    /*
    a post method
    located under /resources/rentals
    consumes a json form should be the format {"inventory":234,"customer":217,"staff":1,"date":"2020-04-06 15:09"}
    to create a new rental
    and produces two responses
    -201 : rental is created
    -404 : error incorrect form
     */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createRental(RentalFromPost rentalFromPost) {

        Inventory inventory = inventoryService.getInventoryById(rentalFromPost.getInventory());
        int customerId = rentalFromPost.getCustomer();
        Staff staff = staffService.getStaffById(rentalFromPost.getStaff());
        Timestamp ts = null;
        boolean dateConversion;
        try {
            ts = Timestamp.valueOf(rentalFromPost.getDate() + ":00");
            dateConversion = true;
        } catch (Exception e) {
            dateConversion = false;
        }
        Timestamp tsNow = new Timestamp(System.currentTimeMillis());


        String message1 = "Rental created";
        String message2 = "only allowed: customer (int), inventory (int), staff (int),\\\n" +
                "            \\ date (yyyy-MM-dd HH:mm)\"\n" +
                "  /resources/rentals/{id}:";
        Response r1 = Response.status(201).entity(message1).type(MediaType.TEXT_PLAIN).build();
        Response r2 = Response.status(404).entity(message2).type(MediaType.TEXT_PLAIN).build();
        Response result = null;
        if (inventory == null || staff == null || !dateConversion) {
            result = r2;

        } else {

            Rental rental = new Rental(ts, inventory, customerId, null, staff, tsNow);
            boolean resultInsert = rentalService.insertRental(rental);
            if (resultInsert) {
                result = r1;
            }
        }
        return result;
    }
}
