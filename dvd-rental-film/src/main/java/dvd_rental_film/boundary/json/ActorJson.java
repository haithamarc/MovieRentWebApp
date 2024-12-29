package dvd_rental_film.boundary.json;

import dvd_rental_film.boundary.Href;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ActorJson {
    public Href films;
    public String firstName;
    public int id;
    public String lastName;
}
