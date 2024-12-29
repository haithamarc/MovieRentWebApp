package dvd_rental_film.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@NamedQuery(name = "film_actor.getByActorID", query = "select ac from FilmActor ac where actorId = :actor_id ")
@Entity
@Table(name = "film_actor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilmActor  implements Serializable {
    @Id
    @Column(name = "film_id")
    private int filmId;
    @Id
    @Column(name = "actor_id")
    private int actorId;
    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;

}
