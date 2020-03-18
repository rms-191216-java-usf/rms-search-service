package com.revature.rms.search.entites.campus;

import com.revature.rms.search.dtos.BuildingDto;

import java.util.List;
import java.util.Objects;

public class Building {

  private String id;
  private String name;
  private String abbrName;
  private Address physicalAddress;
  private int trainingLead;
  private List<Amenity> amenities;
  private List<Room> rooms;
  private ResourceMetadata resourceMetadata;

  public Building() {
    super();
  }

  public Building(
      String id,
      String name,
      String abbrName,
      Address physicalAddress,
      int trainingLead,
      List<Amenity> amenities,
      List<Room> rooms,
      ResourceMetadata resourceMetadata) {
    this.id = id;
    this.name = name;
    this.abbrName = abbrName;
    this.physicalAddress = physicalAddress;
    this.trainingLead = trainingLead;
    this.amenities = amenities;
    this.rooms = rooms;
    this.resourceMetadata = resourceMetadata;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

  public Address getPhysicalAddress() {
    return physicalAddress;
  }

  public void setPhysicalAddress(Address physicalAddress) {
    this.physicalAddress = physicalAddress;
  }

  public int getTrainingLead() {
    return trainingLead;
  }

  public void setTrainingLead(int trainingLead) {
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

  public BuildingDto extractBuilding() {
    return new BuildingDto(this.id, this.name, this.abbrName, physicalAddress.extractAddressDto(), this.amenities);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Building building = (Building) o;
    return id == building.id
        && trainingLead == building.trainingLead
        && Objects.equals(name, building.name)
        && Objects.equals(abbrName, building.abbrName)
        && Objects.equals(physicalAddress, building.physicalAddress)
        && Objects.equals(amenities, building.amenities)
        && Objects.equals(rooms, building.rooms)
        && Objects.equals(resourceMetadata, building.resourceMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, name, abbrName, physicalAddress, trainingLead, amenities, rooms, resourceMetadata);
  }

  @Override
  public String toString() {
    return "Building{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", abbrName='"
        + abbrName
        + '\''
        + ", physicalAddress="
        + physicalAddress
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
