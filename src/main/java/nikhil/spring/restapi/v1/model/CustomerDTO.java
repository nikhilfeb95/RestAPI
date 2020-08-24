package nikhil.spring.restapi.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;

    private String firstName;
    private String lastName;
    private String customer_url;
}
