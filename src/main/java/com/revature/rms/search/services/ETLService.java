package com.revature.rms.search.services;

import com.revature.rms.search.clients.CampusClient;
import com.revature.rms.search.clients.EmployeeClient;
import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.campus.*;
import com.revature.rms.search.entites.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ETLService {

  private EmployeeClient empClient;
  private CampusClient campClient;

  @Autowired
  public ETLService(EmployeeClient employeeClient, CampusClient campusClient) {
    super();
    this.empClient = employeeClient;
    this.campClient = campusClient;
  }

  public List<CampusDto> getAllCampuses() {
    List<Campus> campuses = campClient.getAllCampus();
    List<CampusDto> dtos = new ArrayList<>();
    campuses.forEach(c -> dtos.add(getCampusDtoById(c.getId())));

    return dtos;
  }

  public CampusDto getCampusDtoById(String id) {
    Campus campus = campClient.getCampusById(id);
    CampusDto campusDto = getCampusObjects(campus);
    campusDto.setBuildings(getListOfBuildingsData(campus.getBuildings()));
    campusDto.setCorporateEmployees(
        getEachEmployeeMeta(empClient.getAllById(campus.getCorporateEmployees())));
    return campusDto;
  }

  public BuildingDto getBuildingDtoById(String id) {
    Building building = campClient.getBuildingById(id);
    return getBuildingData(building);
  }

  public RoomDto getRoomDtoById(String id) {
    Room room = campClient.getRoomById(id);
    RoomDto roomDto = room.extractRoom();
    roomDto.setResourceMetadata(campusMetaData(room.getResourceMetadata()));
    return roomDto;
  }

  public List<EmployeeDto> getAllEmployees() {
    return getEachEmployeeMeta(empClient.getAllEmployee());
  }

  public EmployeeDto getEmployeeDtoById(int id) {
    Employee employee = empClient.getEmployeeById(id);
    EmployeeDto employeeDto = employee.extractEmployee();
    employeeDto.setResourceMetadata(getEmployeeMetadata(employee.getResourceMetadata()));
    return employeeDto;
  }

  public EmployeeDto getEmployeeById(int id) {
    Employee emp = empClient.getEmployeeById(id);
    EmployeeDto dto = emp.extractEmployee();
    dto.setResourceMetadata(getEmployeeMetadata(emp.getResourceMetadata()));

    return dto;
  }

  public BuildingDto getBuildingData(Building building) {
    BuildingDto dto = building.extractBuilding();
    dto.setTrainingLead(getEmployeeById(building.getTrainingLead()));
    dto.setRooms(getEachRoomMeta(building.getRooms()));
    dto.setResourceMetadata(campusMetaData(building.getResourceMetadata()));
    return dto;
  }

  public List<RoomStatusDto> getEmpsFromRoomStatus(List<RoomStatus> roomStatus) {
    List<RoomStatusDto> dtos = new ArrayList<>();
    for (int i = 0; i < roomStatus.size(); i++) {
      RoomStatus status = roomStatus.get(i);
      RoomStatusDto statusDto = status.extractRoomStatus();
      statusDto.setSubmitter(getEmployeeById(status.getId()));
      dtos.add(statusDto);
    }

    return dtos;
  }

  public List<BuildingDto> getListOfBuildingsData(List<Building> buildings) {
    List<BuildingDto> buildingDtos = new ArrayList<>();
    for (int i = 0; i < buildings.size(); i++) {
      Building building = buildings.get(i);
      BuildingDto b = building.extractBuilding();
      b.setTrainingLead(getEmployeeById(building.getTrainingLead()));
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

  public ResourceMetadataDto getEmployeeMetadata(
      com.revature.rms.search.entites.employee.ResourceMetadata data) {
    ResourceMetadataDto dto = data.extractEmployeeMeta();
    dto.setResourceCreator(getEmployeeById(data.getResourceCreator()));
    dto.setLastModifier(getEmployeeById(data.getLastModifier()));
    dto.setResourceOwner(getEmployeeById(data.getResourceOwner()));

    return dto;
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
    for (int i = 0; i < rooms.size(); i++) {
      Room room = rooms.get(i);
      roomDtos.get(i).setCurrentStatus(getEmpsFromRoomStatus(room.getRoomStatus()));
      roomDtos.get(i).setResourceMetadata(campusMetaData(room.getResourceMetadata()));
    }
    return roomDtos;
  }

  public List<EmployeeDto> getEachEmployeeMeta(List<Employee> employees) {
    List<EmployeeDto> empDtos = new ArrayList<>();
    employees.forEach(e -> empDtos.add(e.extractEmployee()));
    for (int i = 0; i < employees.size(); i++) {
      Employee emp = employees.get(i);
      empDtos.get(i).setResourceMetadata(getEmployeeMetadata(emp.getResourceMetadata()));
    }
    return empDtos;
  }
}
