package nikhil.spring.restapi.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import nikhil.spring.restapi.services.VendorService;
import nikhil.spring.restapi.v1.model.VendorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VendorControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    void testGetAllVendors() throws Exception{
        List<VendorDTO> vendorDTOS = Arrays.asList(new VendorDTO(), new VendorDTO());

        when(vendorService.getAllVendors()).thenReturn(vendorDTOS);

        mockMvc.perform(get("/api/v1/vendors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    void testGetVendor() throws Exception{
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get("/api/v1/vendors/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void testCreateVendor() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Oingo");

        when(vendorService.createVendor(any())).thenReturn(vendorDTO);

        mockMvc.perform(post("/api/v1/vendors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Oingo")));
    }

    @Test
    void testUpdateVendor() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Oingo");

        when(vendorService.saveVendorDTO(anyLong(),any())).thenReturn(vendorDTO);

        mockMvc.perform(put("/api/v1/vendors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Oingo")));
    }

    @Test
    void testPatchVendor() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Oingo");

        when(vendorService.patchVendor(anyLong(),any())).thenReturn(vendorDTO);

        mockMvc.perform(patch("/api/v1/vendors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Oingo")));
    }

    @Test
    void testDeleteVendor() throws Exception{
        mockMvc.perform(delete("/api/v1/vendors/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}