package dvd_rental_film.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@NamedQuery(name = "actor.getAll", query = "select ac from Actor ac")
@NamedQuery(name = "actor.getByActorID", query = "select ac from Actor ac where actorId = :actor_id ")
@NamedQuery(name = "actor.updateByID", query = "update Actor c set c.firstName = :first_name,  c.lastName = :last_name, c.lastUpdate = :last_update WHERE c.actorId = :actor_id")
@NamedQuery(name = "actor.delete", query = "delete from Actor where actorId = :actor_id")
@NamedQuery(name = "actor.countAll", query = "select count(a) from Actor a")

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "actor")
@Getter
@Setter
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "actor_actor_id_seq")
    @Column(name = "actor_id")
    private int actorId;

    @Column(name = "last_update")
    private Timestamp lastUpdate;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(mappedBy="actorsm")
    private List<Film> films ;
}
