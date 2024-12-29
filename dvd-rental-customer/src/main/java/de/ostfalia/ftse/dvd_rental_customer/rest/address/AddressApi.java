package de.ostfalia.ftse.dvd_rental_customer.rest.address;

import de.ostfalia.ftse.dvd_rental_customer.control.AddressController;
import de.ostfalia.ftse.dvd_rental_customer.control.CityController;
import de.ostfalia.ftse.dvd_rental_customer.control.CountryController;
import de.ostfalia.ftse.dvd_rental_customer.entity.Address;
import de.ostfalia.ftse.dvd_rental_customer.entity.City;
import de.ostfalia.ftse.dvd_rental_customer.entity.Country;
import de.ostfalia.ftse.dvd_rental_customer.rest.UrlProperties;

import javax.inject.Inject;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@Path("/addresses")
public class AddressApi {

    @Inject
    private AddressController addressController;

    @Inject
    private CityController cityController;

    @Inject
    private CountryController countryController;

    @Inject
    private UrlProperties urlProperties;

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdresses(@DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset) {
        List<Address> addressList = addressController.get(limit, offset);
        if(addressList != null && !addressList.isEmpty()) {
            return Response.ok(buildResponseList(addressList)).build();
        }
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAddress(AddressJson addressJson) {
        Country country = countryController.getByCountry(addressJson.country);
        City city = cityController.getByCity(addressJson.city);
        if(country != null && city != null) {
            Address address = new Address(
                    addressJson.id,
                    addressJson.address,
                    addressJson.address2,
                    addressJson.district,
                    city,
                    addressJson.postalCode,
                    addressJson.phone,
                    LocalDateTime.now()
            );
            if(VALIDATOR.validate(address).isEmpty()) {
                address = addressController.merge(address);
                return Response
                        .status(Response.Status.CREATED)
                        .header("Location", urlProperties.getCustomerBase() + "resources/addresses/" + address.getAddressId())
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddress(@PathParam("id") int id) {
        Address a = addressController.get(id);
        if(a != null) {
            AddressJson res = new AddressJson(
                    a.getAddress(),
                    a.getAddress2(),
                    a.getCity().getCity(),
                    a.getCity().getCountry().getCountry(),
                    a.getDistrict(),
                    a.getAddressId(),
                    a.getPhone(),
                    a.getPostalCode()
            );
            return Response.ok(res).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private List<AddressJson> buildResponseList(List<Address> addressList) {
        return addressList.stream().map(a -> new AddressJson(
                a.getAddress(),
                a.getAddress2(),
                a.getCity().getCity(),
                a.getCity().getCountry().getCountry(),
                a.getDistrict(),
                a.getAddressId(),
                a.getPhone(),
                a.getPostalCode()
        )).toList();
    }
}
