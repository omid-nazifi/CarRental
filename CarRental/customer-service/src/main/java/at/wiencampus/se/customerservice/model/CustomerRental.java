package at.wiencampus.se.customerservice.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "customerRental",uniqueConstraints = @UniqueConstraint(name = "customer_reservationDate_unique",columnNames = "reservationDate"))
@Entity(name = "customerRental")
public class CustomerRental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private Date reservationDate;

    private Date pickUpDate;

    private Date dropOffDate;

    private boolean isDamaged;

    private float totalCost;

    public CustomerRental() {
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
                ", customer=" + customer.getCustomerId() +
                ", vehicle=" + vehicle.getVehicleId() +
                ", reservationDate=" + reservationDate +
                ", pickUpDate=" + pickUpDate +
                ", dropOffDate=" + dropOffDate +
                ", isDamaged=" + isDamaged +
                ", totalCost=" + totalCost +
                '}';
    }
}
