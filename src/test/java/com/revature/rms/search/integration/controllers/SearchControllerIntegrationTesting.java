package com.revature.rms.search.integration.controllers;


import com.revature.rms.search.controllers.SearchController;
import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.batch.Batch;
import com.revature.rms.search.entites.campus.*;
import com.revature.rms.search.entites.workorder.WorkOrder;
import com.revature.rms.search.repositories.BatchRepository;
import com.revature.rms.search.repositories.WorkOrderRepository;
import com.revature.rms.search.services.ETLService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SearchController.class)
@AutoConfigureMockMvc
public class SearchControllerIntegrationTesting {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ETLService etlService;
    @MockBean
    private BatchRepository batchRepository;
    @MockBean
    private WorkOrderRepository workOrderRepository;


    //Set up Mock Data
    Address address;
    ResourceMetadataDto resourceMetadataDto;
    EmployeeDto employeeDto;
    CampusDto testCampus;
    BuildingDto testBuilding;
    List<BuildingDto> testBuildings;
    RoomDto roomDto;
    List<RoomDto> roomDtos;
    List<CampusDto> testCampusList;
    RoomDto testRoom;
    List<RoomDto> roomDtos2;
    EmployeeDto testEmployee;
    List<EmployeeDto>  testEmployeeList;


    @Before
    public void setUp(){
        address = new Address();
        resourceMetadataDto = new ResourceMetadataDto();
        employeeDto = new EmployeeDto();
        testCampus = new CampusDto(32, "University of South Florida", "USF", address,
                employeeDto, employeeDto, employeeDto, new ArrayList<BuildingDto>(1),
                new ArrayList<EmployeeDto>(3), resourceMetadataDto);
        testBuilding = new BuildingDto(1,"test", "test", address, new ArrayList<Amenity>(1));
        testBuildings = Arrays.asList(testBuilding);
        roomDto = new RoomDto(1, "1", 1);
        roomDtos = Arrays.asList(roomDto);
        testCampusList = new ArrayList<CampusDto>();
        testCampusList.add(testCampus);
        testRoom = new RoomDto(1,"205",25);
        roomDtos2 = Arrays.asList(testRoom);
        testEmployee = new EmployeeDto(1,"test", "test", "test@test.com", "tester");
        testEmployeeList = Arrays.asList(testEmployee);

    }

    /**
     * tests get all campuses endpoint /search/campuses using MockMVC testing and having ETLService return a list of campuses when prompted
     * @throws Exception
     */
    @Test
    public void testGetAllCampuses() throws Exception{
        //Arrange
        Mockito.when(etlService.getAllCampuses()).thenReturn(testCampusList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/campuses")
                .accept(MediaType.APPLICATION_JSON);

        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                                        .andExpect(jsonPath("$[0].abbrName", is("USF")));


    }

    /**
     * tests get all campuses by training manager id endpoint /search/campuses/training-managers/id/{id} using MockMVC testing and having ETLService return a list of campuses when prompted
     * @throws Exception
     */
    @Test
    public void testGetAllCampusesByTrainingManagerId() throws Exception{
        //Arrange
        int id = 1;
        Mockito.when(etlService.getAllCampusesByTrainingManagerId(id)).thenReturn(testCampusList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/campuses/training-managers/id/1")
                .accept(MediaType.APPLICATION_JSON);

        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].abbrName", is("USF")));
    }

    /**
     * tests get campus by id endpoint /search/campuses/id/{id} using MockMVC testing and having ETLService return a campus when prompted
     * @throws Exception
     */
    @Test
    public void testGetCampusById() throws Exception{
        //Arrange
        int id = 1;
        Mockito.when(etlService.getCampusDtoById(id)).thenReturn(testCampus);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/campuses/id/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.abbrName", is("USF")));
    }

    /**
     * tests get all campuses by owner id endpoint /search/campuses/owners/id/{id} using MockMVC testing and having ETLService return a list of campuses when prompted
     * @throws Exception
     */
    @Test
    public void testGetAllCampusesByOwnerId() throws Exception{
        int id = 1;
        Mockito.when(etlService.getAllCampusesByOwnerId(id)).thenReturn(testCampusList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/campuses/owners/id/1")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].abbrName", is("USF")));
    }

    /**
     * tests get all buildings endpoint /search/buildings using MockMVC testing and having ETLService return a list of buildings when prompted
     * @throws Exception
     */
    @Test
    public void testGetAllBuildings() throws Exception{
        Mockito.when(etlService.getAllBuildings()).thenReturn(testBuildings);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/buildings")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].abbrName", is("test")));
    }

    /**
     * tests get building by id endpoint /search/buildings/id/{id} using MockMVC testing and having ETLService return a building when prompted
     * @throws Exception
     */
    @Test
    public void testGetBuildingById() throws Exception{
        //Arrange
        int id = 1;
        Mockito.when(etlService.getBuildingDtoById(id)).thenReturn(testBuilding);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/buildings/id/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.abbrName", is("test")));
    }

    /**
     * tests get buildings by training lead id endpoint /search/buildings/training-managers/id/{id} using MockMVC testing and having ETLService return a building when prompted
     * @throws Exception
     */
    @Test
    public void testGetBuildingByTrainingLeadId() throws Exception{
        //Arrange
        int id = 1;;
        Mockito.when(etlService.getBuildingDtoByTrainingLeadId(id)).thenReturn(testBuilding);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/buildings/training-managers/id/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.abbrName", is("test")));

    }

    /**
     * tests get all buildings by owner id endpoint /search/buildings/owners/id/{id} using MockMVC testing and having ETLService return a list of buildings when prompted
     * @throws Exception
     */
    @Test
    public void testGetAllBuildingsByOwnerId() throws Exception{
        Mockito.when(etlService.getAllBuildingsByOwner(1)).thenReturn(testBuildings);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/buildings/owners/id/1")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].abbrName", is("test")));
    }

    /**
     * tests get all rooms endpoint /search/rooms using MockMVC testing and having ETLService return a list of room dtos when prompted
     * @throws Exception
     */
    @Test
    public void testGetAllRooms() throws Exception{
        Mockito.when(etlService.getAllRooms()).thenReturn(roomDtos);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/rooms")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roomNumber", is("1")));
    }

    /**
     * tests get room by id endpoint /search/rooms/id/{id} using MockMVC testing and having ETLService return a room when prompted
     * @throws Exception
     */
    @Test
    public void testGetRoomById() throws Exception{
        //Arrange
        int id = 1;
        Mockito.when(etlService.getRoomDtoById(id)).thenReturn(testRoom);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/rooms/id/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.roomNumber", is("205")));
    }

    /**
     * tests get room by training id endpoint /search/rooms/training-managers/id/{id} using MockMVC testing and having ETLService return a room when prompted
     * @throws Exception
     */
    @Test
    public void testGetRoomByTrainerId() throws Exception{
        //Arrange
        int id = 1;
        Mockito.when(etlService.getRoomDtoByTrainerId(id)).thenReturn(testRoom);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/rooms/training-managers/id/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.roomNumber", is("205")));

    }

    /**
     * tests get all rooms by owner endpoint /search/rooms/owner/id/{id} using MockMVC testing and having ETLService return a list of room dtos when prompted
     * @throws Exception
     */
    @Test
    public void testGetAllRoomsByOwner() throws Exception{

        Mockito.when(etlService.getAllRoomByOwner(1)).thenReturn(roomDtos2);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/rooms/owners/id/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roomNumber", is("205")));
    }

    /**
     * tests get all employees endpoint /search/employees using MockMVC testing and having ETLService return a list of employees when prompted
     * @throws Exception
     */
    @Test
    public void testGetAllEmployees() throws Exception{
        //Arrange
        Mockito.when(etlService.getAllEmployees()).thenReturn(testEmployeeList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/employees")
                .accept(MediaType.APPLICATION_JSON);

        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                                        .andExpect(jsonPath("$[0].firstName", is("test")));

    }

    /**
     * tests get employee by id endpoint /search/employees/id/{id} using MockMVC testing and having ETLService return an employee when prompted
     * @throws Exception
     */
    @Test
    public void testGetEmployeeById() throws Exception{
        Mockito.when(etlService.getEmployeeById(1)).thenReturn(testEmployee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/employees/id/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("test")));
    }

    /**
     * tests get all employees by owner id endpoint /search/employees/owners/id/{id} using MockMVC testing and having ETLService return a list of employees when prompted
     * @throws Exception
     */
    @Test
    public void testGetAllEmployeesByOwner() throws Exception{
        Mockito.when(etlService.getAllEmployeeByOwner(1)).thenReturn(testEmployeeList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/employees/owners/id/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("test")));
    }

    /**
     * tests get batch by id endpoint /search/batches/id/{id} using MockMVC testing and having ETLService return a batch when prompted
     * @throws Exception
     */
    @Test
    public void testGetBatchById() throws Exception{
        Batch batch = new Batch(1, "test", "test", "test", 1, 1, null, null, null);
        Mockito.when(etlService.getBatchById(1)).thenReturn(batch);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/batches/id/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("test")));
    }

    /**
     * tests get work order by id endpoint /search/workorders/id/{id} using MockMVC testing and having ETLService return a work order when prompted
     * @throws Exception
     */
    @Test
    public void testGetWorkOrderByID() throws Exception{
        WorkOrder workOrder = new WorkOrder(1, "test", "test", null, "test", "test", 1, 1);
        Mockito.when(etlService.getWorkOrderById(1)).thenReturn(workOrder);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/workorders/id/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("test")));
    }


}
