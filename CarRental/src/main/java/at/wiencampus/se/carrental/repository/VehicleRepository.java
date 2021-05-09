package at.wiencampus.se.carrental.repository;

import at.wiencampus.se.carrental.dal.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
}
