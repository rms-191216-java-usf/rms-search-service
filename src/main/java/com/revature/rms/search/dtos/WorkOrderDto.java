package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.workorder.Category;

import java.util.Objects;

public class WorkOrderDto {

  private int id;
  private String createdDateTime;
  private String resolvedDateTime;
  private Category category;
  private String description;
  private String contactEmail;
  private EmployeeDto creator;
  private EmployeeDto resolver;

  public WorkOrderDto() {
    super();
  }

  public WorkOrderDto(
      int id,
      String createdDateTime,
      String resolvedDateTime,
      Category category,
      String description,
      String contactEmail) {
    this.id = id;
    this.createdDateTime = createdDateTime;
    this.resolvedDateTime = resolvedDateTime;
    this.category = category;
    this.description = description;
    this.contactEmail = contactEmail;
  }

  public WorkOrderDto(
      int id,
      String createdDateTime,
      String resolvedDateTime,
      Category category,
      String description,
      String contactEmail,
      EmployeeDto creator,
      EmployeeDto resolver) {
    this.id = id;
    this.createdDateTime = createdDateTime;
    this.resolvedDateTime = resolvedDateTime;
    this.category = category;
    this.description = description;
    this.contactEmail = contactEmail;
    this.creator = creator;
    this.resolver = resolver;
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

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
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

  public EmployeeDto getCreator() {
    return creator;
  }

  public void setCreator(EmployeeDto creator) {
    this.creator = creator;
  }

  public EmployeeDto getResolver() {
    return resolver;
  }

  public void setResolver(EmployeeDto resolver) {
    this.resolver = resolver;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WorkOrderDto that = (WorkOrderDto) o;
    return id == that.id
        && Objects.equals(createdDateTime, that.createdDateTime)
        && Objects.equals(resolvedDateTime, that.resolvedDateTime)
        && Objects.equals(category, that.category)
        && Objects.equals(description, that.description)
        && Objects.equals(contactEmail, that.contactEmail)
        && Objects.equals(creator, that.creator)
        && Objects.equals(resolver, that.resolver);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        createdDateTime,
        resolvedDateTime,
        category,
        description,
        contactEmail,
        creator,
        resolver);
  }

  @Override
  public String toString() {
    return "WorkOrderDto{"
        + "id="
        + id
        + ", createdDateTime='"
        + createdDateTime
        + '\''
        + ", resolvedDateTime='"
        + resolvedDateTime
        + '\''
        + ", category='"
        + category
        + '\''
        + ", description='"
        + description
        + '\''
        + ", contactEmail='"
        + contactEmail
        + '\''
        + ", creator="
        + creator
        + ", resolver="
        + resolver
        + '}';
  }
}
