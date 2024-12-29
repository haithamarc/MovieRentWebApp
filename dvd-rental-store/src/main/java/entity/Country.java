package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "country")
public class Country{

    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int country_id;


    @Column(name = "country")
    private String country;

    @Column(name = "last_update")
    private Timestamp last_update;

    public Country() {
    }

    public Country(String country, Timestamp last_update) {
        this.country = country;
        this.last_update = last_update;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
                "country_id=" + country_id +
                " country='" + country +
                " last_update=" + last_update +
                '}';
    }
}