package dvd_rental_film.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;


@NamedQuery(name = "language.getAll", query = "select tsd from Language tsd")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "language_language_id_seq")
    @Column(name = "language_id")
    private int languageId;
    @Column(name = "name")
    private  String name ;

    //@Column(insertable = false, updatable = false, nullable = false, columnDefinition = "timestamp without time zone default CURRENT_TIMESTAMP")
    // @Generated(GenerationTime.INSERT)
    // java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate; // TIMESTAMP(6) ?
}
