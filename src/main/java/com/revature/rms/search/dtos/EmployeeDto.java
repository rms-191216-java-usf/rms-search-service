package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.employee.ResourceMetadata;

import java.util.Objects;

public class EmployeeDto {
  private int id;
  private String firstName;
  private String lastName;
  private String email;
  private String title;
  private String department;
  private ResourceMetadataDto resourceMetadata;

  public EmployeeDto() {
    super();
  }

  public EmployeeDto(
      int id,
      String firstName,
      String lastName,
      String email,
      String title,
      String department,
      ResourceMetadataDto resourceMetadata) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.title = title;
    this.department = department;
    this.resourceMetadata = resourceMetadata;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getTitle() {
    return title;
  }

  public String getDepartment() {
    return department;
  }

  public ResourceMetadataDto getResourceMetadata() {
    return resourceMetadata;
  }

  public EmployeeDto extractEmployeeDto(){
    return new EmployeeDto(this.id, this.firstName, this.lastName, this.email, this.title, this.department, this.resourceMetadata);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EmployeeDto that = (EmployeeDto) o;
    return id == that.id
        && Objects.equals(firstName, that.firstName)
        && Objects.equals(lastName, that.lastName)
        && Objects.equals(email, that.email)
        && Objects.equals(title, that.title)
        && Objects.equals(department, that.department)
        && Objects.equals(resourceMetadata, that.resourceMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, email, title, department, resourceMetadata);
  }

  @Override
  public String toString() {
    return "EmployeeDto{"
        + "id="
        + id
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", email='"
        + email
        + '\''
        + ", title='"
        + title
        + '\''
        + ", department='"
        + department
        + '\''
        + ", resourceMetadata="
        + resourceMetadata
        + '}';
  }
}
