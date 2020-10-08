package com.revature.rms.search.controllers;


import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.campus.Address;
import com.revature.rms.search.services.ETLService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

    //****************************** Campus Testing *********************************************************
    //Should return all campuses
    @Test
    public void testFindAllCampus() {
        CampusDto testCampus = new CampusDto(32, "University of South Florida", "USF", new Address(),
                mock(EmployeeDto.class), mock(EmployeeDto.class), mock(EmployeeDto.class), new ArrayList<BuildingDto>(1), new ArrayList<EmployeeDto>(3), new ResourceMetadataDto());

        List<CampusDto> testCampusList = Arrays.asList(testCampus);

        Mockito.when(etlService.getAllCampuses()).thenReturn(testCampusList);

        assertEquals(searchController.getAllCampuses(), testCampusList);
    }

    @Test
    public void testFindCampusesById()
    {
        int id = 1;
        CampusDto testCampus = new CampusDto(32, "University of South Florida", "USF", new Address(),
                mock(EmployeeDto.class), mock(EmployeeDto.class), mock(EmployeeDto.class), new ArrayList<BuildingDto>(1), new ArrayList<EmployeeDto>(3), new ResourceMetadataDto());

        Mockito.when(etlService.getCampusDtoById(id)).thenReturn(testCampus);

        assertEquals(searchController.getCampusDtoById(id), testCampus);
    }

    @Test
    public void testFindBuildingsById()
    {
        int id = 1;

        BuildingDto testBuildingDto = new BuildingDto(1,"testBuilding", "test");

        Mockito.when(etlService.getBuildingDtoById(id)).thenReturn(testBuildingDto);

        assertEquals(searchController.getBuildingDtoById(id), testBuildingDto);
    }

    @Test
    public void testFindRoomsById() {
        int id = 1;
        RoomDto testRoom = new RoomDto(1, "101", 25);

        Mockito.when(etlService.getRoomDtoById(id)).thenReturn(testRoom);

        assertEquals(searchController.getRoomDtoById(id), testRoom);
    }


    //****************************** Employee Testing *********************************************************
    //Find all employees
    @Test
    public void testFindAllEmployees() {
        EmployeeDto testEmployee = new EmployeeDto();

        List<EmployeeDto> testEmployeeList = Arrays.asList(testEmployee);

        Mockito.when(etlService.getAllEmployees()).thenReturn(testEmployeeList);

        assertEquals(searchController.getAllEmployees(), testEmployeeList);
    }

    @Test
    public void testGetEmployeeByValidId() {

        String id = "1";
        EmployeeDto expectedResult = new EmployeeDto(1,"Test", "Tester", "test@test.com", "Tester");

        when(etlService.getEmployeeById(1)).thenReturn((expectedResult));
        assertEquals(searchController.getEmployeeById(1),  expectedResult);
    }





}
