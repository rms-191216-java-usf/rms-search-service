package com.revature.rms.search.integration.controllers;


import com.revature.rms.search.controllers.SearchController;
import com.revature.rms.search.dtos.BuildingDto;
import com.revature.rms.search.dtos.CampusDto;
import com.revature.rms.search.dtos.EmployeeDto;
import com.revature.rms.search.dtos.ResourceMetadataDto;
import com.revature.rms.search.entites.campus.Address;
import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.campus.Campus;
import com.revature.rms.search.entites.common.ResourceMetadata;
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
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Assert
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testFindAllCampusesByTrainingManagerId() throws Exception{
        int id = 1;
        List<CampusDto> testCampusList = new ArrayList<CampusDto>();
        testCampusList.add(testCampus);
        Mockito.when(etlService.getAllCampusesByTrainingManagerId(id)).thenReturn(testCampusList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/campus/training/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testFindCampusById() throws Exception{
        int id = 1;
        Mockito.when(etlService.getCampusDtoById(id)).thenReturn(testCampus);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/campus/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform((requestBuilder)).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testFindAllCampusesByOwner() throws Exception{
        int id = 1;
        Campus campus = new Campus(32, "University of South Florida", "USF", address,
                1, 1, 1, new ArrayList<Building>(1),
                new ArrayList<Integer>(3), new ResourceMetadata());
        List<Campus> testCampusList = new ArrayList<Campus>();
        testCampusList.add(campus);
        Mockito.when(etlService.getAllCampusByOwner(id)).thenReturn(testCampusList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/campus/owner/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }
}
