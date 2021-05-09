package at.wiencampus.se.customerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("country")
public class Country {

    @Id
    private String countryId;
    private String countryName;

    public Country() {

    }

    public Country(String name) {
        this.countryId = UUID.randomUUID().toString();
        this.countryName = name;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String id) {
        this.countryId = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String name) {
        this.countryName = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + countryId +
                ", name='" + countryName + '\'' +
                '}';
    }
}
