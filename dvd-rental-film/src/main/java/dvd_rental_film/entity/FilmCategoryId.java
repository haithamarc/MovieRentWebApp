package dvd_rental_film.entity;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
// für  fall Entity kombonierte mehr id    enthält
public class FilmCategoryId implements Serializable {

    private int categoryId  ;

    private int filmId;
}
