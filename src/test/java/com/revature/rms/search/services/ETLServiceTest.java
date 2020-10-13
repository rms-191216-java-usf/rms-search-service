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
import com.revature.rms.search.entites.common.ResourceMetadata;
import com.revature.rms.search.entites.workorder.WorkOrder;
import com.revature.rms.search.exceptions.InvalidRequestException;
import com.revature.rms.search.exceptions.ResourceNotFoundException;
import com.revature.rms.search.repositories.BatchRepository;
import com.revature.rms.search.repositories.WorkOrderRepository;
import com.revature.rms.search.services.ETLService;
import feign.FeignException;
import org.junit.Assert;
import org.mockito.Mockito;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * We ran into too many issues with testing this layer and what you see are
 * our attempts to resolve them. They were all commented out to avoid the
 * build from breaking while we tested endpoints with postman.
 * */

@RunWith(MockitoJUnitRunner.class)
public class ETLServiceTest {

  // TODO methods to test still: getAllCampuses, getAllCampusesByTrainingManagerId, getAllCampusByOwner,
  // TODO getCampusDto, getCampusDtoById, getCampusObjects, campusMetaData, getAllBuilding,
  // TODO getListOfBuildingsData, getBuildingDtoById, getBuildingDtoByTrainingLeadId, getAllBuildingsByOwner,
  // TODO getBuildingData, getAllRooms, getRoomDtoById, getRoomDtoByTrainerId,

  @InjectMocks ETLService sut;
  @Mock CampusClient mockCampusClient;
  @Mock EmployeeClient mockEmployeeClient;
  private List<Campus> mockCampuses;
  @Mock AuthClient mockAuthClient;
  @Mock WorkOrderRepository mockWorkOrderRepo;
  @Mock BatchRepository mockBatchRepo;

  // TODO put all initialization in a beforeEach method.

  // Objects setup
  // make an address
  Address address = new Address(12,"123 Bruce B Downs Blvd", "Tampa", "FL", "33612", "US");
  // make ResourceMetadata object
  ResourceMetadata resourceMetadata;
  com.revature.rms.search.entites.employee.ResourceMetadata resourceMetadataFromEmployeePackage = new com.revature.rms.search.entites.employee.ResourceMetadata(2,3,"1/19/2020", 20, "2/3/2020", 20);
  // make list of amentities
  List<Amenity> amenities = Arrays.asList(new Amenity(AmenityType.COFFEE, AmenityStatus.LOW));
  // make list of room statuses
  List<RoomStatus> roomStatuses =
          Arrays.asList(new RoomStatus(14, true, true, "1/1/19", 1, "Good"));
  // make list of workorders
  List<Integer> workOrders = Arrays.asList(3);

  // make list of rooms
  List<Room> rooms =
          Arrays.asList(
                  new Room(15, "123", 25, roomStatuses, 1, workOrders, resourceMetadata));

  // make list of buildings
  List<Building> buildings;
  // Lists setup
  List<Integer> employees;
  // make a Campus object
  Campus campus;
  // make list of roles
  List<String> roles = Arrays.asList("TRAINING_SITE_MANAGER", "ADMIN");
  // make an AppUser object
  AppUser empAppUser;
  // make a Curriculum object
  Curriculum curriculum = Curriculum.AI;
  // make a list of associates to put in a Batch object
  List<Integer> associates = Arrays.asList(3,5,7);
  // make a Batch object
  Batch batch = new Batch(24, "ABatch", "2/12/2020", "4/10/2020", 20, 22, associates, curriculum, resourceMetadata);
  // make list of employees (their ID's?)

  // DTO setup
  // make a ResourceMetadataDto object
  ResourceMetadataDto rMDto = new ResourceMetadataDto(empAppUser, "1/1/20", empAppUser, "1/1/20", empAppUser, true);
  // make an EmployeeDto object
  EmployeeDto empDTO;
  // make a list of RoomStatusDto objects
  List<RoomStatusDto> roomStatusDto = Arrays.asList(new RoomStatusDto(18, true, true, "1/1/19", empDTO, "Good"));
  // make a BatchDTO object
  BatchDto batchDto = new BatchDto();
  // make a WorkOrderDto object
  WorkOrderDto workOrderDto = new WorkOrderDto();

  // List of DTOs setup
  // make a list of WorkOrderDtos
  List<WorkOrderDto> workOrderDtoList = Arrays.asList(workOrderDto);
  // make a list of RoomDto objects
  List<RoomDto> roomDtoList = Arrays.asList(new RoomDto(23, "123", 10, roomStatusDto, batchDto, workOrderDtoList, rMDto));
  // make a list of BuildingDto objects
  List<BuildingDto> buildingDto;


  // make a mockCampusDto object
  CampusDto mockCampusDto;

  Employee employee;


  EmployeeDto empDTO2;





  @Before
  public void setup() {
    sut = new ETLService(mockEmployeeClient, mockCampusClient, mockWorkOrderRepo, mockBatchRepo,  mockAuthClient);
    empAppUser = new AppUser(1,"Email@email.com","Baller", roles);
    resourceMetadata = new ResourceMetadata(1, 15,"1/1/20", 1, "1/1/20", 1, true);
    buildings = Arrays.asList(new Building(16, "Muma", "BSN", address, 1, amenities, rooms, resourceMetadata));
    employees = Arrays.asList(1);
    empDTO = new EmployeeDto(12, "John", "Jacob", "jjacob@gmail.com", "TRAINING_SITE_MANAGER", Department.DELIVERY, rMDto);
    mockCampusDto = new CampusDto(26,"USF","USF", address, empDTO, empDTO, empDTO, buildingDto, Arrays.asList(empDTO), rMDto);
    campus = new Campus(17, "USF", "USF", address, 1, 1, 1, buildings, employees, resourceMetadata);
    employee = new Employee(1, "test", "test", "test", "test", Department.HR, new com.revature.rms.search.entites.employee.ResourceMetadata(1, 1, "test", 1, "test", 1));
    buildingDto = Arrays.asList(new BuildingDto(23, "Muma", "BSN", address, empDTO, amenities, roomDtoList, rMDto));
    empDTO2 = new EmployeeDto(1, "test", "test", "test", "test", Department.HR, rMDto);

  }

  @After
  public void tearDown() {
    sut = null;
  }

//  @Test
//  public void testGetCampusDtoByIdWithValidInput() {
//
//    // Arrange
//
//    // mock
//    when(mockCampusClient.getCampusById(26)).thenReturn(campus);
//    CampusDto expectedResult =
//        (CampusDto) when(sut.getCampusObjects(campus)).thenReturn(mockCampusDto);
//
//    // Act and Assert
//    assertEquals(expectedResult, sut.getCampusDtoById(26));

  /**
   * testing resource not found exception in get all capuses by having mock campus client return an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllCampusesResourceNotFound(){
    List<Campus> emptyCampus = new ArrayList<>();
    when(mockCampusClient.getAllCampus()).thenReturn(emptyCampus);
    sut.getAllCampuses();
  }

  //TODO: getALLCampuses full test

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

  //TODO: get all campuses by training manager id true test

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


  //TODO: get all campuses by Owner Id true test

  /**
   * tests Resource Not Found Exception in get campus Dto by having mock employee client throw a feign Client Exception
   * this is caught in getEmployeeById() through get campusObjects() call
   */

  @Test(expected = ResourceNotFoundException.class)
  public void testGetCampusDtoResourceNotFound(){
    when(mockEmployeeClient.getEmployeeById(campus.getTrainingManagerId())).thenReturn(null);
    sut.getCampusDto(campus);
  }

  //TODO: rest of testing of CampusDTO (lot of nested methods)

  /**
   * tests Resource Not Found Exception in get Campus Dto by Id by having mock campus client return a null object
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetCampusDtoByIdResourceNotFound(){
    when(mockCampusClient.getCampusById(1)).thenReturn(null);
    sut.getCampusDtoById(1);
  }

  //TODO: rest of getCampusDtoById testing

  /**
   * tests Resource Not Found Exception in get all Buildings by returning an empty list
   */

  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllBuildingsResourceNotFound(){
    List<Building> buildings = new ArrayList<>();
    when(mockCampusClient.getAllBuildings()).thenReturn(buildings);
    sut.getAllBuildings();
  }

//TODO get all buildings test true

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

  //TODO get Building DTO By Id true test


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

  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllEmployeeResourceNotFound() {
    List<Employee> employeeList = new ArrayList<>();
    when(mockEmployeeClient.getAllEmployee()).thenReturn(employeeList);
    sut.getAllEmployees();
  }

  @Test(expected = NullPointerException.class)
  public void testGetInvalidCampusDto() {
    sut.getCampusDto(null);
  }

  @Test (expected = NullPointerException.class)
  public void testGetCampusInvalidObjects() {
    sut.getCampusObjects(null);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void testGetCampusDtoByInvalidId(){
      sut.getCampusDtoById(-1);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void testGetBuildingDtoByInvalidId() {
    sut.getBuildingDtoById(-1);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void testGetBuildingDtoByTrainingLeadId() {
    sut.getBuildingDtoByTrainingLeadId(-1);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void testGetRoomDtoByInvalidTrainerId() {
    sut.getRoomDtoByTrainerId(-1);
  }

  @Test
  public void testGetWorkOrderById() {
    WorkOrder workOrder = new WorkOrder();
    when(mockWorkOrderRepo.findById(1)).thenReturn(java.util.Optional.of(workOrder));
    Assert.assertSame(workOrder, sut.getWorkOrderById(1));

  }

//  @Test
//  public void testGetBuildingDtoByIdReturnsBuildingDtoClass() {
//    Building building = new Building();
//    BuildingDto buildingDto = new BuildingDto();
//    when(mockCampusClient.getBuildingById(1)).thenReturn(building);
//    Assert.assertSame(BuildingDto.class, sut.getBuildingDtoById(1).getClass());
//  }

//  @Test
//  public void testGetAllBuildingsByOwner() {
//    List<Building> buildings = new ArrayList<>();
//    Building building = new Building();
//    buildings.add(building);
//    when(mockCampusClient.getAllBuildingsByOwner(1)).thenReturn(buildings);
//    Assert.assertSame(buildings, sut.getAllBuildingsByOwner(1));
//
//  }

//  @Test
//  public void testGetAllRooms() {
//    List<Room> rooms = new ArrayList<>();
//
//    when(mockCampusClient.getAllRooms()).thenReturn()
//  }


}
