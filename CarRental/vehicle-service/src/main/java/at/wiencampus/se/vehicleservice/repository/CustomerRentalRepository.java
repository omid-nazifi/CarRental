package at.wiencampus.se.vehicleservice.repository;

import at.wiencampus.se.vehicleservice.model.Customer;
import at.wiencampus.se.vehicleservice.model.CustomerRental;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories
public interface CustomerRentalRepository extends MongoRepository<CustomerRental, String> {
    List<CustomerRental> getCustomerRentalByCustomer(Customer customer);
}
