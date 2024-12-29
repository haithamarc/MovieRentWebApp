package entity;

import entity.json.Href;
import entity.json.RentalJson;

import javax.persistence.*;
import java.sql.Timestamp;

@NamedQuery(name= "getRentalById", query ="SELECT r from Rental r where r.rental_id = :rental_id")
@SqlResultSetMapping(name="terminateResult", columns = { @ColumnResult(name = "count")})
@NamedNativeQueries({
        @NamedNativeQuery(
                name  = "terminateRentalById",
                query = "UPDATE Rental SET return_date = CURRENT_TIMESTAMP WHERE rental_id = :rental_id"
                ,resultSetMapping = "terminateResult"
        )
})
@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @Column(name = "rental_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rental_id;

    @Column(name = "rental_date")
    private Timestamp rental_date;

    @ManyToOne(optional = false)
    @JoinColumn(name ="inventory_id")
    private Inventory inventory;

    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "return_date")
    private Timestamp return_date;

    @ManyToOne(optional = false)
    @JoinColumn(name ="staff_id")
    private Staff staff;

    @Column(name = "last_update")
    private Timestamp last_update;


    public Rental() {
    }

    public Rental(Timestamp rental_date, Inventory inventory, int customer_id, Timestamp return_date, Staff staff, Timestamp last_update) {
        this.rental_date = rental_date;
        this.inventory = inventory;
        this.customer_id = customer_id;
        this.return_date = return_date;
        this.staff = staff;
        this.last_update = last_update;
    }

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public Timestamp getRental_date() {
        return rental_date;
    }

    public void setRental_date(Timestamp rental_date) {
        this.rental_date = rental_date;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Timestamp getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Timestamp return_date) {
        this.return_date = return_date;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public RentalJson rentalToRentalJson(){
        RentalJson rj = new RentalJson();
        String customerString = String.valueOf(this.getCustomer_id());
        Href customer = new Href(customerString);
        rj.setCustomer(customer);
        String filmString = String.valueOf(this.getInventory().getFilm_id());
        Href film = new Href(filmString);
        rj.setFilm(film);
        String rentalDate = this.getRental_date().toString();
        rj.setRentalDate(rentalDate);
        int rentalId = this.getRental_id();
        rj.setRentalId(rentalId);
        String returnDate = this.getReturn_date().toString();
        rj.setRentalDate(returnDate);
        String storeString = String.valueOf(this.getInventory().getStore().getStore_id());
        Href store = new Href(storeString);
        rj.setStore(store);
        return rj;

    }


    @Override
    public String toString() {
        return "{" +
                "rental_id=" + rental_id +
                " rental_date=" + rental_date +
                " inventory=" + inventory +
                " customer_id=" + customer_id +
                " return_date=" + return_date +
                " staff=" + staff +
                " last_update=" + last_update +
                '}';
    }
}