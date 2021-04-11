package at.wiencampus.se.carrental.service;

import at.wiencampus.se.carrental.dal.CustomerRental;
import at.wiencampus.se.carrental.dal.Vehicle;
import at.wiencampus.se.carrental.repository.CustomerRentalRepository;
import at.wiencampus.se.carrental.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CustomerRentalRepository customerRentalRepository;

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public CustomerRental createVehicleRental(CustomerRental newCustomerRental){
        customerRentalRepository.save(newCustomerRental);
        return newCustomerRental;
    }

    public List<CustomerRental> getAllCustomerRentalForCustomer(long customerId){
        return customerRentalRepository.getCustomerRentalByCustoemrId(customerId);
    }

}
