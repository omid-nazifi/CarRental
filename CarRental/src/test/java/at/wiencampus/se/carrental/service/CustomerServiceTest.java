package at.wiencampus.se.carrental.service;

import at.wiencampus.se.carrental.dal.Customer;
import at.wiencampus.se.carrental.repository.CustomerRentalRepository;
import at.wiencampus.se.carrental.repository.CustomerRepository;
import at.wiencampus.se.carrental.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceTest {
    @Autowired
    CustomerService service;

    Customer newCustomer = new Customer((long)111, 1, "firstName", "lastName", "address", "email@email.com", "number", "password");

    @Test
    void registerCustomersTest() {
        service.addCustomer(newCustomer);
        assertNotNull(service.findCustomer(newCustomer.getCustomerId()));
    }

    @Test
    void loginCustomerTest() {
        Customer c = service.loginUser("email@email.com", "password");
        assertNotNull(service.findCustomer(c.getCustomerId()));
    }

    @Test
    void getAllCustomers() {
        assertNotNull(service.getCustomers());
    }

    @Test
    void deleteCustomer() {
        Customer c = service.loginUser("email@email.com", "password");
        service.deleteCustomer(c.getCustomerId());
        boolean customerDeleted = true;
        for (Customer cTemp :
                service.getCustomers()) {
            if (cTemp.getCustomerId() == c.getCustomerId()){
                customerDeleted = false;
                break;
            }
        }
        assertTrue(customerDeleted);
    }
}