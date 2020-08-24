package nikhil.spring.restapi.controllers.v1;


import com.fasterxml.jackson.databind.ObjectMapper;
import nikhil.spring.restapi.services.CustomerService;
import nikhil.spring.restapi.v1.model.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testGetAllCustomers() throws Exception{
        List<CustomerDTO> customerDTOS = Arrays.asList(new CustomerDTO(), new CustomerDTO(), new CustomerDTO());

        when(customerService.getAllCustomers()).thenReturn(customerDTOS);

        mockMvc.perform(get("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.customerDTOList", hasSize(3)));
    }

    @Test
    void testGetCustomerById() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstName("Nikhil");

        when(customerService.findCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void testCreateNewCustomer() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Giorno");
        customerDTO.setLastName("Giovanna");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName("Giorno");
        returnDTO.setLastName("Giovanna");
        returnDTO.setCustomer_url("/api/v1/customers/1");

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnDTO);

        mockMvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")))
                .andExpect(jsonPath("$.firstName", equalTo("Giorno")));
    }

    @Test
    void testUpdateCustomer() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Giorno");
        customerDTO.setLastName("Giovanna");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName("Giorno");
        returnDTO.setLastName("Giovanna");
        returnDTO.setCustomer_url("/api/v1/customers/1");

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(put("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")))
                .andExpect(jsonPath("$.firstName", equalTo("Giorno")));
    }

    @Test
    void testPatchCustomer() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Giorno");
        customerDTO.setLastName("Giovanna");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName("Giorno");
        returnDTO.setLastName("Giovanna");
        returnDTO.setCustomer_url("/api/v1/customers/1");

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")))
                .andExpect(jsonPath("$.firstName", equalTo("Giorno")));
    }
}