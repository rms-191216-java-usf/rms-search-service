package com.revature.rms.search.dtos;

import java.util.Objects;

public class AmenityDto {

  private String type;
  private String status;

  public AmenityDto() {
    super();
  }

  public AmenityDto(String type, String status) {
    this.type = type;
    this.status = status;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public AmenityDto extractAmenityDto(){
    return new AmenityDto(this.type, this.status);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AmenityDto that = (AmenityDto) o;
    return Objects.equals(type, that.type) && Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, status);
  }

  @Override
  public String toString() {
    return "AmenityDto{" + "type='" + type + '\'' + ", status='" + status + '\'' + '}';
  }
}
