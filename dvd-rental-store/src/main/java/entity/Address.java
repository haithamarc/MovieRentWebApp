package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "address")
public class Address{

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int address_id;

    @Column(name = "address")
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "district")
    private String district;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="city_id",referencedColumnName ="city_id")
    private City city;


    @Column(name = "postal_code")
    private String postal_code;

    @Column(name = "phone")
    private String phone;

    @Column(name = "last_update")
    private Timestamp last_update;

    public Address() {
    }

    public Address(String address, String address2, String district, City city, String postal_code, String phone, Timestamp last_update) {
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.city = city;
        this.postal_code = postal_code;
        this.phone = phone;
        this.last_update = last_update;
    }

    public int getId() {
        return address_id;
    }

    public void setId(int address_id) {
        this.address_id = address_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "{" +
                " address_id=" + address_id +
                " address='" + address +
                " address2='" + address2 +
                " district='" + district +
                " city=" + city.getCity() +
                " postal_code='" + postal_code +
                " country=" + city.getCountry().getCountry() +
                " phone='" + phone +
                " last_update=" + last_update +
                "}";
    }
}