package at.wiencampus.se.apigateway.controller;


import at.wiencampus.se.apigateway.service.CustomerService;
import at.wiencampus.se.common.dto.CustomerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
public class CustomerController extends IController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<CustomerData> customerLogin(@RequestParam String email, @RequestParam String password) {

        return new ResponseEntity<>(customerService.loginUser(email, password), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<CustomerData> customerRegister(@RequestBody CustomerData newCustomer) {

        CustomerData savedCustomer = customerService.registerCustomer(newCustomer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<CustomerData>> getAllCustomers() {
        List<CustomerData> customers = customerService.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
