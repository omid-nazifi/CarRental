package at.wiencampus.se.carrental.dal;

import javax.persistence.*;
import java.util.Set;

@Table(name = "customer",uniqueConstraints = @UniqueConstraint(name = "customer_email_unique",columnNames = "email"))
@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long customerId;
    private Long personalId;
    private int countryId;
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;
    @Column(name = "email", nullable = false)
    private String email;
    private String number;
    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @OneToMany(mappedBy = "customer")
    Set<CustomerRental> reservations;

    public Customer() {

    }

    public Customer(Long id, Long personalId, int countryId, String firstName, String lastName, String address, String email, String number) {
        this.customerId = id;
        this.personalId = personalId;
        this.countryId = countryId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.number = number;
    }

    public Customer(Long customerId, Long personalId, int countryId, String firstName, String lastName, String address, String email, String number, String password) {
        this.customerId = customerId;
        this.personalId = personalId;
        this.countryId = countryId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.number = number;
        this.password = password;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long id) {
        this.customerId = id;
    }

    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + customerId +
                ", personalId=" + personalId +
                ", countryId=" + countryId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
