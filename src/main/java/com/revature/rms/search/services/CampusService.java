package com.revature.rms.search.services;

import com.revature.rms.search.clients.CampusClient;
import com.revature.rms.search.clients.EmployeeClient;
import com.revature.rms.search.dtos.BuildingDTO;
import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;

public class CampusService {

  private EmployeeClient empClient;
  private CampusClient campClient;

  @Autowired
  public CampusService(EmployeeClient employeeClient, CampusClient campusClient) {
    this.empClient = employeeClient;
    this.campClient = campusClient;
  }

  public BuildingDTO getBuildingById(int id) {

    Building building = campClient.getBuildingById(id);
    Employee emp = getEmployeeById(building.getTrainerLead);
    BuildingDTO buildingDto = building.extractBuilding();
    buildingDto.setTrainingLead(emp);
    return buildingDto;
  }

  public Employee getEmployeeById(int id) {
    Employee emp = employeeClient.getEmployeeById(id);

    return emp;
  }

  // Example extract method
  // This method would go into the Building POJO
//  public BuildingDTO extractBuilding() {
//    return new BuildingDTO(
//        this.id,
//        this.name,
//        this.abbrName,
//        this.physicalAddress,
//        this.amenities,
//        this.rooms,
//        this.resourceMetadata);
//  }
}

