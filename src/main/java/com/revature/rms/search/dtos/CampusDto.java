package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.campus.Address;
import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.campus.Campus;
import com.revature.rms.search.entites.employee.Employee;
import com.revature.rms.search.entites.campus.ResourceMetadata;

import java.util.List;
import java.util.Objects;

public class CampusDto {
    private int id;
    private String name;
    private String abbrName;
    private Address shippingAddress;
    private EmployeeDto trainingManager;
    private EmployeeDto stagingManager;
    private EmployeeDto hrLead;
    private List<Building> buildings;
    private List<EmployeeDto> corporateEmployees;
    private ResourceMetadata resourceMetadata;

    public CampusDto() {
        super();
    }

    public CampusDto(int id, String name, String abbrName, Address shippingAddress, List<Building> buildings, ResourceMetadata resourceMetadata) {
        this.id = id;
        this.name = name;
        this.abbrName = abbrName;
        this.shippingAddress = shippingAddress;
        this.buildings = buildings;
        this.resourceMetadata = resourceMetadata;
    }

    public CampusDto(
            int id,
            String name,
            String abbrName,
            Address shippingAddress,
            EmployeeDto trainingManager,
            EmployeeDto stagingManager,
            EmployeeDto hrLead,
            List<Building> buildings,
            List<EmployeeDto> corporateEmployees,
            ResourceMetadata resourceMetadata) {
        this.id = id;
        this.name = name;
        this.abbrName = abbrName;
        this.shippingAddress = shippingAddress;
        this.trainingManager = trainingManager;
        this.stagingManager = stagingManager;
        this.hrLead = hrLead;
        this.buildings = buildings;
        this.corporateEmployees = corporateEmployees;
        this.resourceMetadata = resourceMetadata;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbbrName() {
        return abbrName;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public EmployeeDto getTrainingManager() {
        return trainingManager;
    }

    public EmployeeDto getStagingManager() {
        return stagingManager;
    }

    public EmployeeDto getHrLead() {
        return hrLead;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public List<EmployeeDto> getCorporateEmployees() {
        return corporateEmployees;
    }

    public ResourceMetadata getResourceMetadata() {
        return resourceMetadata;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setTrainingManager(EmployeeDto trainingManager) {
        this.trainingManager = trainingManager;
    }

    public void setStagingManager(EmployeeDto stagingManager) {
        this.stagingManager = stagingManager;
    }

    public void setHrLead(EmployeeDto hrLead) {
        this.hrLead = hrLead;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public void setCorporateEmployees(List<EmployeeDto> corporateEmployees) {
        this.corporateEmployees = corporateEmployees;
    }

    public void setResourceMetadata(ResourceMetadata resourceMetadata) {
        this.resourceMetadata = resourceMetadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampusDto campusDto = (CampusDto) o;
        return id == campusDto.id
                && Objects.equals(name, campusDto.name)
                && Objects.equals(abbrName, campusDto.abbrName)
                && Objects.equals(shippingAddress, campusDto.shippingAddress)
                && Objects.equals(trainingManager, campusDto.trainingManager)
                && Objects.equals(stagingManager, campusDto.stagingManager)
                && Objects.equals(hrLead, campusDto.hrLead)
                && Objects.equals(buildings, campusDto.buildings)
                && Objects.equals(corporateEmployees, campusDto.corporateEmployees)
                && Objects.equals(resourceMetadata, campusDto.resourceMetadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                abbrName,
                shippingAddress,
                trainingManager,
                stagingManager,
                hrLead,
                buildings,
                corporateEmployees,
                resourceMetadata);
    }

    @Override
    public String toString() {
        return "CampusDto{"
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
                + ", trainingManager="
                + trainingManager
                + ", stagingManager="
                + stagingManager
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
