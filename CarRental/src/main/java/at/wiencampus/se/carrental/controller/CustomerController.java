package at.wiencampus.se.carrental.controller;


import at.wiencampus.se.carrental.dal.Customer;
import at.wiencampus.se.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController extends IController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
//        List<Customer> customerList = customerService.getCustomers();
        Customer customer = new Customer(123L, 123L, 123, "Max", "Muster", "Addres Wien", "max.muster@test.at", "06881234567");
        List<Customer> customerList = Collections.singletonList(customer);
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerNewCustomer(@RequestBody Customer customer) {
        if (customer == null) {
            return new ResponseEntity<String>("Customer object is null", HttpStatus.NOT_ACCEPTABLE);
        }
        customer.setCustomerId(123L);
        return new ResponseEntity(customer, HttpStatus.OK);
    }

    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody Customer customer) {
        if (customer == null) {
            return new ResponseEntity<String>("Customer object is null", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity("you login successfully!", HttpStatus.OK);
    }
}
