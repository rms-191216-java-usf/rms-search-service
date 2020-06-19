package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.employee.AppUser;
import com.revature.rms.search.entites.employee.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AppUserDto {

  private int id;
  private String username;
  private String password;
  private int employeeId;
  private List<UserRole> role;

  public AppUserDto() {}

//  public AppUserDto(AppUser user) {
//    this.id = user.getId();
//    this.username = user.getUsername();
//    this.role = user.getRole();
//    this.employeeId = 1;
//  }

  public AppUserDto(int id, String username, String password, int employeeId, List<UserRole> role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.employeeId = employeeId;
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public List<UserRole> getRole() {
    return role;
  }

  public void setRole(List<UserRole> role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AppUserDto that = (AppUserDto) o;
    return id == that.id
        && employeeId == that.employeeId
        && Objects.equals(username, that.username)
        && Objects.equals(password, that.password)
        && Objects.equals(role, that.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, employeeId, role);
  }

  @Override
  public String toString() {
    return "AppUserDTO{"
        + "id="
        + id
        + ", username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + ", employeeId="
        + employeeId
        + ", role="
        + role
        + '}';
  }


}
