package com.revature.rms.search.controllers;


import com.revature.rms.search.controllers.SearchController;
import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.campus.Address;
import com.revature.rms.search.services.ETLService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Tests will have to be rewritten for the next sprint. 
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class SearchControllerTest {

    @InjectMocks
    private SearchController searchController;

    @Mock
    private ETLService etlService;

    @Test
    public void testFindAllCampus()
    {
        CampusDto testCampus = new CampusDto("32", "University of South Florida", "USF", new Address(),
                mock(EmployeeDto.class), mock(EmployeeDto.class), mock(EmployeeDto.class), new ArrayList<BuildingDto>(1), new ArrayList<EmployeeDto>(3), new ResourceMetadataDto());

        List<CampusDto> testCampusList = Arrays.asList(testCampus);

        Mockito.when(etlService.getAllCampuses()).thenReturn(testCampusList);

        assertEquals(searchController.findAllCampuses(), testCampusList);
    }


    @Test
    public void testFindAllEmployees()
    {
        EmployeeDto testEmployee = new EmployeeDto();

        List<EmployeeDto> testEmployeeList = Arrays.asList(testEmployee);

        Mockito.when(etlService.getAllEmployees()).thenReturn(testEmployeeList);

        assertEquals(searchController.findAllEmployees(), testEmployeeList);
    }


//    @Test
//    public void testFindAllBuildings()
//    {
//        BuildingDto testBuilding = new BuildingDto();
//
//        List<BuildingDto> testBuildingList = Arrays.asList(testBuilding);
//
//        Mockito.when(etlService.getBuildingData()).thenReturn((BuildingDto) testBuildingList);
//
//        assertEquals(searchController.getBuildingById("id"), testBuildingList);
//    }

    @Test
    public void testFindRoomsById()
    {
        RoomDto testRoom = new RoomDto();

        List<RoomDto> testRoomList = Arrays.asList(testRoom);

        Mockito.when(etlService.getRoomDtoById("id")).thenReturn((RoomDto)testRoomList);

        assertEquals(searchController.findBuildingById("id"), testRoomList);
    }

}
