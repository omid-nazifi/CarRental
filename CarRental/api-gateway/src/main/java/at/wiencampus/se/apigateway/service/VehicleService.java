package at.wiencampus.se.apigateway.service;

import at.wiencampus.se.common.dto.CustomerRentalData;
import at.wiencampus.se.common.dto.VehicleData;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class VehicleService {

    public List<VehicleData> getAllVehicles() {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setCost(1000);
//        vehicle.setVehicleId(321L);
//        vehicle.setVehicleName("Q7 50 TDI");
//        vehicle.setManufacturer("Audi");
//        vehicle.setVehicleId(321L);
        return null; //Collections.singletonList(vehicle);
    }

    public CustomerRentalData createVehicleRental(CustomerRentalData newCustomerRental) {
        return null; //newCustomerRental;
    }

    public List<CustomerRentalData> getAllCustomerRentalForCustomer(long customerId) {
        return Collections.emptyList();
    }

    public boolean returnRentalCar(long rentalId) {
        return true;
    }

    public List<VehicleData> getAllVehicleForCurrency(String currency) {
        return Collections.emptyList();
    }

    public VehicleData createNew(VehicleData vehicle) {
//        vehicle.setVehicleId(123L);
        return null; //vehicle;
    }

}
