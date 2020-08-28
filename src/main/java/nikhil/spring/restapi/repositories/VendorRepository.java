package nikhil.spring.restapi.repositories;

import nikhil.spring.restapi.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
