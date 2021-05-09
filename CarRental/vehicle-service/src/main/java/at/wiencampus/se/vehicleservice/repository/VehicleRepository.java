package at.wiencampus.se.vehicleservice.repository;

import at.wiencampus.se.vehicleservice.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
}
