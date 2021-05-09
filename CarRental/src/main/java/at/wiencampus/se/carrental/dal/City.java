package at.wiencampus.se.carrental.dal;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.UUID;

@Document("city")
public class City {

    @Id
    private String cityId;
    private int countryId;
    private String cityName;

    public City() {
        this.cityId= UUID.randomUUID().toString();
    }

    public City(int countryId, String name) {

        this.cityId= UUID.randomUUID().toString();
        this.countryId = countryId;
        this.cityName = name;
    }

    public String getCityId() { return cityId; }

    public void setCityId(String id) {
        this.cityId = id;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String name) {
        this.cityName = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + cityId +
                ", countryId=" + countryId +
                ", name='" + cityName + '\'' +
                '}';
    }
}
