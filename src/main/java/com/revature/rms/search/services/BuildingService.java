package com.revature.rms.search.services;

import com.revature.rms.search.clients.CampusClient;
import com.revature.rms.search.clients.EmployeeClient;
import com.revature.rms.search.dtos.BuildingDto;
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
//    private CampusClient campusClient;
//    private EmployeeClient employeeClient;
//
//    @Autowired
//    public BuildingService(CampusClient campusClient, EmployeeClient employeeClient){
//        this.campusClient = campusClient;
//        this.employeeClient = employeeClient;
//    }
//
//    public BuildingDto getBuildingDTOById(String id){
//        Building building = campusClient.getBuildingById(id);
//        BuildingDto buildingDTO = building.extractBuildingDTO();
//        Employee trainingLead = employeeClient.getEmployeeById(building.getTrainingLead());
//        EmployeeDto trainingLeadAsDto = trainingLead.extractEmployeeDto();
//        buildingDTO.setTrainingLead(trainingLeadAsDto);
//        buildingDTO.setResourceMetadata(campusMetaData(building.getResourceMetadata()));
//
//        return buildingDTO;
//    }
//
//    public List<BuildingDto> getAllBuildingDTOs(){
//        List<Building> buildings = campusClient.getAllBuildings();
//        List<BuildingDto> buildingDtoList = getEachBuildingMeta(buildings);
//        return buildingDtoList;
//    }
//
//    public EmployeeDto getEmployeeById(int id) {
//        Employee emp = employeeClient.getEmployeeById(id);
//        EmployeeDto employeeDto = emp.extractEmployeeDto();
//        return employeeDto;
//    }
//
//    public ResourceMetadataDto campusMetaData(ResourceMetadata data) {
//        ResourceMetadataDto dto = data.extractCampusMeta();
//        dto.setResourceCreator(getEmployeeById(data.getResourceCreator()));
//        dto.setLastModifier(getEmployeeById(data.getLastModifier()));
//        dto.setResourceCreator(getEmployeeById(data.getResourceOwner()));
//        return dto;
//    }
//    public List<BuildingDto> getEachBuildingMeta(List<Building> buildings) {
//        List<BuildingDto> buildingDtos = new ArrayList<>();
//        buildings.forEach(b -> buildingDtos.add(b.extractBuildingDTO()));
//        for(int i = 0; i < buildings.size(); i++){
//            Building building = buildings.get(i);
//            buildingDtos.get(i).setResourceMetadata(campusMetaData(building.getResourceMetadata()));
//        }
//        return buildingDtos;
//    }
//
//    public List<RoomDto> getEachRoomMeta(List<Room> rooms) {
//        List<RoomDto> roomDtos = new ArrayList<>();
//        rooms.forEach(b -> roomDtos.add(b.extractRoomDto()));
//        for(int i = 0; i < rooms.size(); i++){
//            Room room = rooms.get(i);
//            roomDtos.get(i).setResourceMetadata(campusMetaData(room.getResourceMetadata()));
//        }
//        return roomDtos;
//    }


}
