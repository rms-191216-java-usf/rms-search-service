package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.campus.AmenityStatus;
import com.revature.rms.search.entites.campus.AmenityType;

import java.util.Objects;

public class AmenityDto {

  private AmenityType type;
  private AmenityStatus status;

  public AmenityDto() {super();}

  public AmenityDto(AmenityType type, AmenityStatus status) {
    this.type = type;
    this.status = status;
  }

  public AmenityType getType() {return type;}

  public void setType(AmenityType type) {this.type = type;}

  public AmenityStatus getStatus() {return status;}

  public void setStatus(AmenityStatus status) {this.status = status;}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AmenityDto that = (AmenityDto) o;
    return Objects.equals(type, that.type) && Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {return Objects.hash(type, status);}

  @Override
  public String toString() {
    return "AmenityDto{" + "type='" + type + '\'' + ", status='" + status + '\'' + '}';
  }
}
