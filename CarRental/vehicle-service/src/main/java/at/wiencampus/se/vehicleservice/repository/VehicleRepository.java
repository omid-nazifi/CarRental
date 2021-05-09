package at.wiencampus.se.vehicleservice.repository;

import at.wiencampus.se.common.dto.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
