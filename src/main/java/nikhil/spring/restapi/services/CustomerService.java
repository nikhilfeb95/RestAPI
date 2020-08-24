package nikhil.spring.restapi.services;


import nikhil.spring.restapi.v1.model.CustomerDTO;
import nikhil.spring.restapi.v1.model.CustomerListDTO;

import java.util.List;

public interface CustomerService {
     List<CustomerDTO> getAllCustomers();

     CustomerDTO findCustomerById(Long id);
}
