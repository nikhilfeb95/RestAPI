package nikhil.spring.restapi.v1.mapper;

import nikhil.spring.restapi.domain.Vendor;
import nikhil.spring.restapi.v1.model.VendorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper  {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor venderDTOToVendor(VendorDTO vendorDTO);
}
