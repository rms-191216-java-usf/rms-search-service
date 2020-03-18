package com.revature.rms.search.services;

import com.revature.rms.search.clients.CampusClient;
import com.revature.rms.search.clients.EmployeeClient;
import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.batch.Batch;
import com.revature.rms.search.entites.campus.*;
import com.revature.rms.search.entites.common.ResourceMetadata;
import com.revature.rms.search.entites.employee.Employee;
import com.revature.rms.search.entites.workorder.WorkOrder;
import com.revature.rms.search.exceptions.InvalidRequestException;
import com.revature.rms.search.repositories.BatchRepository;
import com.revature.rms.search.repositories.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ETLService {

  private EmployeeClient empClient;
  private CampusClient campClient;
  private WorkOrderRepository workRepo;
  private BatchRepository batchRepo;

  @Autowired
  public ETLService(
      EmployeeClient employeeClient,
      CampusClient campusClient,
      WorkOrderRepository workOrderRepository,
      BatchRepository batchRepository) {
    super();
    this.empClient = employeeClient;
    this.campClient = campusClient;
    this.workRepo = workOrderRepository;
    this.batchRepo = batchRepository;
  }

  public List<CampusDto> getAllCampuses() {
    List<CampusDto> dtos = new ArrayList<>();
    try {
      List<Campus> campuses = campClient.getAllCampus();
      campuses.forEach(c -> dtos.add(getCampusDto(c)));
    } catch (Exception e) {
      e.getStackTrace();
      throw new InvalidRequestException("Bad request made!");
    }
    return dtos;
  }

  public CampusDto getCampusDto(Campus campus) {
    CampusDto dto = getCampusObjects(campus);
    dto.setBuildings(getListOfBuildingsData(campus.getBuildings()));
    dto.setCorporateEmployees(
        getEachEmployeeMeta(empClient.getAllById(campus.getCorporateEmployees())));
    return dto;
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
      statusDto.setSubmitter(getEmployeeById(status.getSubmitterId()));
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
    ResourceMetadataDto dto = data.extractResourceMetadata();
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
      roomDtos.get(i).setBatch(getBatchInfo(findBatchById(room.getBatchId())));
      roomDtos.get(i).setWorkOrders(getEachWorkOrderInfo(room.getWorkOrders()));
    }
    return roomDtos;
  }

  public WorkOrder getWorkOrderById(String id) {
    Optional<WorkOrder> workOrder = workRepo.findById(id);
    WorkOrder w = new WorkOrder();
    if (workOrder.isPresent()) {
      w = workOrder.get();
    } else {
      return w;
    }
    return w;
  }

  public List<WorkOrderDto> getEachWorkOrderInfo(List<String> ids) {
    List<WorkOrderDto> dtos = new ArrayList<>();
    List<WorkOrder> workOrders = new ArrayList<>();
    ids.forEach(i -> workOrders.add(getWorkOrderById(i)));
    for (int j = 0; j < workOrders.size(); j++) {
      WorkOrderDto dto = workOrders.get(j).extractWorkOrder();
      dto.setCreator(getEmployeeById(workOrders.get(j).getCreatorId()));
      dto.setResolver(getEmployeeById(workOrders.get(j).getResolverId()));
      dtos.add(dto);
    }
    return dtos;
  }

  public Batch findBatchById(String id) {
    Optional<Batch> batch = batchRepo.findById(id);
    Batch b = new Batch();
    if (batch.isPresent()) {
      b = batch.get();
    } else {
      return b;
    }
    return b;
  }

  public BatchDto getBatchInfo(Batch batch) {
    BatchDto dto = batch.extractBatch();
    dto.setTrainer(getEmployeeById(batch.getTrainerId()));
    if (batch.getCoTrainerId() != 0) {
      dto.setCoTrainer(getEmployeeById(batch.getCoTrainerId()));
    }
    dto.setAssociates(getEachEmployeeMeta(empClient.getAllById(batch.getAssociates())));
    dto.setResourceMetadata(campusMetaData(batch.getResourceMetadata()));
    return dto;
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
