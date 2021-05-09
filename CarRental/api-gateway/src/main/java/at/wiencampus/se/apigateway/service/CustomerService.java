//package at.wiencampus.se.apigateway.service;
//
//import at.wiencampus.se.customerservice.model.Customer;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.List;
//
//@Service
//public class CustomerService {
//
//    public Customer addCustomer(Customer customer) {
//        customer.setCustomerId(123L);
//        return customer;
//    }
//
//    public List<Customer> getCustomers() {
//        Customer saved = new Customer();
//        saved.setFirstName("Max");
//        saved.setLastName("Muster");
//        saved.setEmail("max.muster@campuswien.at");
//        return Collections.singletonList(saved);
//    }
//
//    public void deleteCustomer(Long customerId) {
//
//    }
//
//    public Customer findCustomer(Long customerId) {
//        Customer found = new Customer();
//        found.setFirstName("Max");
//        found.setLastName("Muster");
//        found.setEmail("max.muster@campuswien.at");
//        return found;
//    }
//
//    public Customer loginUser(String email, String password) {
//        Customer saved = new Customer();
//        saved.setFirstName("Max");
//        saved.setLastName("Muster");
//        saved.setEmail(email);
//        return saved;
//    }
//
//
//}
