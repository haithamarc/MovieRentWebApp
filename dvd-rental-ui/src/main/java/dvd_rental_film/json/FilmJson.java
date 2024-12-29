package dvd_rental_film.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmJson {
    public Href actors;
    public List<String> categories;
    public String description;
    public Integer id;
    public String language;
    public Short length;
    public String rating;
    public Short releaseYear;
    public Short rentalDuration;
    public Double rentalRate;
    public Double replacementCost;
    public String title;
}
