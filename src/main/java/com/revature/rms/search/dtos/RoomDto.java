package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.batch.Batch;
import com.revature.rms.search.entites.campus.ResourceMetadata;
import com.revature.rms.search.entites.campus.RoomStatus;
import com.revature.rms.search.entites.workorder.WorkOrder;

import java.util.List;
import java.util.Objects;

public class RoomDto {

  private String id;
  private String roomNumber;
  private int maxOccupancy;
  private boolean isActive;
  private List<RoomStatusDto> roomStatus;
  private int batch;
  private List<Integer> workOrders;
  private ResourceMetadataDto resourceMetadata;

  public RoomDto() {
    super();
  }

  public RoomDto(String id, String roomNumber, int maxOccupancy, boolean isActive) {
    this.id = id;
    this.roomNumber = roomNumber;
    this.maxOccupancy = maxOccupancy;
    this.isActive = isActive;
  }

  public RoomDto(
      String id,
      String roomNumber,
      int maxOccupancy,
      boolean isActive,
      List<RoomStatusDto> currentStatus,
      int batch,
      List<Integer> workOrders,
      ResourceMetadataDto resourceMetadata) {
    this.id = id;
    this.roomNumber = roomNumber;
    this.maxOccupancy = maxOccupancy;
    this.isActive = isActive;
    this.roomStatus = currentStatus;
    this.batch = batch;
    this.workOrders = workOrders;
    this.resourceMetadata = resourceMetadata;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(String roomNumber) {
    this.roomNumber = roomNumber;
  }

  public int getMaxOccupancy() {
    return maxOccupancy;
  }

  public void setMaxOccupancy(int maxOccupancy) {
    this.maxOccupancy = maxOccupancy;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public List<RoomStatusDto> getCurrentStatus() {
    return roomStatus;
  }

  public void setCurrentStatus(List<RoomStatusDto> currentStatus) {
    this.roomStatus = currentStatus;
  }

  public int getBatch() {
    return batch;
  }

  public void setBatch(int batch) {
    this.batch = batch;
  }

  public List<Integer> getWorkOrders() {
    return workOrders;
  }

  public void setWorkOrders(List<Integer> workOrders) {
    this.workOrders = workOrders;
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
    RoomDto roomDto = (RoomDto) o;
    return id == roomDto.id
        && maxOccupancy == roomDto.maxOccupancy
        && isActive == roomDto.isActive
        && Objects.equals(roomNumber, roomDto.roomNumber)
        && Objects.equals(roomStatus, roomDto.roomStatus)
        && Objects.equals(batch, roomDto.batch)
        && Objects.equals(workOrders, roomDto.workOrders)
        && Objects.equals(resourceMetadata, roomDto.resourceMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, roomNumber, maxOccupancy, isActive, roomStatus, batch, workOrders, resourceMetadata);
  }

  @Override
  public String toString() {
    return "WorkOrderDto{"
        + "id="
        + id
        + ", roomNumber='"
        + roomNumber
        + '\''
        + ", maxOccupancy="
        + maxOccupancy
        + ", isActive="
        + isActive
        + ", currentStatus="
        + roomStatus
        + ", batch="
        + batch
        + ", workOrders="
        + workOrders
        + ", resourceMetadata="
        + resourceMetadata
        + '}';
  }
}
