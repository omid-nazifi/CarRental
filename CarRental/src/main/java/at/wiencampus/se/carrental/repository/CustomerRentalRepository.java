package at.wiencampus.se.carrental.repository;

import at.wiencampus.se.carrental.dal.CustomerRental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRentalRepository extends JpaRepository<CustomerRental,Long> {
    List<CustomerRental> getCustomerRentalByCustoemrId(long customerId);
}
