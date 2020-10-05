package com.revature.rms.search.entites.employee;

import com.revature.rms.search.dtos.EmployeeDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {

  private int id;

  private String firstName;

  private String lastName;

  private String email;

  private String title;

  private Department department;

  private ResourceMetadata resourceMetadata;

  public Employee() {
    super();
  }

  public Employee(
      int id,
      String firstName,
      String lastName,
      String email,
      String title,
      Department department,
      ResourceMetadata resourceMetadata) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.title = title;
    this.department = department;
    this.resourceMetadata = resourceMetadata;
  }

  public Employee(
          int id,
          String firstName,
          String lastName,
          String email,
          String title) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.title = title;

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

  public Department getDepartment() {
    return department;
  }

  public ResourceMetadata getResourceMetadata() {
    return resourceMetadata;
  }

  // todo fix this.department.toString()
  public EmployeeDto extractEmployee() {
    return new EmployeeDto(
        this.id, this.firstName, this.lastName, this.email, this.title, this.department);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return id == employee.id
        && Objects.equals(firstName, employee.firstName)
        && Objects.equals(lastName, employee.lastName)
        && Objects.equals(email, employee.email)
        && Objects.equals(title, employee.title)
        && department == employee.department
        && Objects.equals(resourceMetadata, employee.resourceMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, email, title, department, resourceMetadata);
  }

  @Override
  public String toString() {
    return "Employee{"
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
        + ", department="
        + department
        + ", resourceMetadata="
        + resourceMetadata
        + '}';
  }
}
