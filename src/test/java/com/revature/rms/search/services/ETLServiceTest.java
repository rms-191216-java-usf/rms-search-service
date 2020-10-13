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
import com.revature.rms.search.entites.workorder.Category;
import com.revature.rms.search.entites.workorder.WorkOrder;
import com.revature.rms.search.exceptions.InvalidRequestException;
import com.revature.rms.search.exceptions.ResourceNotFoundException;
import com.revature.rms.search.repositories.BatchRepository;
import com.revature.rms.search.repositories.WorkOrderRepository;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
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

  Address address;
  ResourceMetadata resourceMetadata;
  List<Amenity> amenities;
  List<RoomStatus> roomStatuses;
  List<Integer> workOrders;
  List<Room> rooms;
  List<Building> buildings;
  List<Integer> employees;
  Campus campus;
  List<String> roles;
  AppUser empAppUser;
  Curriculum curriculum;
  List<Integer> associates;
  Batch batch;
  Employee employee;
  WorkOrder workOrder;
  ResourceMetadataDto resourceMetadataDto;

  @Before
  public void setup() {
    sut = new ETLService(mockEmployeeClient, mockCampusClient, mockWorkOrderRepo, mockBatchRepo,  mockAuthClient);

    campus = new Campus(17, "USF", "USF", address, 1, 1, 1, buildings, employees, resourceMetadata);

    address= new Address(12,"123 Bruce B Downs Blvd", "Tampa", "FL", "33612", "US");

    buildings = Arrays.asList(new Building(16, "Muma", "BSN", address, 1, amenities, rooms, resourceMetadata));

    employees = Arrays.asList(1);

    amenities = Arrays.asList(new Amenity(AmenityType.COFFEE, AmenityStatus.LOW));

    rooms = Arrays.asList(new Room(15, "123", 25, roomStatuses, 1, workOrders, resourceMetadata));

    roomStatuses = Arrays.asList(new RoomStatus(14, true, true, "1/1/19", 1, "Good"));

    workOrders = Arrays.asList(3);

    employee = new Employee(1, "test", "test", "test", "test", Department.HR,
            new com.revature.rms.search.entites.employee.ResourceMetadata(1, 1, "test", 1, "test", 1));

    empAppUser = new AppUser(1,"Email@email.com","Baller", roles);

    roles = Arrays.asList("TRAINING_SITE_MANAGER", "ADMIN");

    workOrder = new WorkOrder(1, "testTime", "testTime", Category.AIR_CONDITIONING, "test", "test", 1, 1);

    resourceMetadataDto = new ResourceMetadataDto(empAppUser, "time", empAppUser, "test", empAppUser, true);

    resourceMetadata = new ResourceMetadata(1, 15,"1/1/20", 1, "1/1/20", 1, true);

    batch = new Batch(24, "ABatch", "2/12/2020", "4/10/2020", 20, 22, associates, curriculum, resourceMetadata);

    curriculum = Curriculum.AI;

    associates = Arrays.asList(3,5,7);
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

  //TODO GET BUILDING DTO BY TRAINING LEAD ID FULL TEST

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

  //TODO Get All Buildings By Owner full test

  //TODO get Building data test

  /**
   *  tests Resource Not Found exception in get all rooms by having mock campus client return an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllRoomsResourceNotFound(){
    List<Room> rooms = new ArrayList<>();
    when(mockCampusClient.getAllRooms()).thenReturn(rooms);
    sut.getAllRooms();
  }

  //TODO Get All Rooms
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

  //TODO get Room by id test

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

  //TODO GET ALL ROOM BY OWNER ID

  /**
   *  tests Resource Not Found exception in get all employees by having mock employee client return an empty list
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testGetAllEmployeeResourceNotFound() {
    List<Employee> employeeList = new ArrayList<>();
    when(mockEmployeeClient.getAllEmployee()).thenReturn(employeeList);
    sut.getAllEmployees();
  }

  //TODO GET ALL EMPLOYEE

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
  public void testGetEmployeeDtoById(){
    when(mockEmployeeClient.getEmployeeById(1)).thenReturn(null);
    sut.getEmployeeDtoById(1);
  }

  //TODO get employee dto by id

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

  //TODO  GET EMPLOYEE BY ID

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

  //TODO GET ALL EMPLOYEE BY OWNER

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

  //TODO GET EACH WORK ORDER INFO

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

  @Test(expected = ResourceNotFoundException.class)
  public void testGetCampusDtoByInvalidId(){
      sut.getCampusDtoById(-1);
  }

}
