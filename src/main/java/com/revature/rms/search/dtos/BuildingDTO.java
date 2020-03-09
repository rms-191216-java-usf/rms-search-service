package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.campus.Address;
import com.revature.rms.search.entites.campus.Amenity;
import com.revature.rms.search.entites.campus.ResourceMetadata;
import com.revature.rms.search.entites.campus.Room;
import com.revature.rms.search.entites.employee.Employee;

import java.util.List;
import java.util.Objects;

public class BuildingDTO {

  private int id;
  private String name;
  private String abbrName;
  private Address address;
  private Employee trainingLead;
  private List<Amenity> amenities;
  private List<Room> rooms;
  private ResourceMetadata resourceMetadata;

  public BuildingDTO() {}

  public BuildingDTO(int id, String name, String abbrName, Address address, List<Amenity> amenities, List<Room> rooms, ResourceMetadata resourceMetadata) {
    this.id = id;
    this.name = name;
    this.abbrName = abbrName;
    this.address = address;
    this.amenities = amenities;
    this.rooms = rooms;
    this.resourceMetadata = resourceMetadata;
  }

  public BuildingDTO(
      int id,
      String name,
      String abbrName,
      Address address,
      Employee trainingLead,
      List<Amenity> amenities,
      List <Room> rooms,
      ResourceMetadata resourceMetadata) {
    this.id = id;
    this.name = name;
    this.abbrName = abbrName;
    this.address = address;
    this.trainingLead = trainingLead;
    this.amenities = amenities;
    this.rooms = rooms;
    this.resourceMetadata = resourceMetadata;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbbrName() {
    return abbrName;
  }

  public void setAbbrName(String abbrName) {
    this.abbrName = abbrName;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Employee getTrainingLead() {
    return trainingLead;
  }

  public void setTrainingLead(Employee trainingLead) {
    this.trainingLead = trainingLead;
  }

  public List<Amenity> getAmenities() {
    return amenities;
  }

  public void setAmenities(List<Amenity> amenities) {
    this.amenities = amenities;
  }

  public List<Room> getRooms() {
    return rooms;
  }

  public void setRooms(List<Room> rooms) {
    this.rooms = rooms;
  }

  public ResourceMetadata getResourceMetadata() {
    return resourceMetadata;
  }

  public void setResourceMetadata(ResourceMetadata resourceMetadata) {
    this.resourceMetadata = resourceMetadata;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BuildingDTO that = (BuildingDTO) o;
    return id == that.id
        && Objects.equals(name, that.name)
        && Objects.equals(abbrName, that.abbrName)
        && Objects.equals(address, that.address)
        && Objects.equals(trainingLead, that.trainingLead)
        && Objects.equals(amenities, that.amenities)
        && Objects.equals(rooms, that.rooms)
        && Objects.equals(resourceMetadata, that.resourceMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, name, abbrName, address, trainingLead, amenities, rooms, resourceMetadata);
  }

  @Override
  public String toString() {
    return "BuildingDTO{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", abbrName='"
        + abbrName
        + '\''
        + ", address="
        + address
        + ", trainingLead="
        + trainingLead
        + ", amenities="
        + amenities
        + ", rooms="
        + rooms
        + ", resourceMetadata="
        + resourceMetadata
        + '}';
  }
}
