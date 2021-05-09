package at.wiencampus.se.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    private Long customerId;
    private Long personalId;
    private int countryId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String number;
    private String password;

    Set<CustomerRental> reservations;

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
