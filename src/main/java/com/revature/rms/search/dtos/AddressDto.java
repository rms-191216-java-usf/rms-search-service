package com.revature.rms.search.dtos;

import java.util.Objects;

public class AddressDto {

  private String unitStreet;
  private String city;
  private String state;
  private String zip;
  private String country;

  public AddressDto() {
    super();
  }

  public AddressDto(String unitStreet, String city, String state, String zip, String country) {
    this.unitStreet = unitStreet;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.country = country;
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
    AddressDto that = (AddressDto) o;
    return zip == that.zip
        && Objects.equals(unitStreet, that.unitStreet)
        && Objects.equals(city, that.city)
        && Objects.equals(state, that.state)
        && Objects.equals(country, that.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(unitStreet, city, state, zip, country);
  }

  @Override
  public String toString() {
    return "AddressDto{"
        + "unitStreet='"
        + unitStreet
        + '\''
        + ", city='"
        + city
        + '\''
        + ", state='"
        + state
        + '\''
        + ", zip="
        + zip
        + ", country='"
        + country
        + '\''
        + '}';
  }
}
