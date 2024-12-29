package de.ostfalia.ftse.dvd_rental_customer.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class Resources {

    @Inject
    private UrlProperties urlProperties;
    
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResources() {
        List<String> resources = new ArrayList<>();
        resources.add(urlProperties.getCustomerBase() + "resources/addresses");
        resources.add(urlProperties.getCustomerBase() + "resources/customers");
        resources.add(urlProperties.getCustomerBase() + "resources/payments");
        return Response.ok(resources.toArray()).build();
    }
}
