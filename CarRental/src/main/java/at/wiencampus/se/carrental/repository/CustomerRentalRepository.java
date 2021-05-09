package at.wiencampus.se.carrental.repository;

import at.wiencampus.se.carrental.dal.Customer;
import at.wiencampus.se.carrental.dal.CustomerRental;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
@EnableMongoRepositories
public interface CustomerRentalRepository extends MongoRepository<CustomerRental, String> {
    List<CustomerRental> getCustomerRentalByCustomer(Customer customer);
}
