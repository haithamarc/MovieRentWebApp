package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "city")
public class City{

    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int city_id;

    @Column(name = "city")
    private String city;


    @ManyToOne(optional = false)
    @JoinColumn(name ="country_id")
    private Country country;

    @Column(name = "last_update")
    private Timestamp last_update;

    public City() {
    }

    public City(String city, Country country, Timestamp last_update) {
        this.city = city;
        this.country = country;
        this.last_update = last_update;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
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
                "city_id=" + city_id +
                " city='" + city +
                " country=" + country +
                " last_update=" + last_update +
                '}';
    }
}