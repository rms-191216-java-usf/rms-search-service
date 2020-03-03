package com.revature.rms.search.dtos;


import com.revature.rms.search.entites.employee.Employee;

import java.util.Objects;

public class WorkOrderDto {

    private int id;
    private String createdDateTime;
    private String resolvedDateTime;
    private String category;
    private String description;
    private String contactEmail;
    private Employee creator;
    private Employee resolver;

    public WorkOrderDto() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getResolvedDateTime() {
        return resolvedDateTime;
    }

    public void setResolvedDateTime(String resolvedDateTime) {
        this.resolvedDateTime = resolvedDateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Employee getCreator() {
        return creator;
    }

    public void setCreator(Employee creator) {
        this.creator = creator;
    }

    public Employee getResolver() {
        return resolver;
    }

    public void setResolver(Employee resolver) {
        this.resolver = resolver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkOrderDto that = (WorkOrderDto) o;
        return id == that.id &&
                Objects.equals(createdDateTime, that.createdDateTime) &&
                Objects.equals(resolvedDateTime, that.resolvedDateTime) &&
                Objects.equals(category, that.category) &&
                Objects.equals(description, that.description) &&
                Objects.equals(contactEmail, that.contactEmail) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(resolver, that.resolver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDateTime, resolvedDateTime, category, description, contactEmail, creator, resolver);
    }

    @Override
    public String toString() {
        return "WorkOrderDto{" +
                "id=" + id +
                ", createdDateTime='" + createdDateTime + '\'' +
                ", resolvedDateTime='" + resolvedDateTime + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", creator=" + creator +
                ", resolver=" + resolver +
                '}';
    }
}
