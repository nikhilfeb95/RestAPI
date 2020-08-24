package nikhil.spring.restapi.repositories;

import nikhil.spring.restapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
