package at.wiencampus.se.carrental.controller;


import at.wiencampus.se.carrental.dal.CustomerRental;
import at.wiencampus.se.carrental.dal.Vehicle;
import at.wiencampus.se.carrental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class CarController extends IController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("/allVehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    public ResponseEntity<List<CustomerRental>> getAllVehicleRentalForCustomer(long customerId){
        return new ResponseEntity<>(vehicleService.getAllCustomerRentalForCustomer(customerId), HttpStatus.OK);
    }

    @PutMapping("/vehicleRent")
    public ResponseEntity<CustomerRental> rentAVehicle(CustomerRental customerRental){
        vehicleService.createVehicleRental(customerRental);
        return new ResponseEntity<>(customerRental, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Vehicle>> getAllCustomers() {
        Vehicle vehicle = new Vehicle(123L, 123, "VehicleName", "g213s", "Type1", 4, new Date(), true, 12345, 1234, 1234, 5000000, "");
        List<Vehicle> vehicleList = Collections.singletonList(vehicle);
        return new ResponseEntity<>(vehicleList, HttpStatus.OK);
    }
}
