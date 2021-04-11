package at.wiencampus.se.carrental.service;

import at.wiencampus.se.carrental.dal.Customer;
import at.wiencampus.se.carrental.exception.CustomerNotFoundException;
import at.wiencampus.se.carrental.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer){
        return customerRepository
                .save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long customerId){
        customerRepository
                .deleteById(customerId);
    }

    public Customer findCustomer(Long customerId){
       return customerRepository
               .findCustomerByCustomerId(customerId)
               .orElseThrow(() -> new CustomerNotFoundException("Customer by id "+  customerId + "was not found"));
    }

    public Customer loginUser(String email, String password){
        return customerRepository.findCustomerByEmailAndPassword(email, password);
    }


}
