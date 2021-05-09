//package at.wiencampus.se.apigateway.service;
//
//import at.wiencampus.se.customerservice.model.CustomerRental;
//import at.wiencampus.se.customerservice.model.Vehicle;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.List;
//
//@Service
//public class VehicleService {
//
//    public List<Vehicle> getAllVehicles() {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setCost(1000);
//        vehicle.setVehicleId(321L);
//        vehicle.setVehicleName("Q7 50 TDI");
//        vehicle.setManufacturer("Audi");
//        vehicle.setVehicleId(321L);
//        return Collections.singletonList(vehicle);
//    }
//
//    public CustomerRental createVehicleRental(CustomerRental newCustomerRental) {
//        return newCustomerRental;
//    }
//
//    public List<CustomerRental> getAllCustomerRentalForCustomer(long customerId) {
//        return Collections.emptyList();
//    }
//
//    public boolean returnRentalCar(long rentalId) {
//        return true;
//    }
//
//    public List<Vehicle> getAllVehicleForCurrency(String currency) {
//        return Collections.emptyList();
//    }
//
//    public Vehicle createNew(Vehicle vehicle) {
//        vehicle.setVehicleId(123L);
//        return vehicle;
//    }
//
//}
