package de.ostfalia.ftse.dvd_rental_customer.rest.payment;

import de.ostfalia.ftse.dvd_rental_customer.control.CustomerController;
import de.ostfalia.ftse.dvd_rental_customer.control.PaymentController;
import de.ostfalia.ftse.dvd_rental_customer.entity.Customer;
import de.ostfalia.ftse.dvd_rental_customer.entity.Payment;
import de.ostfalia.ftse.dvd_rental_customer.rest.Href;
import de.ostfalia.ftse.dvd_rental_customer.rest.UrlProperties;

import javax.inject.Inject;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/payments")
public class PaymentApi {

    @Inject
    private PaymentController paymentController;

    @Inject
    private CustomerController customerController;

    @Inject
    private UrlProperties urlProperties;

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPayment(PaymentReqBody paymentReqBody) {
        Customer c = customerController.get(paymentReqBody.customer);
        if(c != null) {
            Payment payment = new Payment(
                    0,
                    c,
                    paymentReqBody.staff,
                    paymentReqBody.rental,
                    paymentReqBody.amount,
                    paymentReqBody.date
            );
            if(VALIDATOR.validate(payment).isEmpty()) {
                paymentController.create(payment);
                return Response.status(Response.Status.CREATED).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPayment(@PathParam("id") int id) {
        Payment p = paymentController.get(id);
        if(p != null) {
            PaymentJson res = new PaymentJson(
                    p.getAmount(),
                    new Href(urlProperties.getCustomerBase() + "resources/customers/" + p.getCustomer().getCustomerId()),
                    p.getPaymentId(),
                    new Href(urlProperties.getStoreBase() + "resources/rentals/" + p.getRentalId()),
                    new Href(urlProperties.getStoreBase() + "resources/staff/" + p.getStaffId())
            );
            return Response.ok(res).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Payment p = paymentController.get(id);
        if(p != null) {
            //paymentController.delete(p);  //Kommentar am Anfang entfernen, um richtig zu Loeschen
            System.out.println("Deleted Payment");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
