package com.revature.rms.search.services;

import com.revature.rms.search.clients.AuthClient;
import com.revature.rms.search.clients.CampusClient;
import com.revature.rms.search.clients.EmployeeClient;


import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.batch.Batch;
import com.revature.rms.search.entites.batch.Curriculum;
import com.revature.rms.search.entites.campus.*;
import com.revature.rms.search.entites.employee.AppUser;
import com.revature.rms.search.entites.employee.Department;
import com.revature.rms.search.entites.employee.Employee;
import com.revature.rms.core.metadata.*;
import com.revature.rms.search.entites.workorder.Category;
import com.revature.rms.search.entites.workorder.WorkOrder;
import com.revature.rms.core.exceptions.*;
import com.revature.rms.search.repositories.BatchRepository;
import com.revature.rms.search.repositories.WorkOrderRepository;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * We ran into too many issues with testing this layer and what you see are
 * our attempts to resolve them. They were all commented out to avoid the
 * build from breaking while we tested endpoints with postman.
 * */

@RunWith(MockitoJUnitRunner.class)
public class ETLServiceTest {


  @InjectMocks ETLService sut;
  @Mock CampusClient mockCampusClient;
  @Mock EmployeeClient mockEmployeeClient;
  @Mock AuthClient mockAuthClient;
  @Mock WorkOrderRepository mockWorkOrderRepo;
  @Mock BatchRepository mockBatchRepo;
  ETLService spyService;

  Address address;
  ResourceMetadata resourceMetadata;
  List<Amenity> amenities;
  List<RoomStatus> roomStatuses;
  List<Integer> workOrders;
  List<Room> rooms;
  List<Building> buildings;
  List<Integer> employeesIds;
  Campus campus;
  List<String> roles;
  AppUser empAppUser;
  Curriculum curriculum;
  List<Integer> associates;
  Batch batch;
  Employee employee;
  WorkOrder workOrder;
  ResourceMetadataDto resourceMetadataDto;
  CampusDto campusDto;
  EmployeeDto employeeDto;
  List<BuildingDto> buildingDtos;
  List<EmployeeDto> employeeDtos;
  List<Employee> employees;
  Building building;
  BuildingDto buildingDto;
  BuildingDto buildingDto1;
  List<RoomDto> roomDtos;
  List<RoomDto> roomDtos1;
  BatchDto batchDto;

  @Before
  public void setup() {
    sut = new ETLService(mockEmployeeClient, mockCampusClient, mockWorkOrderRepo, mockBatchRepo,  mockAuthClient);

    spyService = Mockito.spy(new ETLService(mockEmployeeClient, mockCampusClient, mockWorkOrderRepo, mockBatchRepo, mockAuthClient));

    campus = new Campus(17, "USF", "USF", address, 1, 1, 1, buildings, employeesIds, resourceMetadata);

    campusDto = new CampusDto(17, "USF", "USF", address, employeeDto, employeeDto, employeeDto, buildingDtos, employeeDtos, resourceMetadataDto);

    address= new Address(12,"123 Bruce B Downs Blvd", "Tampa", "FL", "33612", "US");

    building = new Building(16, "Muma", "BSN", address, 1, amenities, rooms, resourceMetadata);

    buildings = Arrays.asList(building);

    buildingDto = new BuildingDto(16, "Muma", "BSN", address, amenities);

    buildingDto1 = new BuildingDto(16, "Muma", "BSN", address, new EmployeeDto(1, "test", "test", "test", "test", Department.HR, resourceMetadataDto), amenities, null, null);

    buildingDtos = Arrays.asList(buildingDto);

    employeesIds = Arrays.asList(1);

    amenities = Arrays.asList(new Amenity(AmenityType.COFFEE, AmenityStatus.LOW));

    rooms = Arrays.asList(new Room(15, "123", 25, roomStatuses, 1, workOrders, resourceMetadata));

    roomDtos1 = new ArrayList<>();
    roomDtos1.add(new RoomDto(15, "123", 25, null, null, null, resourceMetadataDto));

    roomDtos = Arrays.asList(new RoomDto(15, "123", 25, null, null, null, resourceMetadataDto));

    roomStatuses = Arrays.asList(new RoomStatus(14, true, true, "1/1/19", 1, "Good"));

    workOrders = Arrays.asList(3);

    employee = new Employee(1, "test", "test", "test", "test", Department.HR,
            new ResourceMetadata(1, "1/1/2020", 1, "1/2/2020", 1, true));

    employeeDto = new EmployeeDto(1, "test", "test", "test", "test", Department.HR, resourceMetadataDto);

    employeeDtos = Arrays.asList(employeeDto);

    empAppUser = new AppUser(1,"Email@email.com","Baller", roles);

    roles = Arrays.asList("TRAINING_SITE_MANAGER", "ADMIN");

    workOrder = new WorkOrder(1, "testTime", "testTime", Category.AIR_CONDITIONING, "test", "test", 1, 1);

    resourceMetadataDto = new ResourceMetadataDto(empAppUser, "time", empAppUser, "test", empAppUser, true);

    resourceMetadata = new ResourceMetadata(1, "1/1/2020", 1, "1/2/2020", 1, true);

    batch = new Batch(24, "ABatch", "2/12/2020", "4/10/2020", 20, 22, associates, curriculum, resourceMetadata);

    batchDto = batch.extractBatch();

    curriculum = Curriculum.AI;

    associates = Arrays.asList(3,5,7);

    employees = Arrays.asList(employee);
  }
  @After
  public void tearDown() {
    sut = null;
  }

  /**
   * testing resource not found exception in get all capuses by having mock campus client return an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllCampusesResourceNotFound(){
    List<Campus> emptyCampus = new ArrayList<>();
    when(mockCampusClient.getAllCampus()).thenReturn(emptyCampus);
    sut.getAllCampuses();
  }

  /**
   * tests the entire get all campus successfully by spying the ETLService and mocking the get campus dto and having it return a campusDTO object
   */
  @Test
  public void testGetAllCampuses(){
    List<Campus> campuses = Arrays.asList(campus);
    List<CampusDto> campusDtoList = Arrays.asList(campusDto);
    when(mockCampusClient.getAllCampus()).thenReturn(campuses);
    Mockito.doReturn(campusDto).when(spyService).getCampusDto(campus);
    Assert.assertEquals(campusDtoList, spyService.getAllCampuses());
  }

  /**
   * tests invalid request exception in get all campuses by training manager id by passing an invalid id number (<1)
   */
  @Test(expected = InvalidRequestException.class)
  public void getAllCampusesByTrainingManagerIdInvalidRequest(){
    sut.getAllCampusesByTrainingManagerId(0);
  }

  /**
   * tests resource not found exception in get all campuses by training manger id by having mock campus client return an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllCampusesByTrainingManagerIdResourceNotFound(){
    List<Campus> emptyCampus = new ArrayList<>();
    when(mockCampusClient.getCampusByTrainingManagerId(1)).thenReturn(emptyCampus);
    sut.getAllCampusesByTrainingManagerId(1);
  }

  /**
   * tests the entire get all campus by training manager id successfully by spying the ETLService
   * and mocking the get campus dto and having it return a campusDTO object
   */
  @Test
  public void testGetAllCampusesByTrainingManagerId(){
    List<Campus> campuses = Arrays.asList(campus);
    List<CampusDto> campusDtoList = Arrays.asList(campusDto);
    when(mockCampusClient.getCampusByTrainingManagerId(1)).thenReturn(campuses);
    Mockito.doReturn(campusDto).when(spyService).getCampusDto(campus);
    Assert.assertEquals(campusDtoList, spyService.getAllCampusesByTrainingManagerId(1));
  }

  /**
   * tests Invalid request Exception in get all campuses by owner id by inputting an id that's less than 1
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetAllCampusesByOwnerIdInvalidId(){
    sut.getAllCampusesByOwnerId(0);
  }

  /**
   * tests Resource Not Found Exception in get all campuses by owner id by having mock campus client return an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
    public void testGetAllCampusesByOwnerIdResourceNotFound(){
      List<Campus> emptyCampus = new ArrayList<>();
      when(mockCampusClient.getAllCampusByOwner(1)).thenReturn(emptyCampus);
      sut.getAllCampusesByOwnerId(1);
    }

  /**
   * tests the entire get all campus by owner id successfully by spying the ETLService
   * and mocking the get campus dto and having it return a campusDTO object
   */
   @Test
   public void testGetAllCampusesByOwnerId(){
     List<Campus> campuses = Arrays.asList(campus);
     List<CampusDto> campusDtoList = Arrays.asList(campusDto);
     when(mockCampusClient.getAllCampusByOwner(1)).thenReturn(campuses);
     Mockito.doReturn(campusDto).when(spyService).getCampusDto(campus);
     Assert.assertEquals(campusDtoList, spyService.getAllCampusesByOwnerId(1));
   }

  /**
   * tests Resource Not Found Exception in get campus Dto by having mock employee client throw a feign Client Exception
   * this is caught in getEmployeeById() through get campusObjects() call
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetCampusDtoResourceNotFound(){
    sut.getCampusDto(campus);
  }

  /**
   * tests the entire get campus dto successfully by spying the ETLService
   * and mocking the get campus objects, get list of buildings data and get each employee meta
   * and then comparing the expected outcome of campusDto the actual outcome
   */
  @Test
  public void testGetCampusDto(){
    Mockito.doReturn(campusDto).when(spyService).getCampusObjects(campus);
    Mockito.doReturn(buildingDtos).when(spyService).getListOfBuildingsData(campus.getBuildings());
    when(mockEmployeeClient.getEmployeesByIds(campus.getCorporateEmployees())).thenReturn(employees);
    Mockito.doReturn(employeeDtos).when(spyService).getEachEmployeeMeta(employees);
    Assert.assertEquals(campusDto, spyService.getCampusDto(campus));
  }

  /**
   * tests Resource Not Found Exception in get Campus Dto by Id by having mock campus client return a null object
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetCampusDtoByIdResourceNotFound(){
    when(mockCampusClient.getCampusById(1)).thenReturn(null);
    sut.getCampusDtoById(1);
  }

  /**
   * tests Invalid Request exception in get campus dto by id by inputting an invalid id number
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetCampusDtoByIdInvalid(){
    sut.getCampusDtoById(0);
  }

  /**
   * tests the entire get campus dto by id successfully by spying the ETLService
   * and mocking the get campus objects, get list of buildings data and get each employee meta
   * and then comparing the expected outcome of campusDto the actual outcome
   */
  @Test
  public void testGetCampusDtoById(){
    Mockito.doReturn(campusDto).when(spyService).getCampusObjects(campus);
    Mockito.doReturn(buildingDtos).when(spyService).getListOfBuildingsData(campus.getBuildings());
    when(mockEmployeeClient.getEmployeesByIds(campus.getCorporateEmployees())).thenReturn(employees);
    Mockito.doReturn(employeeDtos).when(spyService).getEachEmployeeMeta(employees);
    Assert.assertEquals(campusDto, spyService.getCampusDto(campus));
  }

  /**
   * tests the exception catch block by having ETLService throw a null pointer
   */
  @Test(expected = Exception.class)
  public void testGetCampusObjectException(){
    Mockito.doThrow(NullPointerException.class).when(spyService).getEmployeeById(1);
    spyService.getCampusObjects(campus);
  }

  /**
   * tests entire get campus object method by spying the ETLService
   * and mocking get employee by id and campus meta data
   */
  @Test
  public void testGetCampusObject(){
    campusDto.setTrainingManager(employeeDto);
    campusDto.setStagingManager(employeeDto);
    campusDto.setHrLead(employeeDto);
    campusDto.setResourceMetadata(resourceMetadataDto);
    Mockito.doReturn(employeeDto).when(spyService).getEmployeeById(1);
    Mockito.doReturn(resourceMetadataDto).when(spyService).campusMetaData(campus.getResourceMetadata());
    Assert.assertEquals(campusDto, spyService.getCampusObjects(campus));
  }

  /**
   * tests the entire campus meta data method by spying the ETLService and having it return
   * an app user when get app user by id is called
   */
  @Test
  public void testCampusMetaData(){
    ResourceMetadataDto resourceMetadataDto2 = new ResourceMetadataDto( empAppUser, "1/1/2020", empAppUser, "1/2/2020", empAppUser, true);
    Mockito.doReturn(empAppUser).when(spyService).getAppUserById(anyInt());
    Assert.assertEquals(resourceMetadataDto2, spyService.campusMetaData(resourceMetadata));
  }

  /**
   * tests Resource Not Found Exception in get all Buildings by returning an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllBuildingsResourceNotFound(){
    List<Building> buildings = new ArrayList<>();
    when(mockCampusClient.getAllBuildings()).thenReturn(buildings);
    sut.getAllBuildings();
  }

  /**
   * tests the entire get all buildings successfully by spying the ETLService
   * and mocking the get list of buildings data to return a list of buildings dtos
   * as well as mocking the campus client and have it return a list of buildings
   */
  @Test
  public void testGetAllBuildings(){
    when(mockCampusClient.getAllBuildings()).thenReturn(buildings);
    Mockito.doReturn(buildingDtos).when(spyService).getListOfBuildingsData(buildings);
    Assert.assertEquals(buildingDtos, spyService.getAllBuildings());
  }

  /**
   * tests the entire get list of buildings data by spying the ETLService
   * and mocking the get employee by id, get each room meta, and campus meta data
   * and then comparing a list of building dtos and the return of the method
   */
  @Test
  public void testGetListOfBuildingsData(){
    buildingDtos.get(0).setTrainingLead(employeeDto);
    buildingDtos.get(0).setRooms(roomDtos);
    Mockito.doReturn(employeeDto).when(spyService).getEmployeeById(anyInt());
    Mockito.doReturn(roomDtos).when(spyService).getEachRoomMeta(building.getRooms());
    Assert.assertEquals(buildingDtos, spyService.getListOfBuildingsData(buildings));
  }
  /**
   * tests Invalid Request exception in get building dto by id by inputting an invalid id number
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetBuildingDtoByIdInvalidRequest(){
    sut.getBuildingDtoById(0);
  }

  /**
   * tests Resource Not Found exception in get building dto by id by returning null when mock campus client is asked to get building by id
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetBuildingDtoByIdResourceNotFound(){
    when(mockCampusClient.getBuildingById(1)).thenReturn(null);
    sut.getBuildingDtoById(1);
  }

  /**
   * tests the entire get building dto by id successfully by spying the ETLService
   * and mocking the get building data to return a building dtos
   * as well as mocking the campus client and have it return a building
   */
  @Test
  public void testGetBuildingDtoById(){
    when(mockCampusClient.getBuildingById(1)).thenReturn(building);
    Mockito.doReturn(buildingDto).when(spyService).getBuildingData(building);
    Assert.assertEquals(buildingDto, spyService.getBuildingDtoById(1));
  }

  /**
   * tests Invalid Request exception in get building dto by training lead id by inputting an invalid id number
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetBuildingDtoByTrainingLeadIdInvalidRequest(){
    sut.getBuildingDtoByTrainingLeadId(0);
  }

  /**
   * tests Resource Not Found exception in get building dto by id by returning null when mock campus client is asked to get building by id
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetBuildingDtoByTrainingLeadIdResourceNotFound(){
    when(mockCampusClient.getBuildingByTrainingLeadId(1)).thenReturn(null);
    sut.getBuildingDtoByTrainingLeadId(1);
  }

  /**
   * tests the entire get building dto by training lead id successfully by spying the ETLService
   * and mocking the get building data to return a building dto
   * as well as mocking the campus client and have it return a building
   */
  @Test
  public void testGetBuildingDtoByTrainingLeadId(){
    when(mockCampusClient.getBuildingByTrainingLeadId(1)).thenReturn(building);
    Mockito.doReturn(buildingDto).when(spyService).getBuildingData(building);
    Assert.assertEquals(buildingDto, spyService.getBuildingDtoByTrainingLeadId(1));
  }

  /**
   * tests Invalid Request exception in get all buildings by Owner by inputting an invalid id number
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetALLBuildingsByOwnerInvalidRequest(){
    sut.getAllBuildingsByOwner(0);
  }

  /**
   * tests Resource Not Found exception in get all buildings by Owner by having mock campus client return an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllBuildingsByOwnerResourceNotFound(){
    List<Building> buildings = new ArrayList<>();
    when(mockCampusClient.getAllBuildingsByOwner(1)).thenReturn(buildings);
    sut.getAllBuildingsByOwner(1);
  }

  /**
   * tests the entire get all buildings by owner successfully by spying the ETLService
   * and mocking the get building data to return a building dto
   * as well as mocking the campus client and have it return a list of buildings
   */
  @Test
  public void testGetAllBuildingsByOwner(){
    when(mockCampusClient.getAllBuildingsByOwner(1)).thenReturn(buildings);
    Mockito.doReturn(buildingDto).when(spyService).getBuildingData(building);
    Assert.assertEquals(buildingDtos, spyService.getAllBuildingsByOwner(1));
  }

  /**
   * tests the entire get building data by spying the ETLService
   * and mocking get employee by id, get each room meta, get campus meta data
   * and then comparing a pre made building dto to what the method returns
   */
  @Test
  public void testGetBuildingData(){
    buildingDto1.setRooms(roomDtos1);
    Mockito.doReturn(employeeDto).when(spyService).getEmployeeById(building.getTrainingLead());
    Mockito.doReturn(roomDtos).when(spyService).getEachRoomMeta(building.getRooms());
    Assert.assertEquals(buildingDto1, spyService.getBuildingData(building));
  }

  /**
   *  tests Resource Not Found exception in get all rooms by having mock campus client return an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllRoomsResourceNotFound(){
    List<Room> rooms = new ArrayList<>();
    when(mockCampusClient.getAllRooms()).thenReturn(rooms);
    sut.getAllRooms();
  }

  /**
   * tests the entire get all rooms by spying the ETLService
   * and mocking get each room meta and returning a list of room dtos
   * after mock campus client returns a list of rooms
   */
  @Test
  public void testGetALLRooms(){
    when(mockCampusClient.getAllRooms()).thenReturn(rooms);
    Mockito.doReturn(roomDtos).when(spyService).getEachRoomMeta(rooms);
    Assert.assertEquals(roomDtos, spyService.getAllRooms());
  }

  /**
   * tests Invalid Request exception in get room dto by id by inputting an invalid id number
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetRoomDtoByIdInvalid(){
    sut.getRoomDtoById(0);
  }

  /**
   *  tests Resource Not Found exception in get room dto by id having mock campus client return null
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetRoomDtoByIdResourceNotFound(){
    when(mockCampusClient.getRoomById(1)).thenReturn(null);
    sut.getRoomDtoById(1);
  }

  /**
   * tests get room dto by id by spying the ETLService
   * and mocking get emps from room status, get batch by id, get batch info, get each work order info
   * and campus meta data to create a room dto that would match the room dto also made in the test
   */
  @Test
  public void testGetRoomDtoById(){
    List<RoomStatusDto> roomStatusDtos = new ArrayList<>();
    roomStatusDtos.add(new RoomStatusDto(14, true, true, "1/1/19", employeeDto, "Good"));
    roomDtos.get(0).setCurrentStatus(roomStatusDtos);
    roomDtos.get(0).setBatch(batchDto);
    List<WorkOrderDto> workOrderDtos = Arrays.asList(workOrder.extractWorkOrder());
    roomDtos.get(0).setWorkOrders(workOrderDtos);
    roomDtos.get(0).setResourceMetadata(resourceMetadataDto);
    when(mockCampusClient.getRoomById(1)).thenReturn(rooms.get(0));
    Mockito.doReturn(roomStatusDtos).when(spyService).getEmpsFromRoomStatus(rooms.get(0).getCurrentStatus());
    Mockito.doReturn(batch).when(spyService).getBatchById(rooms.get(0).getBatchId());
    Mockito.doReturn(batchDto).when(spyService).getBatchInfo(batch);
    Mockito.doReturn(workOrderDtos).when(spyService).getEachWorkOrderInfo(rooms.get(0).getWorkOrders());
    Mockito.doReturn(resourceMetadataDto).when(spyService).campusMetaData(rooms.get(0).getResourceMetadata());
    Assert.assertEquals(roomDtos.get(0), spyService.getRoomDtoById(1));
  }

  /**
   * tests Invalid Request exception in get room dto by trainer id by inputting an invalid id number
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetRoomDtoByTrainerIdInvalid(){
    sut.getRoomDtoByTrainerId(0);
  }

  /**
   *  tests Resource Not Found exception in get room Dto by trainer id by having mock campus client return an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetRoomDtoByTrainerIdResourceNotFound(){
    List<Room> rooms = new ArrayList<>();
    when(mockCampusClient.getAllRooms()).thenReturn(rooms);
    sut.getRoomDtoByTrainerId(1);
  }

  /**
   * tests for exception catching in room dto by trainer id
   * by spying the ETLService and mocking get emps from room status, get batch by id, get batch info,
   * get each work order info and campus meta data and having it throw a null pointer
   */
  @Test(expected = Exception.class)
  public void testGetRoomDtoByTrainerIdException(){
    when(mockCampusClient.getAllRooms()).thenReturn(rooms);
    List<RoomStatusDto> roomStatusDtos = new ArrayList<>();
    roomStatusDtos.add(new RoomStatusDto(14, true, true, "1/1/19", employeeDto, "Good"));
    List<WorkOrderDto> workOrderDtos = Arrays.asList(workOrder.extractWorkOrder());
    Mockito.doReturn(roomStatusDtos).when(spyService).getEmpsFromRoomStatus(rooms.get(0).getCurrentStatus());
    Mockito.doReturn(batch).when(spyService).getBatchById(rooms.get(0).getBatchId());
    Mockito.doReturn(batchDto).when(spyService).getBatchInfo(batch);
    Mockito.doReturn(workOrderDtos).when(spyService).getEachWorkOrderInfo(rooms.get(0).getWorkOrders());
    Mockito.doReturn(resourceMetadataDto).when(spyService).campusMetaData(rooms.get(0).getResourceMetadata());
    spyService.getRoomDtoByTrainerId(1);
  }
  //TODO GET ROOM DTO BY TRAINER ID

  /**
   * tests Invalid Request exception in get all room by owner by inputting an invalid id number
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetAllRoomByOwnerInvalid(){
    sut.getAllRoomByOwner(0);
  }

  /**
   *  tests Resource Not Found exception in get room Dto by trainer id by having mock campus client return an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllRoomByOwnerResourceNotFound(){
    List<Room> rooms = new ArrayList<>();
    when(mockCampusClient.getAllRoomByOwner(1)).thenReturn(rooms);
    sut.getAllRoomByOwner(1);
  }

  /**
   * tests the entire get all room by owner id method by spying the ETLService
   * and mocking the get each room method and making it return a list of room dtos
   * as well as mocking the campus client to return a list of rooms
   */
  @Test
  public void testGetAllRoomByOwnerId(){
    when(mockCampusClient.getAllRoomByOwner(1)).thenReturn(rooms);
    Mockito.doReturn(roomDtos).when(spyService).getEachRoomMeta(rooms);
    Assert.assertEquals(roomDtos, spyService.getAllRoomByOwner(1));
  }

  /**
   *  tests Resource Not Found exception in get all employees by having mock employee client return an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllEmployeeResourceNotFound() {
    List<Employee> employeeList = new ArrayList<>();
    when(mockEmployeeClient.getAllEmployee()).thenReturn(employeeList);
    sut.getAllEmployees();
  }

  /**
   * tests get each room meta by spying the ETLService
   * and mocking get emps from room status, get batch by id, get batch info, get each work order info
   * and campus meta data to create a room dto that would match the room dto also made in the test
   */
  @Test
  public void testGetEachRoomMeta(){
    List<RoomStatusDto> roomStatusDtos = new ArrayList<>();
    roomStatusDtos.add(new RoomStatusDto(14, true, true, "1/1/19", employeeDto, "Good"));
    roomDtos.get(0).setCurrentStatus(roomStatusDtos);
    roomDtos.get(0).setBatch(batchDto);
    List<WorkOrderDto> workOrderDtos = Arrays.asList(workOrder.extractWorkOrder());
    roomDtos.get(0).setWorkOrders(workOrderDtos);
    roomDtos.get(0).setResourceMetadata(resourceMetadataDto);
    Mockito.doReturn(roomStatusDtos).when(spyService).getEmpsFromRoomStatus(rooms.get(0).getCurrentStatus());
    Mockito.doReturn(batch).when(spyService).getBatchById(rooms.get(0).getBatchId());
    Mockito.doReturn(batchDto).when(spyService).getBatchInfo(batch);
    Mockito.doReturn(workOrderDtos).when(spyService).getEachWorkOrderInfo(rooms.get(0).getWorkOrders());
    Mockito.doReturn(resourceMetadataDto).when(spyService).campusMetaData(rooms.get(0).getResourceMetadata());
    Assert.assertEquals(roomDtos, spyService.getEachRoomMeta(rooms));
  }

  /**
   * tests get emps from room statuses by spying the ETLService
   * and mocking get employee by id and making it return an employeeDto
   * then comparing an expected list of roomStatusDtos with the return
   */
  @Test
  public void testGetEmpsFromRoomStatus(){
    List<RoomStatusDto> roomStatusDtos = new ArrayList<>();
    roomStatusDtos.add(new RoomStatusDto(14, true, true, "1/1/19", employeeDto, "Good"));
    Mockito.doReturn(employeeDto).when(spyService).getEmployeeById(roomStatuses.get(0).getSubmitterId());
    Assert.assertEquals(roomStatusDtos, spyService.getEmpsFromRoomStatus(roomStatuses));
  }

  /**
   * tests the resource not found exception by spying the ETLService
   * and mocking the get each employee meta to return an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllEmployeesResourceNotFound(){
    List<EmployeeDto> emptyList = new ArrayList<>();
    when(mockEmployeeClient.getAllEmployee()).thenReturn(employees);
    Mockito.doReturn(emptyList).when(spyService).getEachEmployeeMeta(employees);
    spyService.getAllEmployees();
  }

  /**
   * tests the entire get all employees method by spying the ETLService
   * and mocking the get each employee meta to return a list of employee dtos
   * after it gets a list of employees from mocking the employee client
   */
  @Test
  public void testGetAllEmployees(){
    when(mockEmployeeClient.getAllEmployee()).thenReturn(employees);
    Mockito.doReturn(employeeDtos).when(spyService).getEachEmployeeMeta(employees);
    Assert.assertEquals(employeeDtos, spyService.getAllEmployees());
  }

  /**
   * tests Invalid Request exception in get employee dto by id by inputting an invalid id number
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetEmployeeDtoByIdInvalid(){
    sut.getEmployeeDtoById(0);
  }

  /**
   * tests Resource Not Found exception in get employee dto by id by having employee client return null
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetEmployeeDtoByIdResourceNotFound(){
    when(mockEmployeeClient.getEmployeeById(1)).thenReturn(null);
    sut.getEmployeeDtoById(1);
  }

  /**
   * tests the entire get employee dto by id by mocking the employee client
   * and having it return an employee
   */
  @Test
  public void testGetEmployeeDtoById(){
    when(mockEmployeeClient.getEmployeeById(1)).thenReturn(employee);
    Assert.assertEquals(employee.extractEmployee(), sut.getEmployeeDtoById(1));
  }

  /**
   * tests invalid request exception in get employee by id by giving it an invalid id number
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetEmployeeByIdInvalid(){
    sut.getEmployeeById(0);
  }

  /**
   * tests Resource Not Found exception in get employee by id by having employee client return null
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetEmployeeByIdResourceNotFound(){
    when(mockEmployeeClient.getEmployeeById(1)).thenReturn(null);
    sut.getEmployeeById(1);
  }

  /**
   * tests the entire get employee by id by spying the ETLService
   * and mocking get employee metadata by having it return a resource metadata dto
   * as well as mocking the employee client and having it return an employee
   */
  @Test
  public void testGetEmployeeByID(){
    employeeDto.setResourceMetadata(resourceMetadataDto);
    when(mockEmployeeClient.getEmployeeById(1)).thenReturn(employee);
    Mockito.doReturn(resourceMetadataDto).when(spyService).getEmployeeMetadata(employee.getResourceMetadata());
    Assert.assertEquals(employeeDto, spyService.getEmployeeById(1));
  }

  /**
   * tests invalid request exception in get all employee by owner by giving it an invalid id number
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetAllEmployeeByOwnerInvalid(){
    sut.getAllEmployeeByOwner(0);
  }

  /**
   * tests Resource Not Found exception in get all employee by owner by having employee client return an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllEmployeeByOwnerResource(){
    List<Employee> emptyEmployees = new ArrayList<>();
    when(mockEmployeeClient.getAllEmployeeByOwner(1)).thenReturn(emptyEmployees);
    sut.getAllEmployeeByOwner(1);
  }

  /**
   * tests the entire get all employee by owner by spying the ETLService
   * and mocking the get each employee meta to return a pre made list of employee dtos
   * and mocking the employee client to return a list of employees
   * and then comparing the pre made lists to the returned lists
   */
  @Test
  public void testGetAllEmployeeByOwner(){
    when(mockEmployeeClient.getAllEmployeeByOwner(1)).thenReturn(employees);
    Mockito.doReturn(employeeDtos).when(spyService).getEachEmployeeMeta(employees);
    Assert.assertEquals(employeeDtos, spyService.getAllEmployeeByOwner(1));
  }

  /**
   * tests invalid request exception in get app user by id by giving it an invalid id number
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetAppUserByIdInvalid(){
    sut.getAppUserById(0);
  }

  /**
   * tests Resource Not Found exception in get app user by id by having auth client return null
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetAppUserByIdResourceNotFound(){
    when(mockAuthClient.getUserById(1)).thenReturn(null);
    sut.getAppUserById(1);
  }

  /**
   * tests general exception catching in get app user by id by having mock auth client throw an Null pointer and checking to see if the method throws and exception
   */
  @Test
  public void testGetAppUserByIdException(){
    when(mockAuthClient.getUserById(1)).thenThrow(NullPointerException.class);
    sut.getAppUserById(1);
  }

  /**
   * tests to see if get app user by id works by having auth client give back an app user and checking the return
   */
  @Test
  public void testGetAppUserById(){
    when(mockAuthClient.getUserById(1)).thenReturn(empAppUser);
    Assert.assertEquals(empAppUser, sut.getAppUserById(1));
  }

  /**
   * tests invalid request exception in get work order by id by giving it a bad id number
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetWorkOrderByIdInvalid(){
    sut.getWorkOrderById(0);
  }

  /**
   * tests resource not found exception in get work order by id by having work repo return an empty optional
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetWorkOrderByIdResourceNotFound(){
    when(mockWorkOrderRepo.findById(1)).thenReturn(Optional.empty());
    sut.getWorkOrderById(1);
  }

  /**
   * tests general exception catching in get work order by id by having mock work repo throw an Null pointer and checking to see if the method throws and exception
   */
  @Test
  public void testGetWorkOrderByIdException(){
    when(mockWorkOrderRepo.findById(1)).thenThrow(NullPointerException.class);
    sut.getWorkOrderById(1);
  }

  /**
   * tests get work order by id fully by having work repo give a valid work order and comparing the return
   */
  @Test
  public void testGetWorkOrderById(){
    when(mockWorkOrderRepo.findById(1)).thenReturn(Optional.of(workOrder));
    Assert.assertEquals(workOrder, sut.getWorkOrderById(1));
  }

  /**
   * tests invalid request exception in get each work oder info by giving it a list with one invalid id
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetEachWorkOrderInfoInvalid(){
    List<Integer> ids = new ArrayList<>();
    ids.add(2);
    ids.add(0);
    sut.getEachWorkOrderInfo(ids);
  }

  /**
   * tests the entire get each work order info by spying the ETLService
   * and mocking the get work order by id and get employee by id
   * and then comparing a pre made list of work order dtos and the return list
   */
  @Test
  public void testGetEachWorkOrderInfo(){
    WorkOrderDto workOrderDto = workOrder.extractWorkOrder();
    workOrderDto.setCreator(employeeDto);
    workOrderDto.setResolver(employeeDto);
    List<WorkOrderDto> workOrderDtos = Arrays.asList(workOrderDto);
    List<Integer> ids = Arrays.asList(1);
    Mockito.doReturn(workOrder).when(spyService).getWorkOrderById(1);
    Mockito.doReturn(employeeDto).when(spyService).getEmployeeById(workOrder.getResolverId());
    Assert.assertEquals(workOrderDtos, spyService.getEachWorkOrderInfo(ids));
  }

  /**
   * tests invalid request exception in get batch by id by giving it an invalid id
   */
  @Test(expected = InvalidRequestException.class)
  public void testGetBatchByIdInvalid(){
    sut.getBatchById(0);
  }

  /**
   * tests resource not found exception in get batch by id by having batch repo return an empty optional
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetBatchByIdResourceNotFound(){
    when(mockBatchRepo.findById(1)).thenReturn(Optional.empty());
    sut.getBatchById(1);
  }

  /**
   * tests the general exception catching in get batch by id by having batch repo throw a null pointer exception
   */
  @Test
  public void testGetBatchByIdException(){
    when(mockBatchRepo.findById(1)).thenThrow(NullPointerException.class);
    sut.getBatchById(1);
  }

  /**
   * tests successful get batch by id by having batch repo return an optional of a batch and then comparing the return
   */
  @Test
  public void testGetBatchById(){
    when(mockBatchRepo.findById(1)).thenReturn(Optional.of(batch));
    Assert.assertEquals(batch, sut.getBatchById(1));
  }

  @Test(expected = NullPointerException.class)
  public void testGetInvalidCampusDto() {
    sut.getCampusDto(null);
  }

  @Test (expected = NullPointerException.class)
  public void testGetCampusInvalidObjects() {
    sut.getCampusObjects(null);
  }

}
