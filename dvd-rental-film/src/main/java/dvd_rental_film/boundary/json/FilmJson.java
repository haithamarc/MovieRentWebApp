package dvd_rental_film.boundary.json;

import dvd_rental_film.boundary.Href;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
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
