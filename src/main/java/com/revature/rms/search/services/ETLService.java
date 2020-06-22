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
    } catch (Exception e) {
      e.printStackTrace();
      throw new InvalidRequestException("Bad request made!");
    }
    return dtos;
  }

  /**
   * getAllCampusByOwner method: Returns all Campus belonging to the specified owner
   * @param id
   * @return a list of Campus objects
   */
  public List<CampusDto> getAllCampusByOwner(int id) {
    List<CampusDto> dtos = new ArrayList<>();
    try {
      List<Campus> campuses = campClient.getAllCampusByOwner(id);
      campuses.forEach(c -> dtos.add(getCampusDto(c)));
    }catch (Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource Not Found");
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
              getEachEmployeeMeta(empClient.getAllById(campus.getCorporateEmployees())));
    }catch(Exception e){
      e.printStackTrace();
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
      campusDto = getCampusObjects(campus);
      campusDto.setBuildings(getListOfBuildingsData(campus.getBuildings()));
      campusDto.setCorporateEmployees(
              getEachEmployeeMeta(empClient.getAllById(campus.getCorporateEmployees())));
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
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
      e.printStackTrace();
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
      dto.setResourceCreator(getAppUserById(data.getResourceOwner()));
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
    }
    return dto;
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
    try {
      Building building = campClient.getBuildingById(id);
      return getBuildingData(building);
    }catch(Exception e) {
      throw new ResourceNotFoundException("Resource not found!");
    }
  }

  /**
   * getAllBuildingByOwner method: Returns a list of building based on a provided app user id
   * @param id
   * @return a list of Building objects
   */
  public List<BuildingDto> getAllBuildingsByOwner(int id) {
    List<BuildingDto> dtos = new ArrayList<>();
    try {
      List<Building> buildings = campClient.getAllBuildingsByOwner(id);
      buildings.forEach(b -> dtos.add(getBuildingData(b)));
    } catch (Exception e) {
      throw new ResourceNotFoundException("No Building Found");
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
   * getRoomDtoById method: Returns a RoomDto object with all nested objects
   * @param id
   * @return a RoomDto object
   * @throws ResourceNotFoundException when the RoomDto cannot be found
   */
  public RoomDto getRoomDtoById(int id) {
    RoomDto roomDto = new RoomDto();
    try {
      Room room = campClient.getRoomById(id);
      roomDto = room.extractRoom();
      List<RoomStatusDto> roomStatusList = getEmpsFromRoomStatus(room.getCurrentStatus());
      roomDto.setCurrentStatus(roomStatusList);
      BatchDto batch = getBatchInfo(findBatchById(room.getBatchId()));
      roomDto.setBatch(batch);
      List<WorkOrderDto> workOrderList= getEachWorkOrderInfo(room.getWorkOrders());
      roomDto.setWorkOrders(workOrderList);
      roomDto.setResourceMetadata(campusMetaData(room.getResourceMetadata()));
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
    }
    return roomDto;
  }

  /**
   *getAllRoomByOwner method: Returns a list of rooms associated with a give app user
   * @param id
   * @return a list of Room Objects
   */
  public List<Room> getAllRoomByOwner(int id) {
    List<Room> rooms;
    try{
      rooms = campClient.getAllRoomByOwner(id);
    } catch (Exception e) {
      throw new ResourceNotFoundException("No Rooms Found");
    }
    return rooms;
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
        roomDtos.get(i).setBatch(getBatchInfo(findBatchById(room.getBatchId())));
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
    return getEachEmployeeMeta(empClient.getAllEmployee());
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
    EmployeeDto employeeDto = new EmployeeDto();
    try {
      Employee employee = empClient.getEmployeeById(id);
      employeeDto = employee.extractEmployee();
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
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
    EmployeeDto dto;
    try {
      Employee emp = empClient.getEmployeeById(id);
      dto = emp.extractEmployee();
      dto.setResourceMetadata(getEmployeeMetadata(emp.getResourceMetadata()));
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
    }
    return dto;
  }

  /**
   * getAllEmplyeeByOwner method: Returns a list of Employees resources owned by a provided app user
   * @param id
   * @return a list of Employee Objects
   */
  public List<Employee> getAllEmployeeByOwner(int id) {
    List<Employee> employees;
    try{
      employees = empClient.getAllEmployeeByOwner(id);
    } catch (Exception e) {
      throw new ResourceNotFoundException("No Employee Found");
    }
    return employees;
  }

  /**
   * getAppUserById Method: This method sends a request to the authClient to get an AppUser by its ID.
   * @param id int value for the specific AppUser you need to find.
   * @return Returns an AppUser Object.
   */
  public AppUser getAppUserById(int id) {
    //AppUserDto appUserDto;
    AppUser user;
    try{
      user = authClient.getUserById(id);
      //appUserDto = new AppUserDto(user);
    }catch (Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource Not Found");
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
    WorkOrder w = new WorkOrder();
    try {
      Optional<WorkOrder> workOrder = workRepo.findById(id);
      if (workOrder.isPresent()) {
        w = workOrder.get();
      } else {
        return w;
      }
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
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
  public Batch findBatchById(int id) {
    Batch b = new Batch();
    try {
      Optional<Batch> batch = batchRepo.findById(id);
      if (batch.isPresent()) {
        b = batch.get();
      } else {
        return b;
      }
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
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
      dto.setAssociates(getEachEmployeeMeta(empClient.getAllById(batch.getAssociates())));
      dto.setResourceMetadata(campusMetaData(batch.getResourceMetadata()));
    }catch(Exception e) {
      e.printStackTrace();
      throw new ResourceNotFoundException("Resource not found!");
    }
    return dto;
  }
}
