package at.wiencampus.se.common.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRentalData implements Serializable {

    private String id;
    private CustomerData customerData;
    private VehicleData vehicleData;
    private Date reservationDate;
    private Date pickUpDate;
    private Date dropOffDate;
    private boolean isDamaged;
    private float totalCost;

    @Override
    public String toString() {
        return "CustomerRental{" +
                "id=" + id +
                ", customer=" + customerData.getCustomerId() +
                ", vehicle=" + vehicleData.getVehicleId() +
                ", reservationDate=" + reservationDate +
                ", pickUpDate=" + pickUpDate +
                ", dropOffDate=" + dropOffDate +
                ", isDamaged=" + isDamaged +
                ", totalCost=" + totalCost +
                '}';
    }
}
