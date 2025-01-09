package dvd_rental_film.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@NamedQuery(
        name = "FilmActor.getByActorId",
        query = "SELECT fa FROM FilmActor fa WHERE fa.actorId = :actorId"
)
@Entity
@Table(name = "film_actor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilmActor implements Serializable {

    @EmbeddedId
    private FilmActorId id;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    // Convenience fields for easier mapping
    @Column(name = "film_id", insertable = false, updatable = false)
    private int filmId;

    @Column(name = "actor_id", insertable = false, updatable = false)
    private int actorId;
}
