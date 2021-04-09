package at.wiencampus.se.carrental.repository;

import at.wiencampus.se.carrental.dal.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findCustomerByCustomerId(Long customerId);
}
