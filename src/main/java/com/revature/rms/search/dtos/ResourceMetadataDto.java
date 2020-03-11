package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.employee.Employee;

import java.util.Objects;

public class ResourceMetadataDto {

    private EmployeeDto resourceCreator;
    private String resourceCreationDateTime;
    private EmployeeDto lastModifier;
    private String lastModifiedDateTime;
    private EmployeeDto resourceOwner;


    public ResourceMetadataDto() {
    }
    public ResourceMetadataDto(String resourceCreationDateTime, String lastModifiedDateTime) {
        this.resourceCreationDateTime = resourceCreationDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }
    public ResourceMetadataDto(EmployeeDto resourceCreator, String resourceCreationDateTime, EmployeeDto lastModifier, String lastModifiedDateTime, EmployeeDto resourceOwner) {
        this.resourceCreator = resourceCreator;
        this.resourceCreationDateTime = resourceCreationDateTime;
        this.lastModifier = lastModifier;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.resourceOwner = resourceOwner;
    }
    public EmployeeDto getResourceCreator() {
        return resourceCreator;
    }
    public void setResourceCreator(EmployeeDto resourceCreator) {
        this.resourceCreator = resourceCreator;
    }
    public String getResourceCreationDateTime() {
        return resourceCreationDateTime;
    }
    public void setResourceCreationDateTime(String resourceCreationDateTime) {
        this.resourceCreationDateTime = resourceCreationDateTime;
    }
    public EmployeeDto getLastModifier() {
        return lastModifier;
    }
    public void setLastModifier(EmployeeDto lastModifier) {
        this.lastModifier = lastModifier;
    }
    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }
    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }
    public EmployeeDto getResourceOwner() {
        return resourceOwner;
    }
    public void setResourceOwner(EmployeeDto resourceOwner) {
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
