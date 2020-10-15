package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.employee.Department;

import java.util.Objects;

public class EmployeeDto {
  private int id;
  private String firstName;
  private String lastName;
  private String email;
  private String title;
  private Department department;
  private ResourceMetadataDto resourceMetadata;

  public EmployeeDto() {
    super();
  }

  public EmployeeDto(
      int id, String firstName, String lastName, String email, String title, Department department) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.title = title;
    this.department = department;
  }

  public EmployeeDto(
      int id,
      String firstName,
      String lastName,
      String email,
      String title,
      Department department,
      ResourceMetadataDto resourceMetadata) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.title = title;
    this.department = department;
    this.resourceMetadata = resourceMetadata;
  }

    public EmployeeDto(int id, String firstName, String lastName, String email, String title) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.title = title;
    }

    public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {

    this.department = department;
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
