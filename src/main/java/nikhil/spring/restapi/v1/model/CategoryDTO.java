package nikhil.spring.restapi.v1.model;

//class which is exposed to the api --> like commands --> decouple domain

import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
}
