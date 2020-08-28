package nikhil.spring.restapi.controllers.v1;

import nikhil.spring.restapi.services.CategoryService;
import nikhil.spring.restapi.v1.model.CategoryDTO;
import nikhil.spring.restapi.v1.model.CategoryListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories(){
        return new CategoryListDTO(categoryService.getAllCategories());
    }


    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }
}
