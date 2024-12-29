package dvd_rental_film.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@IdClass(FilmCategoryId.class)
@Table(name = "film_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilmCategory  implements Serializable {
    @Id
    @Column(name = "category_id")
    private int categoryId  ;
    @Id
    @Column(name = "film_id")
    private int filmId;
    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;

}
