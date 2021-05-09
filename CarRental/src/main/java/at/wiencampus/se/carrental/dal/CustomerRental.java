package at.wiencampus.se.carrental.dal;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import java.util.Date;
import java.util.UUID;

@Document("customerRental")
public class CustomerRental {

    @Id
    private String id;

    private String customerId;
    private String vehicleId;

    private Date reservationDate;

    private Date pickUpDate;

    private Date dropOffDate;

    private boolean isDamaged;

    private float totalCost;

    public CustomerRental() {
        this.id= UUID.randomUUID().toString();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public void setDamaged(boolean damaged) {
        isDamaged = damaged;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "CustomerRental{" +
                "id=" + id +
                ", customer=" + customerId +
                ", vehicle=" + vehicleId +
                ", reservationDate=" + reservationDate +
                ", pickUpDate=" + pickUpDate +
                ", dropOffDate=" + dropOffDate +
                ", isDamaged=" + isDamaged +
                ", totalCost=" + totalCost +
                '}';
    }
}
