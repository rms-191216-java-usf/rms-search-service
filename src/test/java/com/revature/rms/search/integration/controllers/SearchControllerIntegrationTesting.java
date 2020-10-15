package com.revature.rms.search.integration.controllers;


import com.revature.rms.search.controllers.SearchController;
import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.campus.*;
import com.revature.rms.search.repositories.BatchRepository;
import com.revature.rms.search.repositories.WorkOrderRepository;
import com.revature.rms.search.services.ETLService;
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
    Address address = new Address();
    ResourceMetadataDto resourceMetadataDto = new ResourceMetadataDto();
    EmployeeDto employeeDto = new EmployeeDto();
    CampusDto testCampus = new CampusDto(32, "University of South Florida", "USF", address,
              employeeDto, employeeDto, employeeDto, new ArrayList<BuildingDto>(1),
              new ArrayList<EmployeeDto>(3), resourceMetadataDto);
    BuildingDto testBuilding = new BuildingDto(1,"test", "test", address, new ArrayList<Amenity>(1));
    List<BuildingDto> testBuildings = Arrays.asList(testBuilding);
    RoomDto roomDto = new RoomDto(1, "1", 1);
    List<RoomDto> roomDtos = Arrays.asList(roomDto);

    @Test
    public void testGetAllCampuses() throws Exception{
        //Arrange
        List<CampusDto> testCampusList = new ArrayList<CampusDto>();
        testCampusList.add(testCampus);
        Mockito.when(etlService.getAllCampuses()).thenReturn(testCampusList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/campuses")
                .accept(MediaType.APPLICATION_JSON);

        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                                        .andExpect(jsonPath("$[0].abbrName", is("USF")));


    }

    @Test
    public void testGetAllCampusesByTrainingManagerId() throws Exception{
        //Arrange
        int id = 1;
        List<CampusDto> testCampusList = new ArrayList<CampusDto>();
        testCampusList.add(testCampus);
        Mockito.when(etlService.getAllCampusesByTrainingManagerId(id)).thenReturn(testCampusList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/campuses/training-managers/id/1")
                .accept(MediaType.APPLICATION_JSON);

        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].abbrName", is("USF")));
    }

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


    @Test
    public void testGetAllCampusesByOwnerId() throws Exception{
        int id = 1;
        List<CampusDto> campusDtos = Arrays.asList(testCampus);
        Mockito.when(etlService.getAllCampusesByOwnerId(id)).thenReturn(campusDtos);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/campuses/owners/id/1")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].abbrName", is("USF")));
    }

    @Test
    public void testGetAllBuildings() throws Exception{
        Mockito.when(etlService.getAllBuildings()).thenReturn(testBuildings);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/buildings")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].abbrName", is("test")));
    }

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

    @Test
    public void testGetBuildingByTrainingLeadId() throws Exception{
        //Arrange
        int id = 1;
        BuildingDto testBuilding = new BuildingDto(1,"test", "test", address, new ArrayList<Amenity>(1));
        Mockito.when(etlService.getBuildingDtoByTrainingLeadId(id)).thenReturn(testBuilding);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/buildings/training-managers/id/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.abbrName", is("test")));

    }
    @Test
    public void testGetAllBuildingsByOwnerId() throws Exception{
        Mockito.when(etlService.getAllBuildingsByOwner(1)).thenReturn(testBuildings);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/buildings/owners/id/1")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].abbrName", is("test")));
    }

    @Test
    public void testGetAllRooms() throws Exception{
        Mockito.when(etlService.getAllRooms()).thenReturn(roomDtos);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/rooms")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roomNumber", is("1")));
    }

    @Test
    public void testGetRoomById() throws Exception{
        //Arrange
        int id = 1;
        RoomDto testRoom = new RoomDto(1,"205",25);
        Mockito.when(etlService.getRoomDtoById(id)).thenReturn(testRoom);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/rooms/id/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.roomNumber", is("205")));
    }

    @Test
    public void testGetRoomByTrainerId() throws Exception{
        //Arrange
        int id = 1;
        RoomDto testRoom = new RoomDto(1,"205",25);
        Mockito.when(etlService.getRoomDtoByTrainerId(id)).thenReturn(testRoom);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/rooms/training-managers/id/1")
                .accept(MediaType.APPLICATION_JSON);
        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.roomNumber", is("205")));

    }

    //TODO get all rooms by owners id

    @Test
    public void testGetAllEmployees() throws Exception{
        //Arrange
        EmployeeDto testEmployee = new EmployeeDto(1,"test", "test", "test@test.com", "tester");
        List<EmployeeDto>  testEmployeeList = new ArrayList<EmployeeDto>();
        testEmployeeList.add(testEmployee);
        Mockito.when(etlService.getAllEmployees()).thenReturn(testEmployeeList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/search/employees")
                .accept(MediaType.APPLICATION_JSON);

        //Act
        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                                        .andExpect(jsonPath("$[0].firstName", is("test")));

    }

    //TODO get employee by id
    //TODO get all employee by owner id
    //TODO get batch by id
    //TODO get work order by id

}
