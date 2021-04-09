package at.wiencampus.se.carrental.controller;


import at.wiencampus.se.carrental.dal.Customer;
import at.wiencampus.se.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers()
    {
        List<Customer> customerList=customerService.getCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }
}
