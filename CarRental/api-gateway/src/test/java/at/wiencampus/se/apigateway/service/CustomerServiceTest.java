//package at.wiencampus.se.apigateway.service;
//
//import at.wiencampus.se.common.dal.Customer;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.*;
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class CustomerServiceTest {
//    @Autowired
//    CustomerService service;
//
//    Customer newCustomer = new Customer(null, (long)111, 1, "firstName", "lastName", "address", "email@email.email10", "number", "password");
//
//    @Test
//    void addCustomerAndFindCustomerTest() {
//        service.addCustomer(newCustomer);
//        Customer c = service.loginUser("email@email.email10", "password");
//        assertNotNull(service.findCustomer(c.getCustomerId()));
//
//    }
//
//    @Test
//    void getCustomersTest() {
//        assertFalse(service.getCustomers().isEmpty());
//    }
//
//    @Test
//    void loginUser() {
//        assertNotNull(service.loginUser("email","password"));
//    }
//
//    @Test
//    void deleteCustomer() {
//        Customer c = service.loginUser("email@email.email10", "password");
//        service.deleteCustomer(c.getCustomerId());
//        boolean customerDeleted = true;
//        for (Customer cTemp :
//                service.getCustomers()) {
//            if (cTemp.getCustomerId() == c.getCustomerId()){
//                customerDeleted = false;
//                break;
//            }
//        }
//        assertTrue(customerDeleted);
//    }
//}
