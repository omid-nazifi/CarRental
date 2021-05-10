package at.wiencampus.se.carrental.controller;


import at.wiencampus.se.carrental.dal.CustomerRental;
import at.wiencampus.se.carrental.dal.Vehicle;
import at.wiencampus.se.carrental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/vehicle")
public class VehicleController extends IController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/rental/{customerId}")
    public ResponseEntity<List<CustomerRental>> getAllVehicleRentalForCustomer(@PathVariable("customerId") String customerId) {
        return new ResponseEntity<>(vehicleService.getAllCustomerRentalForCustomer(customerId), HttpStatus.OK);
    }

    @PutMapping("/vehicleRent")
    public ResponseEntity<CustomerRental> rentAVehicle(@RequestBody CustomerRental customerRental) {
        return new ResponseEntity<>(vehicleService.createVehicleRental(customerRental), HttpStatus.OK);
    }

    @PutMapping("/vehicleRent/return/{rentalId}")
    public boolean returnRentalCar(@PathVariable("rentalId") String rentalId) {
        return vehicleService.returnRentalCar(rentalId);
    }

    @PostMapping("/addVehicle")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        Vehicle saved = vehicleService.createNew(vehicle);
        return new ResponseEntity<Vehicle>(saved, HttpStatus.OK);
    }

}
