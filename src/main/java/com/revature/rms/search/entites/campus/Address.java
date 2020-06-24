package com.revature.rms.search.entites.campus;

import com.revature.rms.search.dtos.AddressDto;

import java.util.Objects;

public class Address {

    private int id;
    private String unitStreet;
    private String city;
    private String state;
    private String zip;
    private String country;

    public Address() {
        super();
    }

    public Address(int id, String unitStreet, String city, String state, String zip, String country) {
        this.id = id;
        this.unitStreet = unitStreet;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitStreet() {
        return unitStreet;
    }

    public void setUnitStreet(String unitStreet) {
        this.unitStreet = unitStreet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(unitStreet, address.unitStreet) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state) &&
                Objects.equals(zip, address.zip) &&
                Objects.equals(country, address.country) &&
                Objects.equals(id, address.id);
    }

    public AddressDto extractAddressDto(){
        return new AddressDto(this.id, this.unitStreet, this.city, this.state, this.zip, this.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, unitStreet, city, state, zip, country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", unitStreet='" + unitStreet + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
