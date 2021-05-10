package at.wiencampus.se.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleServiceReply implements Serializable {
    private VehicleServiceName name;
    private CustomerRentalData customerRental;
    private Boolean isReturned;
    private VehicleData vehicle;
    private List<CustomerRentalData> customerRentals;
    private List<VehicleData> vehicles;
    private Exception exception;
}