package at.wiencampus.se.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleServiceRequest implements Serializable {
    private VehicleServiceName name;
    private CustomerRentalData customerRental;
    private String rentalId;
    private VehicleData vehicle;
    private Long CustomerId;
    private String currency;
}