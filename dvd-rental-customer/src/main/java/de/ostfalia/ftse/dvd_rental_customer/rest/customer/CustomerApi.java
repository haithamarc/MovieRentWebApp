package de.ostfalia.ftse.dvd_rental_customer.rest.customer;

import de.ostfalia.ftse.dvd_rental_customer.control.AddressController;
import de.ostfalia.ftse.dvd_rental_customer.control.CustomerController;
import de.ostfalia.ftse.dvd_rental_customer.control.PaymentController;
import de.ostfalia.ftse.dvd_rental_customer.entity.Customer;
import de.ostfalia.ftse.dvd_rental_customer.entity.Payment;
import de.ostfalia.ftse.dvd_rental_customer.rest.Href;
import de.ostfalia.ftse.dvd_rental_customer.rest.UrlProperties;
import de.ostfalia.ftse.dvd_rental_customer.rest.payment.PaymentJson;

import javax.inject.Inject;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Path("/customers")
public class CustomerApi {

    @Inject
    private CustomerController customerController;

    @Inject
    private AddressController addressController;

    @Inject
    private PaymentController paymentController;

    @Inject
    private UrlProperties urlProperties;

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerJson customerJson, @QueryParam("address") @NotNull Integer addressId, @QueryParam("store") @NotNull Integer storeId) {
        Customer customer = new Customer(
                customerJson.id,
                storeId,
                customerJson.firstName,
                customerJson.lastName,
                customerJson.email,
                addressController.get(addressId),
                customerJson.activebool,
                LocalDate.now(),
                LocalDateTime.now(),
                customerJson.active
        );
        if(VALIDATOR.validate(customer).isEmpty()) {
            customerController.create(customer);
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerCount() {
        return Response.ok(customerController.count()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") int id) {
        Customer c = customerController.get(id);
        if(c != null) {
            CustomerJson res = new CustomerJson(
                    c.getActive(),
                    c.isActiveBool(),
                    new Href(urlProperties.getCustomerBase() + "resources/addresses/" + c.getAddress().getAddressId()),
                    c.getCreateDate(),
                    c.getEmail(),
                    c.getFirstName(),
                    c.getCustomerId(),
                    c.getLastName(),
                    new Href(urlProperties.getStoreBase() + "resources/stores/" + c.getStoreId())
            );
            return Response.ok(res).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}/payments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPayments(@PathParam("id") int id) {
        Customer c = customerController.get(id);
        List<Payment> paymentList = paymentController.getByCustomer(c);
        if(c != null) {
            List<PaymentJson> res = paymentList.stream().map(p -> new PaymentJson(
                    p.getAmount(),
                    new Href(urlProperties.getCustomerBase() + "resources/customers/" + p.getCustomer().getCustomerId()),
                    p.getPaymentId(),
                    new Href(urlProperties.getStoreBase() + "resources/rentals/" + p.getRentalId()),
                    new Href(urlProperties.getStoreBase() + "resources/staff/" + p.getStaffId())
            )).toList();
            return Response.ok(res).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
