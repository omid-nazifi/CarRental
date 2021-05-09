package at.wiencampus.se.vehicleservice.repository;

import at.wiencampus.se.vehicleservice.model.Customer;
import at.wiencampus.se.vehicleservice.model.CustomerRental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRentalRepository extends JpaRepository<CustomerRental,Long> {
    List<CustomerRental> getCustomerRentalByCustomer(Customer customer);
}
