package com.revature.rms.search.dtos;

import java.util.List;
import java.util.Objects;

public class AppUserDTO {

  private int id;
  private String username;
  private String password;
  private int employeeId;
  private List<String> role;

  public AppUserDTO() {}

  public AppUserDTO(int id, String username, String password, int employeeId, List<String> role) {
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

  public List<String> getRole() {
    return role;
  }

  public void setRole(List<String> role) {
    this.role = role;
  }

  public AppUserDTO extractAppUserDto(){
    return new AppUserDTO(this.id, this.username, this.password, this.employeeId, this.role);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AppUserDTO that = (AppUserDTO) o;
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
