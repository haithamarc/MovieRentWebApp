package de.ostfalia.ftse.dvd_rental_customer.rest.address;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AddressJson {
    public String address;
    public String address2;
    public String city;
    public String country;
    public String district;
    public int id;
    public String phone;
    public String postalCode;
}
