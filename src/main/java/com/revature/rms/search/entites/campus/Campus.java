package com.revature.rms.search.entites.campus;

import com.revature.rms.search.dtos.CampusDto;
import com.revature.rms.search.entites.common.ResourceMetadata;

import java.util.List;
import java.util.Objects;

public class Campus {

  private String id;
  private String name;
  private String abbrName;
  private Address shippingAddress;
  private int trainingManagerId;
  private int stagingManagerId;
  private int hrLead;
  private List<Building> buildings;
  private List<Integer> corporateEmployees;
  private ResourceMetadata resourceMetadata;

  public Campus() {
    super();
  }

  public Campus(
      String id,
      String name,
      String abbrName,
      Address shippingAddress,
      int trainingManagerId,
      int stagingManagerId,
      int hrLead,
      List<Building> buildings,
      List<Integer> corporateEmployees,
      ResourceMetadata resourceMetadata) {
    this.id = id;
    this.name = name;
    this.abbrName = abbrName;
    this.shippingAddress = shippingAddress;
    this.trainingManagerId = trainingManagerId;
    this.stagingManagerId = stagingManagerId;
    this.hrLead = hrLead;
    this.buildings = buildings;
    this.corporateEmployees = corporateEmployees;
    this.resourceMetadata = resourceMetadata;
  }

  //need to crate a constructor
    public Campus(String s, String university_of_south_florida, String usf, Address address) {
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

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public int getTrainingManagerId() {
    return trainingManagerId;
  }

  public void setTrainingManagerId(int trainingManagerId) {
    this.trainingManagerId = trainingManagerId;
  }

  public int getStagingManagerId() {
    return stagingManagerId;
  }

  public void setStagingManagerId(int stagingManagerId) {
    this.stagingManagerId = stagingManagerId;
  }

  public int getHrLead() {
    return hrLead;
  }

  public void setHrLead(int hrLead) {
    this.hrLead = hrLead;
  }

  public List<Building> getBuildings() {
    return buildings;
  }

  public void setBuildings(List<Building> buildings) {
    this.buildings = buildings;
  }

  public List<Integer> getCorporateEmployees() {
    return corporateEmployees;
  }

  public void setCorporateEmployees(List<Integer> corporateEmployees) {
    this.corporateEmployees = corporateEmployees;
  }

  public ResourceMetadata getResourceMetadata() {
    return resourceMetadata;
  }

  public void setResourceMetadata(ResourceMetadata resourceMetadata) {
    this.resourceMetadata = resourceMetadata;
  }

  public CampusDto extractCampus() {
    return new CampusDto(this.id, this.name, this.abbrName, this.shippingAddress);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Campus campus = (Campus) o;
    return id == campus.id
        && trainingManagerId == campus.trainingManagerId
        && stagingManagerId == campus.stagingManagerId
        && hrLead == campus.hrLead
        && Objects.equals(name, campus.name)
        && Objects.equals(abbrName, campus.abbrName)
        && Objects.equals(shippingAddress, campus.shippingAddress)
        && Objects.equals(buildings, campus.buildings)
        && Objects.equals(corporateEmployees, campus.corporateEmployees)
        && Objects.equals(resourceMetadata, campus.resourceMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        name,
        abbrName,
        shippingAddress,
        trainingManagerId,
        stagingManagerId,
        hrLead,
        buildings,
        corporateEmployees,
        resourceMetadata);
  }

  @Override
  public String toString() {
    return "Campus{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", abbrName='"
        + abbrName
        + '\''
        + ", shippingAddress="
        + shippingAddress
        + ", trainingManagerId="
        + trainingManagerId
        + ", stagingManagerId="
        + stagingManagerId
        + ", hrLead="
        + hrLead
        + ", buildings="
        + buildings
        + ", corporateEmployees="
        + corporateEmployees
        + ", resourceMetadata="
        + resourceMetadata
        + '}';
  }
}
