package nikhil.spring.restapi.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class VendorDTO {
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("vendor_url")
    private String vendorUrl;
}
