package com.revature.rms.search.services;

import com.revature.rms.search.clients.CampusClient;
import com.revature.rms.search.clients.EmployeeClient;
import com.revature.rms.search.dtos.BuildingDTO;
import com.revature.rms.search.dtos.RoomDto;
import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.campus.Room;
import com.revature.rms.search.entites.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
        buildingDTO.setTrainingLead(trainingLead);
        return buildingDTO;
    }

    public List<BuildingDTO> getAllBuildingDTOs(){
        List<Building> buildings = campusClient.getAllBuildings();
        List<BuildingDTO> buildingDTOList = new ArrayList<>();

        Employee trainingLead;
        Integer counter = 0;
        for (Building b:
             buildings) {
            BuildingDTO buildingDTO = b.extractBuildingDTO();
            trainingLead = employeeClient.getEmployeeById(b.getTrainingLead());
            buildingDTO.setTrainingLead(trainingLead);
            buildingDTOList.add(buildingDTO);
        }
        return buildingDTOList;
    }



}
