package entity;

import entity.json.Href;
import entity.json.InventoryJson;

import javax.persistence.*;
import java.sql.Timestamp;
@NamedQuery(name= "getInventoryByFilmId", query ="SELECT i from Inventory i where i.film_id = :film_id")
@NamedQuery(name= "getInventoryById", query ="SELECT i from Inventory i where i.inventory_id = :inventory_id")
@Entity
@Table(name = "inventory")
public class Inventory{

    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventory_id;


    @Column(name = "film_id")
    private int film_id;

    @ManyToOne(optional = false)
    @JoinColumn(name ="store_id")
    private Store store;


    @Column(name = "last_update")
    private Timestamp last_update;

    public Inventory() {
    }

    public Inventory(int film_id, Store store, Timestamp last_update) {
        this.film_id = film_id;
        this.store = store;
        this.last_update = last_update;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public InventoryJson inventoryToInventoryJson() {
        InventoryJson ij = new InventoryJson();
        ij.setId(this.getInventory_id());
        String filmString = String.valueOf(this.getFilm_id());
        Href film = new Href(filmString);
        ij.setFilm(film);
        String storeString = String.valueOf(this.getStore().getStore_id());
        Href store = new Href(storeString);
        ij.setStore(store);
        return ij;
    }

    @Override
    public String toString() {
        return "{" +
                " inventory_id=" + inventory_id +
                " film_id=" + film_id +
                " store=" + store +
                " last_update=" + last_update +
                "}";
    }


}