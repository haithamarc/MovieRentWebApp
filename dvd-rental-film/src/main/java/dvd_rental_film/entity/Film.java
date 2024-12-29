package dvd_rental_film.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@NamedQuery(name = "film.getAll", query = "select tsd from Film tsd")
@NamedQuery(name = "film.getByFilmID", query = "select tsd from Film tsd where filmId = :film_id ")
@NamedQuery(name="film.countAll", query="SELECT COUNT(o) FROM Film o")
@NamedQuery(name = "film.delete", query = "delete from Film where filmId = :id")
@NamedQuery(name="film.Category", query="SELECT e FROM Category e , Film d where d.filmId = :id  ")
@NamedQuery(name = "film.updateByID", query = "update Film c set  c.rentalRate =:rntr, c.title = :t,c.description =:d,c.replacementCost =:rep WHERE c.filmId = :id")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film")
@Getter
@Setter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "film_film_id_seq")
    @Column(name = "film_id")
    private int filmId;
    @Column(name = "description")
    private String description;
    @Column(name = "release_year")
    private short releaseYear;
    @Column(name = "language_id")
    private int languageId;
    @Column(name = "rental_duration")
    private short rentalDuration;
    @Column(name = "rental_rate")
    private double rentalRate;//??
    @Column(name = "length")
    private short length ;
    @Column(name = "replacement_cost")
    private double replacementCost;
    @Column(name = "rating")
    private String rating ;
    @Column(name = "title")
    private String title;

    //@Column(insertable = false, updatable = false, nullable = false, columnDefinition = "timestamp without time zone default CURRENT_TIMESTAMP")
    // @Generated(GenerationTime.INSERT)
    // java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate; // TIMESTAMP(6) ?
    //another way , um Aktuell erzeug Zeit
   /* @PrePersist
    private void init(){ lastUpdate= Timestamp.from(Instant.now()); }
    */

    //cascade :um mit Tabelle FilmActor zu verbinden und automatische dort auch speichern
    @ManyToMany(fetch = FetchType.EAGER,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,CascadeType.REMOVE
    })
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_Id", referencedColumnName="film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_Id",referencedColumnName="actor_id")
    )
    private List<Actor> actorsm ;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,CascadeType.REMOVE
    })
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_Id", referencedColumnName="film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_Id",referencedColumnName="category_id")
    )
    private List<Category> categorm;
}
