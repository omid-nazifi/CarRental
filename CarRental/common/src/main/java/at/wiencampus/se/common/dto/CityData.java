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
public class CityData implements Serializable {

    private Long cityId;
    private int countryId;
    private String cityName;

    @Override
    public String toString() {
        return "City{" +
                "id=" + cityId +
                ", countryId=" + countryId +
                ", name='" + cityName + '\'' +
                '}';
    }
}
