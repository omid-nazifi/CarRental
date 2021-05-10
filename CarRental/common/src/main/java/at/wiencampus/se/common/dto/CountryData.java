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
public class CountryData implements Serializable {
    private String countryId;
    private String countryName;

    @Override
    public String toString() {
        return "Country{" +
                "id=" + countryId +
                ", name='" + countryName + '\'' +
                '}';
    }
}
