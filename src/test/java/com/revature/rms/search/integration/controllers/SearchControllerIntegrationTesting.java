package com.revature.rms.search.integration.controllers;


import com.revature.rms.search.controllers.SearchController;
import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.campus.*;
import com.revature.rms.search.entites.common.ResourceMetadata;
import com.revature.rms.search.entites.employee.Department;
import com.revature.rms.search.entites.employee.Employee;
import com.revature.rms.search.entites.workorder.WorkOrder;
import com.revature.rms.search.repositories.BatchRepository;
import com.revature.rms.search.repositories.WorkOrderRepository;
import com.revature.rms.search.services.ETLService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.*;
import java.util.ArrayList;
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
    Address address = new Address();
    ResourceMetadataDto resourceMetadataDto = new ResourceMetadataDto();
    EmployeeDto employeeDto = new EmployeeDto();
    CampusDto testCampus = new CampusDto(32, "University of South Florida", "USF", address,
              employeeDto, employeeDto, employeeDto, new ArrayList<BuildingDto>(1),
              new ArrayList<EmployeeDto>(3), resourceMetadataDto);


    @Test
    public void testGetAllCampuses() throws Exception{
        //Arrange
        List<CampusDto> testCampusList = new ArrayList<CampusDto>();
        testCampusList.add(testCampus);
        Mockito.when(etlService.getAllCampuses()).thenReturn(testCampusList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/campuses")
                .accept(MediaType.APPLICATION_JSON);

        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                                        .andExpect(jsonPath("$[0].abbrName", is("USF")));


    }

    @Test
    public void testFindAllCampusesByTrainingManagerId() throws Exception{
        //Arrange
        int id = 1;
        List<CampusDto> testCampusList = new ArrayList<CampusDto>();
        testCampusList.add(testCampus);
        Mockito.when(etlService.getAllCampusesByTrainingManagerId(id)).thenReturn(testCampusList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/campus/training/1")
                .accept(MediaType.APPLICATION_JSON);

        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].abbrName", is("USF")));
    }

    @Test
    public void testFindCampusById() throws Exception{
        //Arrange
        int id = 1;
        Mockito.when(etlService.getCampusDtoById(id)).thenReturn(testCampus);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/campus/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.abbrName", is("USF")));
    }

    @Test
    public void testFindAllCampusesByOwner() throws Exception{
        //Arrange
        int id = 1;
        Campus campus = new Campus(32, "University of South Florida", "USF", address,
                1, 1, 1, new ArrayList<Building>(1),
                new ArrayList<Integer>(3), new ResourceMetadata());
        List<Campus> testCampusList = new ArrayList<Campus>();
        testCampusList.add(campus);
        Mockito.when(etlService.getAllCampusByOwner(id)).thenReturn(testCampusList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/campus/owner/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].abbrName", is("USF")));
    }

    @Test
    public void testFindBuildingById() throws Exception{
        //Arrange
        int id = 1;
        BuildingDto testBuilding = new BuildingDto(1,"test", "test", address, new ArrayList<Amenity>(1));
        Mockito.when(etlService.getBuildingDtoById(id)).thenReturn(testBuilding);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/building/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.abbrName", is("test")));
    }

    @Test
    public void testFindBuildingByTrainingLeadId() throws Exception{
        //Arrange
        int id = 1;
        BuildingDto testBuilding = new BuildingDto(1,"test", "test", address, new ArrayList<Amenity>(1));
        Mockito.when(etlService.getBuildingDtoByTrainingLeadId(id)).thenReturn(testBuilding);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/building/training/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.abbrName", is("test")));

    }

    @Test
    public void testFindBuildingByOwner() throws Exception{
        //Arrange
        int id = 1;
        Building testBuilding = new Building(1, "name", "abbrName", address, 1, new ArrayList<Amenity>() , new ArrayList<Room>(), new ResourceMetadata());
        List<Building> testBuildingList = new ArrayList<Building>();
        testBuildingList.add(testBuilding);
        Mockito.when(etlService.getAllBuildingsByOwner(id)).thenReturn(testBuildingList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/building/owner/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].abbrName", is("abbrName")));
    }

    @Test
    public void testFindRoomById() throws Exception{
        //Arrange
        int id = 1;
        RoomDto testRoom = new RoomDto(1,"205",25);
        Mockito.when(etlService.getRoomDtoById(id)).thenReturn(testRoom);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/room/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.roomNumber", is("205")));
    }

    @Test
    public void testFindRoomByTrainerId() throws Exception{
        //Arrange
        int id = 1;
        RoomDto testRoom = new RoomDto(1,"205",25);
        Mockito.when(etlService.getRoomDtoByTrainerId(id)).thenReturn(testRoom);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/room/trainer/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.roomNumber", is("205")));

    }

    @Test
    public void testFindAllRoomByOwner() throws Exception{
        //Arrange
        int id = 1;
        Room testRoom = new Room(1,"205",25, new ArrayList<>(), 1, new ArrayList<>(), new ResourceMetadata());
        List<Room> testRoomList = new ArrayList<Room>();
        testRoomList.add(testRoom);
        Mockito.when(etlService.getAllRoomByOwner(id)).thenReturn(testRoomList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/room/owner/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roomNumber", is("205")));
    }

    @Test
    public void testFindAllEmployees() throws Exception{
        //Arrange
        EmployeeDto testEmployee = new EmployeeDto(1,"test", "test", "test@test.com", "tester");
        List<EmployeeDto>  testEmployeeList = new ArrayList<EmployeeDto>();
        testEmployeeList.add(testEmployee);
        Mockito.when(etlService.getAllEmployees()).thenReturn(testEmployeeList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/employees")
                .accept(MediaType.APPLICATION_JSON);

        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                                        .andExpect(jsonPath("$[0].firstName", is("test")));

    }

    @Test
    public void testFindEmployeeId() throws Exception{
        //Arrange
        int id = 1;
        RoomDto testRoom = new RoomDto(1,"205",25);
        Mockito.when(etlService.getRoomDtoByTrainerId(id)).thenReturn(testRoom);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/room/trainer/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.roomNumber", is("205")));

    }

    @Test
    public void testFindAllEmployeeByOwner() throws Exception{
        //Arrange
        int id = 1;
        Employee testEmployee = new Employee(1,"test", "test", "test@test.com", "tester");
        List<Employee>  testEmployeeList = new ArrayList<Employee>();
        testEmployeeList.add(testEmployee);
        Mockito.when(etlService.getAllEmployeeByOwner(id)).thenReturn(testEmployeeList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/employee/owner/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("test")));
    }

}
