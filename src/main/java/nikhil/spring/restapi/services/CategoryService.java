package nikhil.spring.restapi.services;

import nikhil.spring.restapi.domain.Category;
import nikhil.spring.restapi.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
