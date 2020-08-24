package nikhil.spring.restapi.controllers.v1;

import nikhil.spring.restapi.services.CustomerService;
import nikhil.spring.restapi.v1.model.CustomerDTO;
import nikhil.spring.restapi.v1.model.CustomerListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    private ResponseEntity<CustomerListDTO> getAllCustomers(){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    private ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id)
    {
        return new ResponseEntity<CustomerDTO>(
                customerService.findCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO)
    //Request body asks spring to look for customer DTO in the request body
    {
        return new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    private ResponseEntity<CustomerDTO> updateCustomer( @PathVariable Long id,
                                                        @RequestBody CustomerDTO customerDTO)
    //Request body asks spring to look for customer DTO in the request body
    {
        return new ResponseEntity<CustomerDTO>(
                customerService.saveCustomerByDTO(id,customerDTO), HttpStatus.OK);
    }
}
