package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.batch.Batch;
import com.revature.rms.search.entites.campus.ResourceMetadata;
import com.revature.rms.search.entites.campus.RoomStatus;
import com.revature.rms.search.entites.workorder.WorkOrder;


import java.util.List;
import java.util.Objects;

public class RoomDto {


    private int id;
    private String roomNumber;
    private int maxOccupancy;
    private boolean isActive;
    private RoomStatus currentStatus;
    private int batch;
    private List<Integer> workOrders;
    private ResourceMetadata resourceMetadata;

    public RoomDto() {
        super();
    }

    public RoomDto(int id, String roomNumber, int maxOccupancy, boolean isActive, RoomStatus currentStatus, int batch, List<Integer> workOrders, ResourceMetadata resourceMetadata) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.maxOccupancy = maxOccupancy;
        this.isActive = isActive;
        this.currentStatus = currentStatus;
        this.batch = batch;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public RoomStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(RoomStatus currentStatus) {
        this.currentStatus = currentStatus;
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

    public ResourceMetadata getResourceMetadata() {
        return resourceMetadata;
    }

    public void setResourceMetadata(ResourceMetadata resourceMetadata) {
        this.resourceMetadata = resourceMetadata;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDto roomDto = (RoomDto) o;
        return id == roomDto.id &&
                maxOccupancy == roomDto.maxOccupancy &&
                isActive == roomDto.isActive &&
                Objects.equals(roomNumber, roomDto.roomNumber) &&
                Objects.equals(currentStatus, roomDto.currentStatus) &&
                Objects.equals(batch, roomDto.batch) &&
                Objects.equals(workOrders, roomDto.workOrders) &&
                Objects.equals(resourceMetadata, roomDto.resourceMetadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomNumber, maxOccupancy, isActive, currentStatus, batch, workOrders, resourceMetadata);
    }

    @Override
    public String toString() {
        return "WorkOrderDto{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", maxOccupancy=" + maxOccupancy +
                ", isActive=" + isActive +
                ", currentStatus=" + currentStatus +
                ", batch=" + batch +
                ", workOrders=" + workOrders +
                ", resourceMetadata=" + resourceMetadata +
                '}';
    }
}
