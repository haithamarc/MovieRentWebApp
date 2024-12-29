package entity.json;

import javax.validation.constraints.NotNull;

public class RentalJson {
    public Href customer;
    public Href film;
    @NotNull
    public String rentalDate;
    public int rentalId;
    public String returnDate;

    public Href store;

    public RentalJson() {
    }


    public RentalJson(Href customer, Href film, String rentalDate, int rentalId, String returnDate, Href store) {
        this.customer = customer;
        this.film = film;
        this.rentalDate = rentalDate;
        this.rentalId = rentalId;
        this.returnDate = returnDate;
        this.store = store;
    }



    public Href getCustomer() {
        return customer;
    }

    public void setCustomer(Href customer) {
        this.customer = customer;
    }

    public Href getFilm() {
        return film;
    }

    public void setFilm(Href film) {
        this.film = film;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Href getStore() {
        return store;
    }

    public void setStore(Href store) {
        this.store = store;
    }
}
