package at.wiencampus.se.carrental.controller;


import at.wiencampus.se.carrental.dal.Customer;
import at.wiencampus.se.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController extends IController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<Customer> customerLogin(@RequestParam String email, @RequestParam String password) {

        return new ResponseEntity<>(customerService.loginUser(email, password), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Customer> customerRegister(@RequestBody Customer newCustomer) {

        Customer savedCustomer = customerService.addCustomer(newCustomer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customerList = customerService.getCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }
}
