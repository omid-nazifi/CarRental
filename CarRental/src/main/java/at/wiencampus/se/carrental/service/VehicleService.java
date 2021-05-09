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

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public CustomerRental createVehicleRental(CustomerRental newCustomerRental) {
        customerRentalRepository.save(newCustomerRental);
        return newCustomerRental;
    }

    public List<CustomerRental> getAllCustomerRentalForCustomer(String customerId) {
        Optional<Customer> customer = customerRepository.findCustomerByCustomerId(customerId);
        return customerRentalRepository.getCustomerRentalByCustomer(customer.get());
    }

    public boolean returnRentalCar(String rentalId) {
        Optional<CustomerRental> rental = customerRentalRepository.findById(rentalId);
        if (rental.isPresent())
            return false;
        customerRentalRepository.deleteById(rentalId);
        return true;
    }

  /*  public List<Vehicle> getAllVehicleForCurrency(String currency) {
        List<Vehicle> allVehicle = vehicleRepository.findAll();
        CurrencyService converter = new CurrencyService();
        for (Vehicle vehicle :
                allVehicle) {
            double convertedPrice = converter.getCurrency(vehicle.getCost(), CurrencyEnum.fromValue(currency)).getPrice();
            vehicle.setCost((float) convertedPrice);
        }
        return allVehicle;
    }*/

    public Vehicle createNew(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

}
