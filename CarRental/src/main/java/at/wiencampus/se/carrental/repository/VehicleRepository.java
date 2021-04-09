package at.wiencampus.se.carrental.repository;

import at.wiencampus.se.carrental.dal.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
