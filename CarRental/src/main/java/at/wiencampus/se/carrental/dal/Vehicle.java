package at.wiencampus.se.carrental.dal;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long vehicleId;
    private int cityId;
    private String vehicleName;
    private String manufacturer;
    private String energyType;
    private int seats;
    private Date productionYear;
    private boolean isManual;
    private int plateNumber;
    private int insuranceNumber;
    private int horsePower;
    private float cost;
    private String conditionDescription;
    @OneToMany(mappedBy = "vehicle")
    Set<CustomerRental> reservations;

    public Vehicle() {

    }

    public Vehicle(Long id, int cityId, String vehicleName, String manufacturer, String energyType, int seats, Date productionYear, boolean isManual, int plateNumber, int insuranceNumber, int horsePower, float cost, String conditionDescription) {
        this.vehicleId = id;
        this.cityId = cityId;
        this.vehicleName = vehicleName;
        this.manufacturer = manufacturer;
        this.energyType = energyType;
        this.seats = seats;
        this.productionYear = productionYear;
        this.isManual = isManual;
        this.plateNumber = plateNumber;
        this.insuranceNumber = insuranceNumber;
        this.horsePower = horsePower;
        this.cost = cost;
        this.conditionDescription = conditionDescription;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long id) {
        this.vehicleId = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Date getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Date productionYear) {
        this.productionYear = productionYear;
    }

    public boolean isManual() {
        return isManual;
    }

    public void setManual(boolean manual) {
        isManual = manual;
    }

    public int getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(int plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(int insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + vehicleId +
                ", cityId=" + cityId +
                ", vehicleName='" + vehicleName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", energyType='" + energyType + '\'' +
                ", seats=" + seats +
                ", productionYear=" + productionYear +
                ", isManual=" + isManual +
                ", plateNumber=" + plateNumber +
                ", insuranceNumber=" + insuranceNumber +
                ", horsePower=" + horsePower +
                ", cost=" + cost +
                ", conditionDescription='" + conditionDescription + '\'' +
                '}';
    }
}
