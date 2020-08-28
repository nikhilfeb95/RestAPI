package nikhil.spring.restapi.services;

import nikhil.spring.restapi.domain.Vendor;
import nikhil.spring.restapi.repositories.VendorRepository;
import nikhil.spring.restapi.v1.mapper.VendorMapper;
import nikhil.spring.restapi.v1.model.VendorDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl("api/v1/vendors" + vendorDTO.getId());
                    return vendorDTO;
                }).collect(Collectors.toList());
    }


    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl("api/v1/vendors" + vendorDTO.getId());
                    return vendorDTO;
                })
                .orElseThrow(RuntimeException::new);
    }
}
