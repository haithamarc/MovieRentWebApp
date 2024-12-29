package boundary;

import control.StaffService;
import entity.Staff;
import entity.json.StaffJson;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("staff")
public class StaffApi {


    @Inject
    StaffService staffService;

    public StaffApi(){

    }
    /*a get method
      located under /resources/staff/{id}
      that receives a staffid as parameter
      get the staff Object from the id using rentalservice
      produces a converted staffJson if staff found or null if not
      as Json_Application
    */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StaffJson getRentalById(@PathParam("id") int id) {
        Staff staff = staffService.getStaffById(id);
        if (staff != null) {
            return staff.staffToStaffJson();
        } else {
            return null;
        }



    }
}
