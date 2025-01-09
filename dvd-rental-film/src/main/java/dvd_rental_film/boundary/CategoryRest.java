package dvd_rental_film.boundary;

import dvd_rental_film.control.CategoryService;
import dvd_rental_film.entity.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryRest {

    private final CategoryService categoryService;

    public CategoryRest(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(produces = "application/json")
    public List<String> getAll() {
        return categoryService.getAll().stream().map(Category::getName).collect(Collectors.toList());
    }
}