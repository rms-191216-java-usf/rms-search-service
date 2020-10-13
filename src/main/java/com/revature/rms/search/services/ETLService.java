package com.revature.rms.search.services;

import com.revature.rms.search.clients.AuthClient;
import com.revature.rms.search.clients.CampusClient;
import com.revature.rms.search.clients.EmployeeClient;
import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.batch.Batch;
import com.revature.rms.search.entites.campus.*;
import com.revature.rms.search.entites.common.ResourceMetadata;
import com.revature.rms.search.entites.employee.AppUser;
import com.revature.rms.search.entites.employee.Employee;
import com.revature.rms.search.entites.workorder.WorkOrder;
import com.revature.rms.search.exceptions.InvalidRequestException;
import com.revature.rms.search.exceptions.ResourceNotFoundException;
import com.revature.rms.search.repositories.BatchRepository;
import com.revature.rms.search.repositories.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * In the first iteration of this service, we tried
 * to make each phase of the transformation process
 * as readable as possible. To make it a easier to
 * keep track of how it was achieved, we grouped each
 * set of the methods that were related to each other
 * together so that there was less jumping around to
 * see what each method took care of. Orchestrating
 * the calls to each service was carefully planned
 * to ensure that no pieces were missing before
 * delivering the final DTO to the front-end.
 * More validation will be needed to account for empty
 * or null objects that may be received from the other
 * services.
 * */
@Service
public class ETLService {

  private AuthClient authClient;
  private EmployeeClient empClient;
  private CampusClient campClient;
  private WorkOrderRepository workRepo;
  private BatchRepository batchRepo;

  /**
   * The repositories will need to be changed
   * to the feign clients in the next sprint
   * */
  @Autowired
  public ETLService(
      EmployeeClient employeeClient,
      CampusClient campusClient,
      WorkOrderRepository workOrderRepository,
      BatchRepository batchRepository,
      AuthClient authClient) {
    super();
    this.empClient = employeeClient;
    this.campClient = campusClient;
    this.workRepo = workOrderRepository;
    this.batchRepo = batchRepository;
    this.authClient = authClient;
  }

  //****************************** Campus Services ********************************************
  /**
   * getAllCampuses method: Returns all CampusDto object with all nested objects
   * @param
   * @return a list of CampusDto objects
   * @throws InvalidRequestException when a bad request is made
   */
  public List<CampusDto> getAllCampuses() {
    List<CampusDto> dtos = new ArrayList<>();
    try {
      List<Campus> campuses = campClient.getAllCampus();
      campuses.forEach(c -> dtos.add(getCampusDto(c)));
      if (campuses.isEmpty()){
        throw new ResourceNotFoundException("No Campuses found");
      }
    } catch (ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No Campuses found");
    } catch (Exception e){
      e.printStackTrace();
    }
    return dtos;
  }

  /**
   * getAllCampusesByTrainingManagerId method: Returns a list of CampusDto objects with all nested objects by the campus' Training Manager ID
   * @param id
   * @return a list of CampusDto objects
   * @throws InvalidRequestException when a bad request is made
   */
  public List<CampusDto> getAllCampusesByTrainingManagerId(int id) {
    List<CampusDto> dtos = new ArrayList<>();
    if (id < 1){
      throw new InvalidRequestException("Id can not be below 1");
    }
    try {
      List<Campus> campuses = campClient.getCampusByTrainingManagerId(id);
      campuses.forEach(c -> dtos.add(getCampusDto(c)));
      if (campuses.isEmpty()){
        throw new ResourceNotFoundException("No campuses found with id: " + id);
      }
    } catch(ResourceNotFoundException rnfe) {
      throw new ResourceNotFoundException("No campuses found with id: " + id);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    if(dtos.size() == 0){
      throw new ResourceNotFoundException("Resource not found!");
    }
    return dtos;
  }

  /**
   * getAllCampusByOwner method: Returns all Campus belonging to the specified owner
   * @param id
   * @return a list of CampusDto objects
   */
  public List<CampusDto> getAllCampusesByOwnerId(int id) {
    List<CampusDto> dtos = new ArrayList<>();
    if (id < 1){
      throw new InvalidRequestException("Owner id can not be below 1");
    }
    try {
      List<Campus> campuses = campClient.getAllCampusByOwner(id);
      campuses.forEach(c -> dtos.add(getCampusDto(c)));
      if (campuses.isEmpty()){
        throw new ResourceNotFoundException("No campuses found with id: " + id);
      }
    } catch (ResourceNotFoundException rnfe) {
      throw new ResourceNotFoundException("No campuses found with id: " + id);
    } catch (Exception e) {
     e.printStackTrace();
    }
    return dtos;
  }

  /**
   * getCampusDto method: Returns a CampusDto object with all nested objects after recieving a campus object without nested objects complete
   * @param campus
   * @return a CampusDto object
   * @throws ResourceNotFoundException building or meta data is not found
   */
  public CampusDto getCampusDto(Campus campus) {
    CampusDto dto = getCampusObjects(campus);
    try{
      dto.setBuildings(getListOfBuildingsData(campus.getBuildings()));
      dto.setCorporateEmployees(
              getEachEmployeeMeta(empClient.getEmployeesByIds(campus.getCorporateEmployees())));
    }catch(Exception e){
      throw new ResourceNotFoundException("Resource not found!");
    }
    return dto;
  }

  /**
   * getCampusDtoById method: Returns a CampusDto object with all nested objects
   * @param id
   * @return a CampusDto object
   * @throws ResourceNotFoundException when the campus, buildings or metadata cannot be found
   */
  public CampusDto getCampusDtoById(int id) {
    CampusDto campusDto = new CampusDto();
    try {
      Campus campus = campClient.getCampusById(id);
      if (campus == null){
        throw new ResourceNotFoundException();
      }
      campusDto = getCampusObjects(campus);
      campusDto.setBuildings(getListOfBuildingsData(campus.getBuildings()));
      campusDto.setCorporateEmployees(
              getEachEmployeeMeta(empClient.getEmployeesByIds(campus.getCorporateEmployees())));
    }catch(ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
    } catch (Exception e){
      e.printStackTrace();
    }
    return campusDto;
  }

  /**
   * getCampusObjects method: Returns a CampusDto object with all nested objects after recieving a campus object without nested objects complete
   * @param campus
   * @return a CampusDto object
   * @throws ResourceNotFoundException building or meta data is not found
   */
  public CampusDto getCampusObjects(Campus campus) {
    CampusDto dto = campus.extractCampus();
    try {
      dto.setTrainingManager(getEmployeeById(campus.getTrainingManagerId()));
      dto.setStagingManager(getEmployeeById(campus.getStagingManagerId()));
      dto.setHrLead(getEmployeeById(campus.getHrLead()));
      dto.setResourceMetadata(campusMetaData(campus.getResourceMetadata()));
    }catch(Exception e) {
      throw new ResourceNotFoundException("Resource not found!");
    }
    return dto;
  }

  /**
   * campusMetaData method: Returns a ResourceMetadataDto object with all nested objects after receiving a campus object without nested objects complete
   * @param data
   * @return a ResourceMetadataDto object
   * @throws ResourceNotFoundException if metadata is not found
   */
  public ResourceMetadataDto campusMetaData(ResourceMetadata data) {
    ResourceMetadataDto dto = data.extractResourceMetadata();
    try {
      dto.setResourceCreator(getAppUserById(data.getResourceCreator()));
      dto.setLastModifier(getAppUserById(data.getLastModifier()));
      dto.setResourceOwner(getAppUserById(data.getResourceOwner()));
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
    }
    return dto;
  }

  /**
   * getAllBuilding method: Returns all BuildingDto objects
   * @return a list of all BuildingDto objects
   */

  public List<BuildingDto> getAllBuildings(){
    List<BuildingDto> dtos = new ArrayList<>();
    try {
      List<Building> buildings = campClient.getAllBuildings();
      if (buildings.isEmpty()){
        throw new ResourceNotFoundException();
      }
      dtos = getListOfBuildingsData(buildings);
    } catch (ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No buildings were found");
    } catch (Exception e){
      e.printStackTrace();
    }
    return dtos;
  }

  /**
   * getListOfBuildingsData method: Returns all BuildingDto object with all nested objects
   * @param buildings
   * @return a list of BuildingDto objects
   * @throws ResourceNotFoundException if no building object is found
   */
  public List<BuildingDto> getListOfBuildingsData(List<Building> buildings){
    List<BuildingDto> buildingDtos = new ArrayList<>();
    try {
      buildings.forEach(b -> buildingDtos.add(b.extractBuilding()));
      for (int i = 0; i < buildings.size(); i++) {
        Building building = buildings.get(i);
        buildingDtos.get(i).setTrainingLead(getEmployeeById(building.getTrainingLead()));
        buildingDtos.get(i).setRooms(getEachRoomMeta(building.getRooms()));
        if(building.getResourceMetadata() != null){
          buildingDtos.get(i).setResourceMetadata(campusMetaData(building.getResourceMetadata()));
        }
      }
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
    }
    return buildingDtos;
  }

  /**
   * getBuildingDtoById method: Returns a BuildingDto object with all nested objects
   * @param id
   * @return a  BuildingDto object
   * @throws ResourceNotFoundException when the campus, buildings or metadata cannot be found
   */
  public BuildingDto getBuildingDtoById(int id) {
    if (id < 1){
      throw new InvalidRequestException("Id must be 1 or above");
    }
    try {
      Building building = campClient.getBuildingById(id);
      if (building == null){
        throw new ResourceNotFoundException();
      }
      return getBuildingData(building);
    }catch(ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("Building not found with id: " + id);
    } catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  /**
   * getBuildingDtoByTrainingLeadId method: Returns a BuildingDto object with all nested objects
   * by searching for the building Training Lead ID.
   * @param id
   * @return a  BuildingDto object
   * @throws ResourceNotFoundException when the campus, buildings or metadata cannot be found
   */
  public BuildingDto getBuildingDtoByTrainingLeadId(int id) {
    if (id < 1){
      throw new InvalidRequestException("Id must be 1 or above");
    }
    try {
      Building building = campClient.getBuildingByTrainingLeadId(id);
      if (building == null){
        throw new ResourceNotFoundException();
      }
      return getBuildingData(building);
    }catch(ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("Building not found with id: " + id);
    } catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  /**
   * getAllBuildingByOwner method: Returns a list of building based on a provided app user id
   * @param id
   * @return a list of BuildingDto objects
   */
  public List<BuildingDto> getAllBuildingsByOwner(int id) {
    if (id < 1){
      throw new InvalidRequestException("Id must be 1 or above");
    }
    List<BuildingDto> dtos = new ArrayList<>();
    try {
      List<Building> buildings = campClient.getAllBuildingsByOwner(id);
      if (buildings.isEmpty()){
        throw new ResourceNotFoundException();
      }
      buildings.forEach(b -> dtos.add(getBuildingData(b)));
    } catch (ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No buildings found with that owner");
    } catch (Exception e){
      e.printStackTrace();
    }
    return dtos;
  }

  /**
   * getBuildingData method: Returns a CampusDto object with all nested objects
   * @param building
   * @return a BuildingDto object
   * @throws ResourceNotFoundException when the BuildingDto cannot be found
   */
  public BuildingDto getBuildingData(Building building) {
    BuildingDto dto = building.extractBuilding();
    try {
      dto.setTrainingLead(getEmployeeById(building.getTrainingLead()));
      dto.setRooms(getEachRoomMeta(building.getRooms()));
      // Campus object received from campus service returned null metadata for the building objects
        // so I put this validation in here to just skip over it. Will need to investigate why this is happening
      if(building.getResourceMetadata() != null){
        dto.setResourceMetadata(campusMetaData(building.getResourceMetadata()));
      }
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
    }
    return dto;
  }

  /**
   *  getAllRooms method: Returns a list of all RoomDto objects
   * @return a list of all RoomDto objects
   */
  public List<RoomDto> getAllRooms() {
    List<RoomDto> dtos = new ArrayList<>();
    try {
      List<Room> rooms = campClient.getAllRooms();
      if (rooms.isEmpty()){
        throw new ResourceNotFoundException();
      }
      dtos = getEachRoomMeta(rooms);
    } catch (ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No rooms were found");
    } catch (Exception e){
      e.printStackTrace();
    }
    return dtos;
  }

  /**
   * getRoomDtoById method: Returns a RoomDto object with all nested objects
   * @param id
   * @return a RoomDto object
   * @throws ResourceNotFoundException when the RoomDto cannot be found
   */
  public RoomDto getRoomDtoById(int id) {
    if (id < 1){
      throw new InvalidRequestException("Id must be 1 or above");
    }
    RoomDto roomDto = new RoomDto();
    try {
      Room room = campClient.getRoomById(id);
      Room nullRoom = new Room();
      if (room == null|| room.equals(nullRoom)){
        throw new ResourceNotFoundException();
      }
      roomDto = room.extractRoom();
      List<RoomStatusDto> roomStatusList = getEmpsFromRoomStatus(room.getCurrentStatus());
      roomDto.setCurrentStatus(roomStatusList);
      BatchDto batch = getBatchInfo(getBatchById(room.getBatchId()));
      roomDto.setBatch(batch);
      List<WorkOrderDto> workOrderList= getEachWorkOrderInfo(room.getWorkOrders());
      roomDto.setWorkOrders(workOrderList);
      roomDto.setResourceMetadata(campusMetaData(room.getResourceMetadata()));
    }catch(ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No room was found with id: " + id);
    } catch (Exception e){
      e.printStackTrace();
    }
    return roomDto;
  }

  /**
   * getRoomDtoByTrainerId method: Grabs all rooms and populates all nested objects and then iterates
   * to find the room with the given Trainer ID
   * @param id
   * @return a RoomDto object
   * @throws ResourceNotFoundException when the RoomDto cannot be found
   */
  //TODO: refactor to reduce memory footprint
  public RoomDto getRoomDtoByTrainerId(int id) {
    if (id < 1){
      throw new InvalidRequestException("Id must be 1 or above");
    }
    RoomDto result = new RoomDto();
    try {
      List<Room> rooms = campClient.getAllRooms();
      List<RoomDto> roomDtos = rooms.stream().map(Room::extractRoom).collect(Collectors.toList());
      for (int i = 0; i < rooms.size() ; i++){
        List<RoomStatusDto> roomStatusDtos = getEmpsFromRoomStatus(rooms.get(i).getCurrentStatus());
        roomDtos.get(i).setCurrentStatus(roomStatusDtos);
        BatchDto batch = getBatchInfo(getBatchById(rooms.get(i).getBatchId()));
        roomDtos.get(i).setBatch(batch);
        List<WorkOrderDto> workOrderList= getEachWorkOrderInfo(rooms.get(i).getWorkOrders());
        roomDtos.get(i).setWorkOrders(workOrderList);
        roomDtos.get(i).setResourceMetadata(campusMetaData(rooms.get(i).getResourceMetadata()));
      }
      for (RoomDto rDto: roomDtos) {
        if(rDto.getBatch().getTrainer().getId() == id) {
          result = rDto;
        }
      }
      if (result == null ){
        throw new ResourceNotFoundException();
      }
    }catch(ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No room found with trainer id: " + id);
    } catch (Exception e){
      e.printStackTrace();
    }

    if(result.getId() == 0){
      throw new ResourceNotFoundException("No room found with trainer id: " + id);
    }
    return result;
  }

  /**
   *getAllRoomByOwner method: Returns a list of rooms associated with a give app user
   * @param id
   * @return a list of RoomDto Objects
   */
  public List<RoomDto> getAllRoomByOwner(int id) {
    if (id < 1){
      throw new InvalidRequestException("Id must be 1 or above");
    }
    List<RoomDto> dtos = new ArrayList<>();
    try{
      List<Room> rooms = campClient.getAllRoomByOwner(id);
      if (rooms.isEmpty()){
        throw new ResourceNotFoundException();
      }
      dtos = getEachRoomMeta(rooms);
    } catch (ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No rooms found with that owner");
    } catch (Exception e){
      e.printStackTrace();
    }
    return dtos;
  }

  /**
   * campusMetaData method: Returns list of  RoomDto object with all nested objects after receiving a campus object without nested objects complete
   * @param rooms
   * @return a RoomDto object
   * @throws ResourceNotFoundException if metadata is not found
   */
  public List<RoomDto> getEachRoomMeta(List<Room> rooms){
    List<RoomDto> roomDtos = new ArrayList<>();
    try {
      rooms.forEach(r -> roomDtos.add(r.extractRoom()));
      for (int i = 0; i < rooms.size(); i++) {
        Room room = rooms.get(i);
        roomDtos.get(i).setCurrentStatus(getEmpsFromRoomStatus(room.getCurrentStatus()));
        roomDtos.get(i).setResourceMetadata(campusMetaData(room.getResourceMetadata()));
        roomDtos.get(i).setBatch(getBatchInfo(getBatchById(room.getBatchId())));
        roomDtos.get(i).setWorkOrders(getEachWorkOrderInfo(room.getWorkOrders()));
      }
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
    }
    return roomDtos;
  }

  /**
   * getEmpsFromRoomStatus method: Returns all RoomStatusDto object with employee associated with RoomStatusDto
   * @param roomStatus
   * @return a list of RoomStatusDto objects
   * @throws ResourceNotFoundException if no RoomStatusDto object is found
   */
  public List<RoomStatusDto> getEmpsFromRoomStatus(List<RoomStatus> roomStatus) {
    List<RoomStatusDto> dtos = new ArrayList<>();
    try {
      for (int i = 0; i < roomStatus.size(); i++) {
        RoomStatus status = roomStatus.get(i);
        RoomStatusDto statusDto = status.extractRoomStatus();
        statusDto.setSubmitter(getEmployeeById(status.getSubmitterId()));
        dtos.add(statusDto);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
    }
    return dtos;
  }

  //****************************** Employee Services ********************************************
  /**
   * getAllEmployees method: Returns all EmployeeDto object with all nested objects
   * @param
   * @return a list of employee objects
   */
  public List<EmployeeDto> getAllEmployees() {
    List<EmployeeDto> employees = getEachEmployeeMeta(empClient.getAllEmployee());
    if (employees.isEmpty()){
      throw new ResourceNotFoundException("No Employees found");
    }
    return  employees;
  }

  /**
   * Although, this method looks exactly the same as the one
   * under it, it was necessary to create them separately to
   * avoid circular references made to the employee service.
   * */

  /**
   * getEmployeeDtoById method: Returns a EmployeeDto object with all nested objects
   * @param id
   * @return a list of employee objects
   * @throws ResourceNotFoundException if EmployeeDto object is not found
   */
  public EmployeeDto getEmployeeDtoById(int id) {
    if (id < 1){
      throw new InvalidRequestException("Id must be 1 or above");
    }
    EmployeeDto employeeDto = new EmployeeDto();
    try {
      Employee employee = empClient.getEmployeeById(id);
      if (employee == null){
        throw new ResourceNotFoundException();
      }
      employeeDto = employee.extractEmployee();
    }catch(ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No employee found with id: " + id);
    } catch (Exception e){
      e.printStackTrace();
    }
    return employeeDto;
  }

  /**
   * getEmployeeDtoById method: Set an EmployeeDto ResourceMetadata
   * @param id
   * @return an EmployeeDto object
   * @throws ResourceNotFoundException if ResourceMetadata object is not found
   */
  public EmployeeDto getEmployeeById(int id) {
    if (id < 1){
      throw new InvalidRequestException("Id must be 1 or above");
    }
    EmployeeDto dto = null;
    try {
      Employee emp = empClient.getEmployeeById(id);
      if (emp == null){
        throw new ResourceNotFoundException();
      }
      dto = emp.extractEmployee();
      dto.setResourceMetadata(getEmployeeMetadata(emp.getResourceMetadata()));
    }catch(ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No employee found with id: " + id);
    } catch (Exception e){
      e.printStackTrace();
    }
    return dto;
  }

  /**
   * getAllEmplyeeByOwner method: Returns a list of Employees resources owned by a provided app user
   * @param id
   * @return a list of EmployeeDto Objects
   */
  public List<EmployeeDto> getAllEmployeeByOwner(int id) {
    if (id < 1){
      throw new InvalidRequestException("Id must be 1 or above");
    }
    List<EmployeeDto> dtos = new ArrayList<>();
    try{
      List<Employee> employees = empClient.getAllEmployeeByOwner(id);
      if (employees.isEmpty()){
        throw new ResourceNotFoundException();
      }
      dtos = getEachEmployeeMeta(employees);
    } catch (ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No employee found with the owner id: " + id);
    } catch (Exception e){
      e.printStackTrace();
    }
    return dtos;
  }

  /**
   * getAppUserById Method: This method sends a request to the authClient to get an AppUser by its ID.
   * @param id int value for the specific AppUser you need to find.
   * @return Returns an AppUser Object.
   */
  public AppUser getAppUserById(int id) {
    if (id < 1){
      throw new InvalidRequestException("Id must be 1 or above");
    }
    AppUser user = null;
    try{
      user = authClient.getUserById(id);
      if (user == null){
        throw new ResourceNotFoundException();
      }
    }catch (ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No user found with id: " + id);
    } catch (Exception e){
      e.printStackTrace();
    }
    return user;
  }

  /**
   * getEachEmployeeMeta Method: This method converts the parameter to a List of EmployeeDto and iterates thru them populate each
   * ResourceMetadata of the EmployeeDto Object with their appropriate Objects and then set the ResourceMetadata to its respective parent Object.
   * @param employees List of type Employee
   * @return Returns a List of type EmployeeDto
   */
  public List<EmployeeDto> getEachEmployeeMeta(List<Employee> employees){
    List<EmployeeDto> empDtos = new ArrayList<>();
    try {
      employees.forEach(e -> empDtos.add(e.extractEmployee()));
      for (int i = 0; i < employees.size(); i++) {
        Employee emp = employees.get(i);
        empDtos.get(i).setResourceMetadata(getEmployeeMetadata(emp.getResourceMetadata()));
      }
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
    }
    return empDtos;
  }

  /**
   * getEmployeeMetadata method: Returns a ResourceMetadataDto object with all nested objects after receiving an employee object without nested objects complete
   * @param data employee.ResourceMetadata Object.
   * @return a resource metadata object.
   * @throws ResourceNotFoundException if metadata is not found.
   */
  public ResourceMetadataDto getEmployeeMetadata(
      com.revature.rms.search.entites.employee.ResourceMetadata data) {
    ResourceMetadataDto dto = data.extractEmployeeMeta();
    try {
      dto.setResourceCreator(getAppUserById(data.getResourceCreator()));
      dto.setLastModifier(getAppUserById(data.getLastModifier()));
      dto.setResourceOwner(getAppUserById(data.getResourceOwner()));
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
    }
    return dto;
  }

  //****************************** Work Order Services ********************************************
  /**
   * These methods will need to be updated when
   * batch service and work order service are
   * completed.
   * */
  @Transactional
  public WorkOrder getWorkOrderById(int id) {
    if (id < 1){
      throw new InvalidRequestException("Id must be 1 or above");
    }
    WorkOrder w = new WorkOrder();
    try {
      Optional<WorkOrder> workOrder = workRepo.findById(id);
      if (workOrder.isPresent()) {
        w = workOrder.get();
      } else {
        throw new ResourceNotFoundException();
      }
    }catch(ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No work order found with id: " + id);
    }catch(Exception e){
      e.printStackTrace();
    }
    return w;
  }

  /**
   * getEachWorkOrderInfo Method: This method Iterates thru each id on the parameter List and grabs their respective workOrder Objects,
   * extracts the information of the workOrder object to be replace with a workOrderDto and then grabs each employee that
   * needs to be populated for that workOrder object.
   * @param ids List of Integers
   * @return Returns a List of complete WorkOrderDto Objects.
   */
  public List<WorkOrderDto> getEachWorkOrderInfo(List<Integer> ids) {
    for (Integer id: ids) {
      if (id < 1){
        throw new InvalidRequestException("Id must be 1 or above");
      }
    }
    List<WorkOrderDto> dtos = new ArrayList<>();
    try {
      List<WorkOrder> workOrders = new ArrayList<>();
      ids.forEach(i -> workOrders.add(getWorkOrderById(i)));
      for (int j = 0; j < workOrders.size(); j++) {
        WorkOrderDto dto = workOrders.get(j).extractWorkOrder();
        dto.setCreator(getEmployeeById(workOrders.get(j).getCreatorId()));
        dto.setResolver(getEmployeeById(workOrders.get(j).getResolverId()));
        dtos.add(dto);
      }
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
    }
    return dtos;
  }

  //****************************** Batch Services ********************************************

  /**
   * findBatchById Method: This method talks to the batchRepository to find a Batch Object by its ID.
   * @param id int of the batch id
   * @return Returns a Batch Object.
   */
  @Transactional
  public Batch getBatchById(int id) {
    if (id < 1) {
      throw new InvalidRequestException("Id must be 1 or above");
    }
    Batch b = new Batch();
    try {
      Optional<Batch> batch = batchRepo.findById(id);
      if (batch.isPresent()) {
        b = batch.get();
      } else {
        throw new ResourceNotFoundException();
      }
    }catch(ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No batch found with id: " + id);
    } catch (Exception e){
      e.printStackTrace();
    }
    return b;
  }

  /**
   * getBatchInfo Method: This method extracts the information of the Batch object to be replace with a BatchDto and then grabs each employee that
   * needs to be populated for that Batch object and the respective ResourceMetadata.
   * @param batch Batch Object Type.
   * @return Returns a complete BatchDto Object.
   */
  public BatchDto getBatchInfo(Batch batch) {
    BatchDto dto = batch.extractBatch();
    try {
      dto.setTrainer(getEmployeeById(batch.getTrainerId()));
      if (batch.getCoTrainerId() != 0) {
        dto.setCoTrainer(getEmployeeById(batch.getCoTrainerId()));
      }
      dto.setAssociates(getEachEmployeeMeta(empClient.getEmployeesByIds(batch.getAssociates())));
      if (dto.getAssociates() == null){
        throw new ResourceNotFoundException();
      }
      dto.setResourceMetadata(campusMetaData(batch.getResourceMetadata()));
    }catch(ResourceNotFoundException rnfe) {
      rnfe.printStackTrace();
      throw new ResourceNotFoundException("No employees are part of this batch");
    }catch (Exception e){
      e.printStackTrace();
    }
    return dto;
  }
}
