package at.wiencampus.se.carrental.repository;

import at.wiencampus.se.carrental.dal.CustomerRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRentalRepository extends JpaRepository<CustomerRental,Long> {
}
