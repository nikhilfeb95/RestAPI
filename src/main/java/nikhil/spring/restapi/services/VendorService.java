package nikhil.spring.restapi.services;

import nikhil.spring.restapi.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {
    List<VendorDTO> getAllVendors();

    VendorDTO getVendorById(Long id);
}
