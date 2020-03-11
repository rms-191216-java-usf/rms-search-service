package com.revature.rms.search.services;

import com.revature.rms.search.clients.CampusClient;
import com.revature.rms.search.clients.EmployeeClient;
import com.revature.rms.search.dtos.BuildingDTO;
import com.revature.rms.search.dtos.CampusDto;
import com.revature.rms.search.dtos.ResourceMetadataDto;
import com.revature.rms.search.dtos.RoomDto;
import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.campus.Campus;
import com.revature.rms.search.entites.campus.ResourceMetadata;
import com.revature.rms.search.entites.campus.Room;
import com.revature.rms.search.entites.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MassiveService {
    private EmployeeClient empClient;
    private CampusClient campClient;


    @Autowired
    public MassiveService(EmployeeClient employeeClient, CampusClient campusClient) {
        super();
        this.empClient = employeeClient;
        this.campClient = campusClient;
    }
    public CampusDto getCampusById(String id) {
        Campus campus = campClient.getCampusById(id);
        CampusDto campusDto = getCampusObjects(campus);
        campusDto.setBuildings(getBuildingData(campus.getBuildings()));
        return campusDto;
    }
    public List<BuildingDTO> getBuildingData(List<Building> buildings) {
        List<BuildingDTO> buildingDtos = new ArrayList<>();
        for(int i = 0; i < buildings.size(); i++) {
            Building building = buildings.get(i);
            Employee emp = getEmployeeById(building.getTrainingLead());
            BuildingDTO b = building.extractBuilding();
            b.setTrainingLead(emp);
            b.setRooms(getEachRoomMeta(building.getRooms()));
            b.setResourceMetadata(campusMetaData(building.getResourceMetadata()));
            buildingDtos.add(b);
        }
        return buildingDtos;
    }
    public CampusDto getCampusObjects(Campus campus) {
        CampusDto dto = campus.extractCampus();
        dto.setTrainingManager(getEmployeeById(campus.getTrainingManagerId()));
        dto.setStagingManager(getEmployeeById(campus.getStagingManagerId()));
        dto.setHrLead(getEmployeeById(campus.getHrLead()));
        dto.setResourceMetadata(campusMetaData(campus.getResourceMetadata()));
        return dto;
    }
    public Employee getEmployeeById(String id) {
        Employee emp = empClient.getEmployeeById(id);
        return emp;
    }
    public ResourceMetadataDto campusMetaData(ResourceMetadata data) {
        ResourceMetadataDto dto = data.extractCampusMeta();
        dto.setResourceCreator(getEmployeeById(data.getResourceCreator()));
        dto.setLastModifier(getEmployeeById(data.getLastModifier()));
        dto.setResourceCreator(getEmployeeById(data.getResourceOwner()));
        return dto;
    }
    public List<RoomDto> getEachRoomMeta(List<Room> rooms) {
        List<RoomDto> roomDtos = new ArrayList<>();
        rooms.forEach(r -> roomDtos.add(r.extractRoom()));
        for(int i = 0; i < rooms.size(); i++){
            Room room = rooms.get(i);
            roomDtos.get(i).setResourceMetadata(campusMetaData(room.getResourceMetadata()));
        }
        return roomDtos;
    }
}