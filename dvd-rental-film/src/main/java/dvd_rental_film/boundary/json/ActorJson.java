package dvd_rental_film.boundary.json;

import dvd_rental_film.boundary.Href;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActorJson {
    public Href films;
    public String firstName;
    public int id;
    public String lastName;
}
