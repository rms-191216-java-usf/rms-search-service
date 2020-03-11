package com.revature.rms.search.services;

import com.revature.rms.search.clients.CampusClient;
import com.revature.rms.search.clients.EmployeeClient;
import com.revature.rms.search.dtos.BuildingDTO;
import com.revature.rms.search.dtos.EmployeeDto;
import com.revature.rms.search.dtos.ResourceMetadataDto;
import com.revature.rms.search.dtos.RoomDto;
import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.campus.ResourceMetadata;
import com.revature.rms.search.entites.campus.Room;
import com.revature.rms.search.entites.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService {
    private CampusClient campusClient;
    private EmployeeClient employeeClient;

    @Autowired
    public BuildingService(CampusClient campusClient, EmployeeClient employeeClient){
        this.campusClient = campusClient;
        this.employeeClient = employeeClient;
    }

    public BuildingDTO getBuildingDTOById(String id){
        Building building = campusClient.getBuildingById(id);
        BuildingDTO buildingDTO = building.extractBuildingDTO();
        Employee trainingLead = employeeClient.getEmployeeById(building.getTrainingLead());
        EmployeeDto trainingLeadAsDto = trainingLead.extractEmployeeDto();
        buildingDTO.setTrainingLead(trainingLeadAsDto);
        buildingDTO.setResourceMetadata(campusMetaData(building.getResourceMetadata()));

        return buildingDTO;
    }

    public List<BuildingDTO> getAllBuildingDTOs(){
        List<Building> buildings = campusClient.getAllBuildings();
        List<BuildingDTO> buildingDTOList = getEachBuildingMeta(buildings);
        return buildingDTOList;
    }

    public EmployeeDto getEmployeeById(int id) {
        Employee emp = employeeClient.getEmployeeById(id);
        EmployeeDto employeeDto = emp.extractEmployeeDto();
        return employeeDto;
    }

    public ResourceMetadataDto campusMetaData(ResourceMetadata data) {
        ResourceMetadataDto dto = data.extractCampusMeta();
        dto.setResourceCreator(getEmployeeById(data.getResourceCreator()));
        dto.setLastModifier(getEmployeeById(data.getLastModifier()));
        dto.setResourceCreator(getEmployeeById(data.getResourceOwner()));
        return dto;
    }
    public List<BuildingDTO> getEachBuildingMeta(List<Building> buildings) {
        List<BuildingDTO> buildingDTOS = new ArrayList<>();
        buildings.forEach(b -> buildingDTOS.add(b.extractBuildingDTO()));
        for(int i = 0; i < buildings.size(); i++){
            Building building = buildings.get(i);
            buildingDTOS.get(i).setResourceMetadata(campusMetaData(building.getResourceMetadata()));
        }
        return buildingDTOS;
    }

    public List<RoomDto> getEachRoomMeta(List<Room> rooms) {
        List<RoomDto> roomDtos = new ArrayList<>();
        rooms.forEach(b -> roomDtos.add(b.extractRoomDto()));
        for(int i = 0; i < rooms.size(); i++){
            Room room = rooms.get(i);
            roomDtos.get(i).setResourceMetadata(campusMetaData(room.getResourceMetadata()));
        }
        return roomDtos;
    }


}
