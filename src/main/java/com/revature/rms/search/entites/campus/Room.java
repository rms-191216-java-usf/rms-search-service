package com.revature.rms.search.entites.campus;

import com.revature.rms.search.dtos.RoomDto;
import com.revature.rms.search.entites.common.ResourceMetadata;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Room {
  private String id;
  private String roomNumber;
  private int maxOccupancy;
  private boolean isActive;
  private List<RoomStatus> roomStatus;
  private String batchId;
  private List<String> workOrders;
  private ResourceMetadata resourceMetadata;

  public Room() {
    super();
  }

  public Room(
      String id,
      String roomNumber,
      int maxOccupancy,
      boolean isActive,
      List<RoomStatus> roomStatus,
      String batchId,
      List<String> workOrders,
      ResourceMetadata resourceMetadata) {
    this.id = id;
    this.roomNumber = roomNumber;
    this.maxOccupancy = maxOccupancy;
    this.isActive = isActive;
    this.roomStatus = roomStatus;
    this.batchId = batchId;
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

  public List<RoomStatus> getRoomStatus() {
    return roomStatus;
  }

  public void setRoomStatus(List<RoomStatus> roomStatus) {
    this.roomStatus = roomStatus;
  }

  public String getBatchId() {
    return batchId;
  }

  public void setBatchId(String batchId) {
    this.batchId = batchId;
  }

  public List<String> getWorkOrders() {
    return workOrders;
  }

  public void setWorkOrders(List<String> workOrders) {
    this.workOrders = workOrders;
  }

  public ResourceMetadata getResourceMetadata() {
    return resourceMetadata;
  }

  public void setResourceMetadata(ResourceMetadata resourceMetadata) {
    this.resourceMetadata = resourceMetadata;
  }

  public RoomDto extractRoom() {
    return new RoomDto(this.id, this.roomNumber, this.maxOccupancy, this.isActive);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Room room = (Room) o;
    return maxOccupancy == room.maxOccupancy
        && isActive == room.isActive
        && batchId == room.batchId
        && Objects.equals(id, room.id)
        && Objects.equals(roomNumber, room.roomNumber)
        && Objects.equals(roomStatus, room.roomStatus)
        && Objects.equals(workOrders, room.workOrders)
        && Objects.equals(resourceMetadata, room.resourceMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, roomNumber, maxOccupancy, isActive, roomStatus, batchId, workOrders, resourceMetadata);
  }

  @Override
  public String toString() {
    return "Room{"
        + "id='"
        + id
        + '\''
        + ", roomNumber='"
        + roomNumber
        + '\''
        + ", maxOccupancy="
        + maxOccupancy
        + ", isActive="
        + isActive
        + ", roomStatus="
        + roomStatus
        + ", batchId="
        + batchId
        + ", workOrders="
        + workOrders
        + ", resourceMetadata="
        + resourceMetadata
        + '}';
  }
}
