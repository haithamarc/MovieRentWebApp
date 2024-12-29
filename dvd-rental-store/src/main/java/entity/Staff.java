package entity;

import entity.json.StaffJson;

import javax.persistence.*;
import java.sql.Timestamp;

@NamedQuery(name= "getStaffById", query ="SELECT s from Staff s where s.staff_id = :staff_id")
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staff_id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @OneToOne
    @JoinColumn(name ="address_id")
    private Address address;

    @Column(name = "email")
    private String email;

    @Column(name = "store_id")
    private int store_id;

    @Column(name = "active")
    private boolean active;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_update")
    private Timestamp last_update;

    @Column(name = "picture")
    private byte[] picture;

    public Staff() {
    }

    public Staff(String first_name, String last_name, Address address, String email, int store_id, boolean active, String username, String password, Timestamp last_update, byte[] picture) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
        this.store_id = store_id;
        this.active = active;
        this.username = username;
        this.password = password;
        this.last_update = last_update;
        this.picture = picture;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }


    public String encodeHexString(byte[] byteArray) {
        String result = "0X";

        if (byteArray != null) {

            for (byte b : byteArray) {
            result += String.format("%02x", b);
            }
        }
        return result;
    }

    public StaffJson staffToStaffJson(){
        StaffJson sj = new StaffJson();
        sj.setActive(this.isActive());
        sj.setEmail(this.getEmail());
        sj.setFirstName(this.getLast_name());
        sj.setId(this.getStaff_id());
        sj.setLastName(this.getLast_name());
        sj.setPassword(this.getPassword());
        if(this.getPicture() != null) {
            sj.setPicture(encodeHexString(this.getPicture()));
        }else {
            sj.setPicture("");
        }
        sj.setUsername(this.getUsername());

        return sj;
    }




    @Override
    public String toString() {
        return "{" +
                "staff_id=" + staff_id +
                " first_name='" + first_name +
                " last_name='" + last_name +
                " address=" + address +
                " email='" + email +
                " store_id=" + store_id +
                " active=" + active +
                " username='" + username +
                " password='" + password +
                " last_update=" + last_update +
                " picture=" + encodeHexString(picture) +
                '}';
    }
}