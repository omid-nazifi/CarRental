package at.wiencampus.se.common.dto;

import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleData implements Serializable {
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
    Set<CustomerRentalData> reservations;

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
