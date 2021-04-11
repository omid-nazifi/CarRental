package at.wiencampus.se.carrental.service;

import at.wiencampus.se.carrental.dal.Customer;
import at.wiencampus.se.carrental.dal.CustomerRental;
import at.wiencampus.se.carrental.dal.Vehicle;
import at.wiencampus.se.carrental.repository.CustomerRentalRepository;
import at.wiencampus.se.carrental.repository.CustomerRepository;
import at.wiencampus.se.carrental.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CustomerRentalRepository customerRentalRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public CustomerRental createVehicleRental(CustomerRental newCustomerRental){
        customerRentalRepository.save(newCustomerRental);
        return newCustomerRental;
    }

    public List<CustomerRental> getAllCustomerRentalForCustomer(long customerId){
        Optional<Customer> customer = customerRepository.findCustomerByCustomerId(customerId);
        return customerRentalRepository.getCustomerRentalByCustomer(customer.get());
    }

}
