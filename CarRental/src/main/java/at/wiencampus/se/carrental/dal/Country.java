package at.wiencampus.se.carrental.dal;

import javax.persistence.*;

@Entity(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long countryId;
    private String countryName;

    public Country() {

    }

    public Country(Long id, String name) {
        this.countryId = id;
        this.countryName = name;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long id) {
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
