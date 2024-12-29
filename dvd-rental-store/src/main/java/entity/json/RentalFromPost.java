package entity.json;

public class RentalFromPost {
    private int inventory;
    private int customer;
    private int staff;
    private String date;

    public RentalFromPost() {
    }

    public RentalFromPost(int inventory, int customer, int staff, String date) {
        this.inventory = inventory;
        this.customer = customer;
        this.staff = staff;
        this.date = date;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
