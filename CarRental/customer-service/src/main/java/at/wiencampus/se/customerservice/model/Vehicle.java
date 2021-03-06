package at.wiencampus.se.customerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document("vehicle")
public class Vehicle {

    @Id
    private String vehicleId;
    private int cityId;
    private String vehicleName;
    private String manufacturer;
    private String energyType;
    private int seats;
    private Date productionYear;
    private boolean isManual;
    private String plateNumber;
    private int insuranceNumber;
    private int horsePower;
    private float cost;
    private String conditionDescription;
    private String url;


    public Vehicle() {

    }

    public Vehicle(int cityId, String vehicleName, String manufacturer, String energyType, int seats, Date productionYear, boolean isManual, String plateNumber, int insuranceNumber, int horsePower, float cost, String conditionDescription, String url) {
        this.vehicleId = UUID.randomUUID().toString();
        ;
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
        this.url = url;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String id) {
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
                ", url=" + url +
                ", conditionDescription='" + conditionDescription + '\'' +
                '}';
    }
}
