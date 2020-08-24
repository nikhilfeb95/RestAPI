package nikhil.spring.restapi.services;


import nikhil.spring.restapi.domain.Customer;
import nikhil.spring.restapi.v1.model.CustomerDTO;


import java.util.List;

public interface CustomerService {
     List<CustomerDTO> getAllCustomers();

     CustomerDTO findCustomerById(Long id);

     CustomerDTO createNewCustomer(CustomerDTO customerDTO);
}
