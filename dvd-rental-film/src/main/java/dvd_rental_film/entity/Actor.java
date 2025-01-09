package dvd_rental_film.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


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

    @ManyToMany(mappedBy="actors")
    private List<Film> films ;
}
