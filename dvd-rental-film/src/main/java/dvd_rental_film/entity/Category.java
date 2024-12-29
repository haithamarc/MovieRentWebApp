package dvd_rental_film.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@NamedQuery(name = "cat.getAll", query = "select c from Category c")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "category_id")
    private int categoryId;
    @Column(name = "name")
    private String  name ;
    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate; // TIMESTAMP(6) ?

    @ManyToMany(mappedBy="categorm")
    private List<Film> films ;

}
