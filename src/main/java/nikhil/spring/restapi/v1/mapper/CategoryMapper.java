package nikhil.spring.restapi.v1.mapper;

import nikhil.spring.restapi.domain.Category;
import nikhil.spring.restapi.v1.model.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
