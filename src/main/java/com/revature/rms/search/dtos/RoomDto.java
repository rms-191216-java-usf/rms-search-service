package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.batch.Batch;
import com.revature.rms.search.entites.campus.ResourceMetadata;
import com.revature.rms.search.entites.campus.RoomStatus;
import com.revature.rms.search.entites.workorder.WorkOrder;

import java.util.ArrayList;
import java.util.Objects;

public class RoomDto {


    private int id;
    private String roomNumber;
    private int maxOccupancy;
    private boolean isActive;
    private RoomStatus currentStatus;
    private Batch batch;
    private ArrayList<WorkOrder> workOrders;
    private ResourceMetadata resourceMetadata;

    public RoomDto() {
        super();
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

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public ArrayList<WorkOrder> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(ArrayList<WorkOrder> workOrders) {
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
