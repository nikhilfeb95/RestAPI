package nikhil.spring.restapi.controllers.v1;

import nikhil.spring.restapi.services.VendorService;
import nikhil.spring.restapi.v1.model.VendorDTO;
import nikhil.spring.restapi.v1.model.VendorListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors()
    {
        return new VendorListDTO(vendorService.getAllVendors());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO findVendorById(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }
}
