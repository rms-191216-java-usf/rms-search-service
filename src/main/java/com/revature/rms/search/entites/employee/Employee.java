package com.revature.rms.search.entites.employee;


import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {


    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private Department department;

    private ResourceMetadata resourceMetadata;

    public Employee() {
        super();
    }

    public Employee(int id, String firstName, String lastName, String email, Department department, ResourceMetadata resourceMetadata) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public Department getDepartment() {
        return department;
    }

    public ResourceMetadata getResourceMetadata() {
        return resourceMetadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(email, employee.email) &&
                department == employee.department &&
                Objects.equals(resourceMetadata, employee.resourceMetadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, department, resourceMetadata);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                ", resourceMetadata=" + resourceMetadata +
                '}';
    }
}
