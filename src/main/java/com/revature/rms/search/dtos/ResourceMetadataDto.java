package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.employee.Employee;

import java.util.Objects;

public class ResourceMetadataDto {
<<<<<<< HEAD
    private Employee resourceCreator;
    private String resourceCreationDateTime;
    private Employee lastModifier;
    private String lastModifiedDateTime;
    private Employee resourceOwner;
=======

    private EmployeeDto resourceCreator;
    private String resourceCreationDateTime;
    private EmployeeDto lastModifier;
    private String lastModifiedDateTime;
    private EmployeeDto resourceOwner;


>>>>>>> 9cb7dd483ce07d33040cddf826694d04791263d4
    public ResourceMetadataDto() {
    }
    public ResourceMetadataDto(String resourceCreationDateTime, String lastModifiedDateTime) {
        this.resourceCreationDateTime = resourceCreationDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }
<<<<<<< HEAD
    public ResourceMetadataDto(Employee resourceCreator, String resourceCreationDateTime, Employee lastModifier, String lastModifiedDateTime, Employee resourceOwner) {
=======
    public ResourceMetadataDto(EmployeeDto resourceCreator, String resourceCreationDateTime, EmployeeDto lastModifier, String lastModifiedDateTime, EmployeeDto resourceOwner) {
>>>>>>> 9cb7dd483ce07d33040cddf826694d04791263d4
        this.resourceCreator = resourceCreator;
        this.resourceCreationDateTime = resourceCreationDateTime;
        this.lastModifier = lastModifier;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.resourceOwner = resourceOwner;
    }
<<<<<<< HEAD
    public Employee getResourceCreator() {
        return resourceCreator;
    }
    public void setResourceCreator(Employee resourceCreator) {
=======
    public EmployeeDto getResourceCreator() {
        return resourceCreator;
    }
    public void setResourceCreator(EmployeeDto resourceCreator) {
>>>>>>> 9cb7dd483ce07d33040cddf826694d04791263d4
        this.resourceCreator = resourceCreator;
    }
    public String getResourceCreationDateTime() {
        return resourceCreationDateTime;
    }
    public void setResourceCreationDateTime(String resourceCreationDateTime) {
        this.resourceCreationDateTime = resourceCreationDateTime;
    }
<<<<<<< HEAD
    public Employee getLastModifier() {
        return lastModifier;
    }
    public void setLastModifier(Employee lastModifier) {
=======
    public EmployeeDto getLastModifier() {
        return lastModifier;
    }
    public void setLastModifier(EmployeeDto lastModifier) {
>>>>>>> 9cb7dd483ce07d33040cddf826694d04791263d4
        this.lastModifier = lastModifier;
    }
    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }
    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }
<<<<<<< HEAD
    public Employee getResourceOwner() {
        return resourceOwner;
    }
    public void setResourceOwner(Employee resourceOwner) {
=======
    public EmployeeDto getResourceOwner() {
        return resourceOwner;
    }
    public void setResourceOwner(EmployeeDto resourceOwner) {
>>>>>>> 9cb7dd483ce07d33040cddf826694d04791263d4
        this.resourceOwner = resourceOwner;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceMetadataDto that = (ResourceMetadataDto) o;
        return Objects.equals(resourceCreator, that.resourceCreator) &&
                Objects.equals(resourceCreationDateTime, that.resourceCreationDateTime) &&
                Objects.equals(lastModifier, that.lastModifier) &&
                Objects.equals(lastModifiedDateTime, that.lastModifiedDateTime) &&
                Objects.equals(resourceOwner, that.resourceOwner);
    }
    @Override
    public int hashCode() {
        return Objects.hash(resourceCreator, resourceCreationDateTime, lastModifier, lastModifiedDateTime, resourceOwner);
    }
    @Override
    public String toString() {
        return "ResourceMetadataDto{" +
                "resourceCreator=" + resourceCreator +
                ", resourceCreationDateTime='" + resourceCreationDateTime + '\'' +
                ", lastModifier=" + lastModifier +
                ", lastModifiedDateTime='" + lastModifiedDateTime + '\'' +
                ", resourceOwner=" + resourceOwner +
                '}';
    }
}
