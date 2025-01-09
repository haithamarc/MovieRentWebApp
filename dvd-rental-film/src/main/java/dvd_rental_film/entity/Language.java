package dvd_rental_film.entity;




import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Language {

    @Id
    private int id;

    private String name;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}