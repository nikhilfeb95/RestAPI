package nikhil.spring.restapi.services;

import nikhil.spring.restapi.domain.Customer;
import nikhil.spring.restapi.repositories.CustomerRepository;
import nikhil.spring.restapi.v1.mapper.CustomerMapper;
import nikhil.spring.restapi.v1.model.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;
    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void getAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS= customerService.getAllCustomers();

        assertEquals(customers.size(), customerDTOS.size());
    }

    @Test
    void findCustomerById() {
        Customer customer = new Customer();
        customer.setFirstName("Jojo");
        customer.setId(1L);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.findCustomerById(1L);

        assertEquals(customer.getId(), customerDTO.getId());
    }
}