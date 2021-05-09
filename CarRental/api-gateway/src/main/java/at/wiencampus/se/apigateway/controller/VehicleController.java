package at.wiencampus.se.apigateway.controller;


import at.wiencampus.se.apigateway.service.VehicleService;
import at.wiencampus.se.common.dto.CustomerRental;
import at.wiencampus.se.common.dto.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vehicle")
public class VehicleController extends IController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/rental")
    public ResponseEntity<List<CustomerRental>> getAllVehicleRentalForCustomer(long customerId) {
        return new ResponseEntity<>(vehicleService.getAllCustomerRentalForCustomer(customerId), HttpStatus.OK);
    }

    @PutMapping("/vehicleRent")
    public ResponseEntity<CustomerRental> rentAVehicle(@RequestBody CustomerRental customerRental) {
        vehicleService.createVehicleRental(customerRental);
        return new ResponseEntity<>(customerRental, HttpStatus.OK);
    }

    @GetMapping("/all/{currency}")
    public ResponseEntity<List<Vehicle>> getAllVehiclesWithCurrency(@PathVariable("currency") String currency) {
        return new ResponseEntity<>(vehicleService.getAllVehicleForCurrency(currency), HttpStatus.OK);
    }

    @PutMapping("/vehicleRent/return/{rentalId}")
    public boolean returnRentalCar(@PathVariable("rentalId") long rentalId) {
        return vehicleService.returnRentalCar(rentalId);
    }

    @PostMapping("/addVehicle")
    public ResponseEntity<Vehicle> returnRentalCar(@RequestBody Vehicle vehicle) {
        Vehicle saved = vehicleService.createNew(vehicle);
        return new ResponseEntity<Vehicle>(saved, HttpStatus.OK);
    }

}
