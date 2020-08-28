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

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.venderDTOToVendor(vendorDTO);
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        return returnDTO;
    }

    private VendorDTO saveAndReturnVendorDTO(Vendor vendor){
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnVendor = vendorMapper.vendorToVendorDTO(savedVendor);
        returnVendor.setVendorUrl("/api/v1/vendors/" + savedVendor.getId());

        return returnVendor;
    }

    @Override
    public VendorDTO saveVendorDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.venderDTOToVendor(vendorDTO);
        vendor.setId(id);
        return saveAndReturnVendorDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {

        return vendorRepository.findById(id)
                .map(vendor -> {
                    if(vendor.getName()!=null){
                        vendor.setName(vendorDTO.getName());
                    }

                    VendorDTO returnVendor = vendorMapper.vendorToVendorDTO(vendor);
                    returnVendor.setVendorUrl("/api/v1/vendors/" + id);

                    return returnVendor;
                }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }


}
