import com.revature.rms.search.clients.CampusClient;
import com.revature.rms.search.clients.EmployeeClient;
import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.campus.Address;
import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.campus.Campus;
import com.revature.rms.search.entites.campus.ResourceMetadata;
import com.revature.rms.search.entites.employee.Employee;
import com.revature.rms.search.services.ETLService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(MockitoJUnitRunner.class)
public class ETLServiceTest {

    ETLService sut;
    @Mock
    CampusClient mockCampusClient;
    @Mock
    EmployeeClient mockEmployeeClient;

    @Before
    public void setup() {
        sut = new ETLService(mockEmployeeClient, mockCampusClient);
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void testGetAllCampuses(){
        Campus testCampus = new Campus("32", "University of South Florida", "USF", new Address(),
                2, 3, 4, new ArrayList<Building>(1), new ArrayList<Integer>(3), new ResourceMetadata());
        CampusDto dto = new CampusDto("32", "University of South Florida", "USF", new Address(), new EmployeeDto(), new EmployeeDto(), new EmployeeDto(), new ArrayList<BuildingDto>(), new ArrayList<EmployeeDto>(), new ResourceMetadataDto());
        List<Campus> mockCampusList = Arrays.asList(testCampus);
        List<CampusDto> expectedList = Arrays.asList(dto);
        when(mockCampusClient.getAllCampus()).thenReturn(mockCampusList);
        List<CampusDto> actualList = (List<CampusDto>) when(sut.getAllCampuses()).thenReturn(expectedList);
        assertEquals(expectedList, actualList);
    }

//    @Test
//    public void testGetCampusDtoById(){
//        CampusDto mockCampusDto = mock(CampusDto.class);
//        Campus campus = new Campus();
//        List<Building> buildingList = new ArrayList<>();
//        when(mockCampusClient.getCampusById("1")).thenReturn(campus);
//        when(sut.getCampusObjects(campus)).thenReturn(mockCampusDto);
//        sut.getCampusDtoById("1");
//    }
//
//    @Testc
//    public void testGetBuildingDtoById(){
//        Building building = new Building();
//        when(mockCampusClient.getBuildingById("1")).thenReturn(building);
//        sut.getBuildingDtoById("1");
//    }
//
//    @Test
//    public void testGetAllEmployee(){
//        List<Employee> employeeList = new ArrayList<>();
//        when(mockEmployeeClient.getAllEmployee()).thenReturn(employeeList);
//        sut.getAllEmployees();
//    }

//    @Test
//    public void testGetRoomDtoById(){
//        RoomDto roomDto = mock(RoomDto.class);
//        Campus campus = new Campus();
//        when(mockCampusClient.getRoomById("1")).thenReturn(roomDto);
//        sut.getRoomDtoById("1");
//    }

    


}

