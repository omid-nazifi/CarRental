package at.wiencampus.se.apigateway.controller;


import at.wiencampus.se.apigateway.service.VehicleService;
import at.wiencampus.se.common.dto.CustomerRentalData;
import at.wiencampus.se.common.dto.VehicleData;
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
    public ResponseEntity<List<VehicleData>> getAllVehicles() {
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/rental")
    public ResponseEntity<List<CustomerRentalData>> getAllVehicleRentalForCustomer(long customerId) {
        return new ResponseEntity<>(vehicleService.getAllCustomerRentalForCustomer(customerId), HttpStatus.OK);
    }

    @PutMapping("/vehicleRent")
    public ResponseEntity<CustomerRentalData> rentAVehicle(@RequestBody CustomerRentalData customerRental) {
        vehicleService.createVehicleRental(customerRental);
        return new ResponseEntity<>(customerRental, HttpStatus.OK);
    }

    @GetMapping("/all/{currency}")
    public ResponseEntity<List<VehicleData>> getAllVehiclesWithCurrency(@PathVariable("currency") String currency) {
        return new ResponseEntity<>(vehicleService.getAllVehicleForCurrency(currency), HttpStatus.OK);
    }

    @PutMapping("/vehicleRent/return/{rentalId}")
    public boolean returnRentalCar(@PathVariable("rentalId") long rentalId) {
        return vehicleService.returnRentalCar(rentalId);
    }

    @PostMapping("/addVehicle")
    public ResponseEntity<VehicleData> returnRentalCar(@RequestBody VehicleData vehicle) {
        VehicleData saved = vehicleService.createNew(vehicle);
        return new ResponseEntity<VehicleData>(saved, HttpStatus.OK);
    }

}
