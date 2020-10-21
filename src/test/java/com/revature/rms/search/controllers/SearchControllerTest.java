package com.revature.rms.search.controllers;


import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.batch.Batch;
import com.revature.rms.search.entites.batch.Curriculum;
import com.revature.rms.search.entites.campus.Address;
import com.revature.rms.search.entites.campus.Amenity;
import com.revature.rms.search.entites.campus.AmenityStatus;
import com.revature.rms.search.entites.campus.AmenityType;
import com.revature.rms.search.entites.common.ResourceMetadata;
import com.revature.rms.search.entites.employee.Department;
import com.revature.rms.search.entites.workorder.Category;
import com.revature.rms.search.entites.workorder.WorkOrder;
import com.revature.rms.core.exceptions.*;
import com.revature.rms.search.services.ETLService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests will have to be rewritten for the next sprint.
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class SearchControllerTest {

    @InjectMocks
    private SearchController searchController;

    @Mock
    private ETLService etlService;

    List<EmployeeDto> testEmployeeList;
    List<Integer> testAssociates;
    Batch testBatch;
    List<RoomDto> testRooms;
    List<CampusDto> testCampuses;
    List<BuildingDto> testBuildings;
    List<Amenity> amenitieList;
    List<WorkOrder> testWorkOrders;
    InvalidRequestException ireTest;
    ResourceNotFoundException rnfTest;

    @Before
    public void setUp() {
        //Employees
        EmployeeDto emp1 = new EmployeeDto(1, "Bruce", "Wayne", "imbatman@bw.com", "Batman", Department.TRAINING, mock(ResourceMetadataDto.class));
        EmployeeDto emp2 = new EmployeeDto(2, "Peter", "Parker", "webslinger@spidey.com", "Spider Man", Department.QC, mock(ResourceMetadataDto.class));
        EmployeeDto emp3 = new EmployeeDto(3, "Tony", "Stark", "ironman@avenge@net", "Iron Man", Department.HR, mock(ResourceMetadataDto.class));
        EmployeeDto emp4 = new EmployeeDto(4, "Thor", "Odinson", "thunderman@asgard.com", "God Of Thunder", Department.RECRUITMENT, mock(ResourceMetadataDto.class));
        EmployeeDto emp5 = new EmployeeDto(5, "Bruce", "Banner", "hulksmash@smash.net", "Hulk", Department.RETENTION, mock(ResourceMetadataDto.class));
        EmployeeDto emp6 = new EmployeeDto(6, "Captain", "America", "merica@freedom.free", "Captain America", Department.DELIVERY, mock(ResourceMetadataDto.class));
        EmployeeDto emp7 = new EmployeeDto(7, "Hawk", "Eye", "nevermiss@aim.com", "HawkEye", Department.STAGING, mock(ResourceMetadataDto.class));
        testEmployeeList = new ArrayList<>();
        testEmployeeList.add(emp1);
        testEmployeeList.add(emp2);
        testEmployeeList.add(emp3);
        testEmployeeList.add(emp4);
        testEmployeeList.add(emp5);
        testEmployeeList.add(emp6);
        testEmployeeList.add(emp7);

        //Campuses
        CampusDto tc1 = new CampusDto(32, "University of South Florida", "USF", new Address(),emp1, emp7, emp3, new ArrayList<BuildingDto>(1), new ArrayList<EmployeeDto>(3), new ResourceMetadataDto());
        CampusDto tc2 = new CampusDto(33, "University of North Florida", "UNF", new Address(), emp1, emp7, emp3, new ArrayList<BuildingDto>(1), new ArrayList<EmployeeDto>(3), new ResourceMetadataDto());
        testCampuses = new ArrayList<>();
        testCampuses.add(tc1);
        testCampuses.add(tc2);

        //Amenity
        Amenity a1 = new Amenity(AmenityType.COFFEE, AmenityStatus.OK);
        Amenity a2 = new Amenity(AmenityType.COFFEE_CREAMER, AmenityStatus.OK);
        amenitieList = new ArrayList<>();
        amenitieList.add(a1);
        amenitieList.add(a2);

        //Buildings
        BuildingDto b1 = new BuildingDto(1, "Super Hero Training", "HT", mock(Address.class), emp1, amenitieList, testRooms, mock(ResourceMetadataDto.class));
        BuildingDto b2 = new BuildingDto(4, "Awesome Full Stack Supreme", "AFSS", mock(Address.class), emp1, amenitieList, testRooms, mock(ResourceMetadataDto.class));
        testBuildings = new ArrayList<>();
        testBuildings.add(b1);
        testBuildings.add(b2);

        //Rooms
        RoomDto room1 = new RoomDto(1, "101", 25);
        RoomDto room2 = new RoomDto(2, "102", 12);
        RoomDto room3 = new RoomDto(3, "250", 100);
        testRooms = new ArrayList<>();
        testRooms.add(room1);
        testRooms.add(room2);
        testRooms.add(room3);

        //Batches
        testAssociates = new ArrayList<>();
        testAssociates.add(2);
        testAssociates.add(3);
        testAssociates.add(4);
        testAssociates.add(5);
        testAssociates.add(6);
        testAssociates.add(7);
        testBatch = new Batch(1, "BestBatch", "June 27, 2020", "October 24, 2020", 4, 4, testAssociates, Curriculum.JAVA_MSA, mock(ResourceMetadata.class));

        //Work Orders
        testWorkOrders = new ArrayList<>();
        testWorkOrders.add(new WorkOrder(1, "Jan 1, 0000", "Dec 31, 9999", Category.LIGHTING, "These lights have been out for a real, real long time", "imbatman@bw.com", 1, 7));
        testWorkOrders.add(new WorkOrder(2, "Apr 1, 1991", "Apr 1, 1991", Category.OTHER, "April FOOOOOOOOLs, nothing needed fixed!!!", "imbatman@bw.com", 1, 5));

        //Exception Handling
        ireTest = new InvalidRequestException("This is for testing");
        rnfTest = new ResourceNotFoundException("This too... is for testing");

    }

    @After
    public void tearDown() {
        testEmployeeList = null;
        testBatch = null;
        testRooms = null;
        testCampuses = null;
        testAssociates = null;
        testBuildings = null;
        testWorkOrders = null;
    }


    //****************************** Campus Testing *********************************************************

    /**
     * Tests that every campus can be retrieved
     */
    @Test
    public void testGetAllCampus() {
        when(etlService.getAllCampuses()).thenReturn(testCampuses);
        assertEquals(testCampuses, searchController.getAllCampuses());
    }

    /**
     * Tests that all campuses ran be a specific training manager can
     * be retrieved
     */
    @Test
    public void testGetAllCampusesByTrainingManagerId() {
        when(etlService.getAllCampusesByTrainingManagerId(testEmployeeList.get(1).getId())).thenReturn(testCampuses);
        assertEquals(testCampuses, searchController.getAllCampusesByTrainingManagerId(testEmployeeList.get(1).getId()));
    }

    /**
     * Tests that a single CampusDto can be retrieved by it's id
     */
    @Test
    public void testGetCampusesDtoById() {
        when(etlService.getCampusDtoById(testCampuses.get(1).getId())).thenReturn(testCampuses.get(1));
        assertEquals(testCampuses.get(1), searchController.getCampusDtoById(testCampuses.get(1).getId()));
    }

    /**
     * Tests that all Campuses belonging to a single user can be retrieved.
     */
    @Test
    public void testGetAllCampusesByOwnerId() {
        when(etlService.getAllCampusesByOwnerId(testEmployeeList.get(1).getId())).thenReturn(testCampuses);
        assertEquals(testCampuses, searchController.getAllCampusesByOwnerId(testEmployeeList.get(1).getId()));
    }

    //****************************** Building Testing *******************************************************

    /**
     * Tests that all BuildingDtos can be retrieved.
     */
    @Test
    public void testGetAllBuildingDtos() {
        when(etlService.getAllBuildings()).thenReturn(testBuildings);
        assertEquals(testBuildings, searchController.getAllBuildings());
    }

    /**
     * Tests that a specific BuildingDto can be retrieved by it's id.
     */
    @Test
    public void testGetBuildingDtoById() {
        when(etlService.getBuildingDtoById(testBuildings.get(1).getId())).thenReturn(testBuildings.get(1));
        assertEquals(testBuildings.get(1), searchController.getBuildingDtoById(testBuildings.get(1).getId()));
    }

    /**
     * Tests that a specific BuildingDto can be retrieved by the trainer id.
     */
    @Test
    public void testGetBuildingDtoByTrainingLeadId() {
        when(etlService.getBuildingDtoByTrainingLeadId(testEmployeeList.get(1).getId())).thenReturn(testBuildings.get(1));
        assertEquals(testBuildings.get(1), searchController.getBuildingDtoByTrainingLeadId(testEmployeeList.get(1).getId()));
    }

    /**
     * Tests that all Buildings belonging to a specific owner can be retrieved.
     */
    @Test
    public void testGetAllBuildingsByOwner() {
        when(etlService.getAllBuildingsByOwner(testEmployeeList.get(1).getId())).thenReturn(testBuildings);
        assertEquals(testBuildings, searchController.getAllBuildingsByOwner(testEmployeeList.get(1).getId()));
    }

    //****************************** Room Testing *********************************************************

    /**
     * Tests that all RoomDtos can be retrieved.
     */
    @Test
    public void testGetAllRoomDtos() {
        when(etlService.getAllRooms()).thenReturn(testRooms);
        assertEquals(testRooms, searchController.getAllRooms());
    }

    /**
     * Tests that a specific RoomDto can be retrieved with a specific id.
     */
    @Test
    public void testGetRoomDtoById() {
        when(etlService.getRoomDtoById(testRooms.get(1).getId())).thenReturn(testRooms.get(1));
        assertEquals(testRooms.get(1), searchController.getRoomDtoById(testRooms.get(1).getId()));
    }

    /**
     * Tests that a specific RoomDto can be retrieved by an assigned trainer.
     */
    @Test
    public void testGetRoomDtoByTrainerId() {
        when(etlService.getRoomDtoByTrainerId(testEmployeeList.get(1).getId())).thenReturn(testRooms.get(1));
        assertEquals(testRooms.get(1), searchController.getRoomDtoByTrainerId(testEmployeeList.get(1).getId()));
    }

    /**
     * Tests that all RoomDtos belonging a specific owner can be retrieved.
     */
    @Test
    public void testGetAllRoomDtoByOwner() {
        when(etlService.getAllRoomByOwner(testEmployeeList.get(1).getId())).thenReturn(testRooms);
        assertEquals(testRooms, searchController.getAllRoomByOwner(testEmployeeList.get(1).getId()));
    }


    //****************************** Employee Testing *********************************************************

    /**
     * Tests that all EmployeeDtos can be retrieved.
     */
    @Test
    public void testGetAllEmployeeDtos() {
        when(etlService.getAllEmployees()).thenReturn(testEmployeeList);
        assertEquals(testEmployeeList, searchController.getAllEmployees());
    }

    /**
     * Tests that a specific EmployeeDto can be retrieved by it's id.
     */
    @Test
    public void testGetEmployeeById() {
        when(etlService.getEmployeeById(testEmployeeList.get(1).getId())).thenReturn(testEmployeeList.get(1));
        assertEquals(testEmployeeList.get(1), searchController.getEmployeeById(testEmployeeList.get(1).getId()));
    }

    /**
     * Tests that all EmployeeDtos can be retrieved by an owner id.
     */
    @Test
    public void testGetAllEmployeesByOwner() {
        when(etlService.getAllEmployeeByOwner(testEmployeeList.get(1).getId())).thenReturn(testEmployeeList);
        assertEquals(testEmployeeList, searchController.getAllEmployeeByOwner(testEmployeeList.get(1).getId()));
    }

    //****************************** Batch Testing ************************************************************

    /**
     *  This tests the SearchController's getBatchById method.
     */
    @Test
    public void testGetBatchById() {
        when(etlService.getBatchById(testBatch.getId())).thenReturn(testBatch);
        assertEquals(testBatch, searchController.getBatchById(testBatch.getId()));
    }

    //************************** Work Order Testing ************************************************************

    /**
     * Tests that a specific WorkOrderDto can be retrieved by it's id.
     */
    @Test
    public void testGetWorkOrderById() {
        when(etlService.getWorkOrderById(testWorkOrders.get(1).getId())).thenReturn(testWorkOrders.get(1));
        assertEquals(testWorkOrders.get(1), searchController.getWorkOrderById(testWorkOrders.get(1).getId()));
    }


}
