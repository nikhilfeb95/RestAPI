package nikhil.spring.restapi.services;

import lombok.extern.slf4j.Slf4j;
import nikhil.spring.restapi.domain.Category;
import nikhil.spring.restapi.repositories.CategoryRepository;
import nikhil.spring.restapi.v1.mapper.CategoryMapper;
import nikhil.spring.restapi.v1.model.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO>categoryDTOS = new ArrayList();

        categoryRepository.findAll()
                .stream()
                .forEach(category -> {
                    categoryDTOS.add(categoryMapper.categoryToCategoryDTO(category));
                });
        if(categoryDTOS.size() == 0)
            log.error("no categories in the db");
            //write block for graceful error message
        return categoryDTOS;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        if(category == null)
        {
            log.error("no category with " + name + " exists");
            //make it better
        }
        return categoryMapper.categoryToCategoryDTO(category);
    }
}
