package dvd_rental_film.boundary;

import java.util.ArrayList;
import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resources")
public class RestConfig {

    @GetMapping
    public List<String> getResources() {
        List<String> resources = new ArrayList<>();
        resources.add("/films");
        resources.add("/categories");
        resources.add("/actors");
        return resources;
    }
}