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
  private AddressDto address;
  private EmployeeDto trainingLead;
  private List<AmenityDto> amenities;
  private List<RoomDto> rooms;
  private ResourceMetadataDto resourceMetadata;

  public BuildingDTO() {}

  public BuildingDTO(
      int id,
      String name,
      String abbrName,
      AddressDto address,
      List<AmenityDto> amenities,
      List<RoomDto> rooms,
      ResourceMetadataDto resourceMetadata) {
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
      AddressDto address,
      EmployeeDto trainingLead,
      List<AmenityDto> amenities,
      List <RoomDto> rooms,
      ResourceMetadataDto resourceMetadata) {
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

  public AddressDto getAddress() {
    return address;
  }

  public void setAddress(AddressDto address) {
    this.address = address;
  }

  public EmployeeDto getTrainingLead() {
    return trainingLead;
  }

  public void setTrainingLead(EmployeeDto trainingLead) {
    this.trainingLead = trainingLead;
  }

  public List<AmenityDto> getAmenities() {
    return amenities;
  }

  public void setAmenities(List<AmenityDto> amenities) {
    this.amenities = amenities;
  }

  public List<RoomDto> getRooms() {
    return rooms;
  }

  public void setRooms(List<RoomDto> rooms) {
    this.rooms = rooms;
  }

  public ResourceMetadataDto getResourceMetadata() {
    return resourceMetadata;
  }

  public void setResourceMetadata(ResourceMetadataDto resourceMetadata) {
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
