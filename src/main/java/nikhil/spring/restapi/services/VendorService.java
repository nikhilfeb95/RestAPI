package nikhil.spring.restapi.services;

import nikhil.spring.restapi.domain.Vendor;
import nikhil.spring.restapi.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {
    List<VendorDTO> getAllVendors();

    VendorDTO getVendorById(Long id);

    VendorDTO createVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorDTO(Long id,VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id,VendorDTO vendorDTO);

    void deleteVendor(Long id);
}