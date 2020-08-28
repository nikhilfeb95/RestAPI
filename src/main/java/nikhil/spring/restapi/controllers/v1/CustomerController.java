package nikhil.spring.restapi.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nikhil.spring.restapi.services.CustomerService;
import nikhil.spring.restapi.v1.model.CustomerDTO;
import nikhil.spring.restapi.v1.model.CustomerListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api("Get/add or delete customers")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private CustomerListDTO getAllCustomers(){
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @ApiOperation(value = "This gets a list of customers", notes = "Returns List<CustomerDTO> objects")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    private CustomerDTO getCustomerById(@PathVariable Long id)
    {
        return customerService.findCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO)
    //Request body asks spring to look for customer DTO in the request body
    {
        return customerService.createNewCustomer(customerDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    private CustomerDTO updateCustomer( @PathVariable Long id,
                                                        @RequestBody CustomerDTO customerDTO)
    //Request body asks spring to look for customer DTO in the request body
    {
        return customerService.saveCustomerByDTO(id,customerDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    private CustomerDTO patchCustomer( @PathVariable Long id,
                                                        @RequestBody CustomerDTO customerDTO)
    {
        return customerService.saveCustomerByDTO(id,customerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    private void deleteCustomer( @PathVariable Long id)
    {
        customerService.deleteCustomerById(id);
    }
}
