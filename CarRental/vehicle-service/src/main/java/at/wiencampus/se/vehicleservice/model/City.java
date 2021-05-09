package at.wiencampus.se.vehicleservice.model;

import javax.persistence.*;

@Entity(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long cityId;
    private int countryId;
    private String cityName;

    public City() {

    }

    public City(Long cityId, int countryId, String name) {
        this.cityId = cityId;
        this.countryId = countryId;
        this.cityName = name;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long id) {
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
