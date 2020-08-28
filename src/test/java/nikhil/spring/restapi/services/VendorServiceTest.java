package nikhil.spring.restapi.services;

import nikhil.spring.restapi.domain.Vendor;
import nikhil.spring.restapi.repositories.VendorRepository;
import nikhil.spring.restapi.v1.mapper.VendorMapper;
import nikhil.spring.restapi.v1.model.VendorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VendorServiceTest {

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    void setUp() {
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void getAllVendors() {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> returnVendors = vendorService.getAllVendors();

        assertEquals(2, returnVendors.size());
    }

    @Test
    void getVendorById() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO returnVendor = vendorService.getVendorById(1L);

        assertEquals(returnVendor.getId(), vendor.getId());
    }

    @Test
    void createVendor() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);

        VendorDTO createdVendor = new VendorDTO();
        createdVendor.setId(1L);

        when(vendorRepository.save(any())).thenReturn(vendor);

        VendorDTO returnVendor = vendorService.createVendor(createdVendor);

        assertEquals(returnVendor.getId(), vendor.getId());
    }

    @Test
    void saveVendor() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);

        VendorDTO createdVendor = new VendorDTO();
        createdVendor.setId(1L);

        when(vendorRepository.save(any())).thenReturn(vendor);

        VendorDTO returnVendor = vendorService.saveVendorDTO(1L, createdVendor);

        assertEquals(returnVendor.getId(), vendor.getId());
    }

    @Test
    void patchVendor() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("test");

        VendorDTO createdVendor = new VendorDTO();
        createdVendor.setId(1L);
        createdVendor.setName("test");

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO returnVendor = vendorService.patchVendor(1L, createdVendor);

        assertEquals(returnVendor.getId(), vendor.getId());
    }

}