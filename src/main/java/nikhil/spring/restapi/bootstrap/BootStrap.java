package nikhil.spring.restapi.bootstrap;

import nikhil.spring.restapi.domain.Category;
import nikhil.spring.restapi.domain.Customer;
import nikhil.spring.restapi.domain.Vendor;
import nikhil.spring.restapi.repositories.CategoryRepository;
import nikhil.spring.restapi.repositories.CustomerRepository;
import nikhil.spring.restapi.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public BootStrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("Oingo");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("Boingo");
        vendorRepository.save(vendor2);
    }

    private void loadCustomers() {
        //given
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");
         customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");

        customerRepository.save(customer2);

        System.out.println("Customers Loaded: " + customerRepository.count());
    }

    private void loadCategories()
    {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
        System.out.println("Data Loaded = " + categoryRepository.count() );
    }
}
