package entity.json;


public class InventoryJson {
    private int id;
    private Href film;
    private Href store;

    public InventoryJson() {
    }

    public InventoryJson(int id, Href film, Href store) {
        this.id = id;
        this.film = film;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Href getFilm() {
        return film;
    }

    public void setFilm(Href film) {
        this.film = film;
    }

    public Href getStore() {
        return store;
    }

    public void setStore(Href store) {
        this.store = store;
    }


}
