package entity;

import entity.json.StoreJson;

import javax.persistence.*;
import java.sql.Timestamp;


@NamedQuery(name= "getStoreById", query ="SELECT s from Store s where s.store_id = :store_id")
@NamedQuery(name= "getStoreCount", query ="SELECT COUNT(s.store_id) FROM Store s")

@Entity
@Table(name = "store")
public class Store{




    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int store_id;

    @OneToOne
    @JoinColumn(name = "manager_staff_id" , referencedColumnName ="staff_id")
    private Staff staff;


    @OneToOne
    @JoinColumn(name ="address_id")
    private Address address;


    @Column(name = "last_update")
    private Timestamp last_update;

    public Store() {
    }

    public Store(Staff staff, Address address, Timestamp last_update) {
        this.staff = staff;
        this.address = address;
        this.last_update = last_update;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }


    public StoreJson storeToStoreJson(){
        StoreJson sj = new StoreJson();
        sj.setId(this.getStore_id());
        return sj;
    }
    @Override
    public String toString() {
        return "{" +
                " store_id=" + store_id +
                " staff=" + staff +
                " address=" + address +
                " last_update=" + last_update +
                "}";
    }
}