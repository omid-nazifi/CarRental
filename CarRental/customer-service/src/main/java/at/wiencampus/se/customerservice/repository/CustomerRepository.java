package at.wiencampus.se.customerservice.repository;

import at.wiencampus.se.customerservice.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;

@EnableMongoRepositories
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findCustomerByCustomerId(String customerId);

    Optional<Customer> findCustomerByEmail(String email);

    Customer findByEmailAndPassword(String email, String password);
}
