package at.wiencampus.se.customerservice.repository;

import at.wiencampus.se.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByCustomerId(Long customerId);

    Optional<Customer> findCustomerByEmail(String email);

    Customer findByEmailAndPassword(String email, String password);
}
