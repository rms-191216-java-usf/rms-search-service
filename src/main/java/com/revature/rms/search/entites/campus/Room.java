package com.revature.rms.search.entites.campus;

import com.revature.rms.search.dtos.RoomDto;
import com.revature.rms.core.metadata.*;

import java.util.List;
import java.util.Objects;

public class Room extends Resource {
  private int id;
  private String roomNumber;
  private int maxOccupancy;
  private List<RoomStatus> currentStatus;
  private int batchId;
  private List<Integer> workOrders;
  private ResourceMetadata resourceMetadata;

  public Room() {
    super();
  }

  public Room(
      int id,
      String roomNumber,
      int maxOccupancy,
      List<RoomStatus> currentStatus,
      int batchId,
      List<Integer> workOrders,
      ResourceMetadata resourceMetadata) {
    this.id = id;
    this.roomNumber = roomNumber;
    this.maxOccupancy = maxOccupancy;
    this.currentStatus = currentStatus;
    this.batchId = batchId;
    this.workOrders = workOrders;
    this.resourceMetadata = resourceMetadata;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public List<RoomStatus> getCurrentStatus() {
    return currentStatus;
  }

  public void setCurrentStatus(List<RoomStatus> currentStatus) {
    this.currentStatus = currentStatus;
  }

  public int getBatchId() {
    return batchId;
  }

  public void setBatchId(int batchId) {
    this.batchId = batchId;
  }

  public List<Integer> getWorkOrders() {
    return workOrders;
  }

  public void setWorkOrders(List<Integer> workOrders) {
    this.workOrders = workOrders;
  }

  public ResourceMetadata getResourceMetadata() {
    return resourceMetadata;
  }

  public void setResourceMetadata(ResourceMetadata resourceMetadata) {
    this.resourceMetadata = resourceMetadata;
  }

  public RoomDto extractRoom() {
    return new RoomDto(this.id, this.roomNumber, this.maxOccupancy);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Room room = (Room) o;
    return maxOccupancy == room.maxOccupancy
        && batchId == room.batchId
        && Objects.equals(id, room.id)
        && Objects.equals(roomNumber, room.roomNumber)
        && Objects.equals(currentStatus, room.currentStatus)
        && Objects.equals(workOrders, room.workOrders)
        && Objects.equals(resourceMetadata, room.resourceMetadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, roomNumber, maxOccupancy, currentStatus, batchId, workOrders, resourceMetadata);
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
        + ", roomStatus="
        + currentStatus
        + ", batchId="
        + batchId
        + ", workOrders="
        + workOrders
        + ", resourceMetadata="
        + resourceMetadata
        + '}';
  }
}
