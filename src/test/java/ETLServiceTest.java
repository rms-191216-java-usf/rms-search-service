import com.revature.rms.search.clients.CampusClient;
import com.revature.rms.search.clients.EmployeeClient;
import com.revature.rms.search.dtos.BuildingDto;
import com.revature.rms.search.dtos.CampusDto;
import com.revature.rms.search.dtos.EmployeeDto;
import com.revature.rms.search.dtos.RoomDto;
import com.revature.rms.search.entites.campus.Building;
import com.revature.rms.search.entites.campus.Campus;
import com.revature.rms.search.entites.employee.Employee;
import com.revature.rms.search.services.ETLService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ETLService.class)
@RunWith(MockitoJUnitRunner.class)
public class ETLServiceTest {

    @InjectMocks
    ETLService sut;
    @MockBean
    CampusClient mockCampusClient;
    @MockBean
    EmployeeClient mockEmployeeClient;


    @org.junit.Test
    public void testGetAllCampuses(){
        List<Campus> mockCampusList = mock(List.class);
        List<CampusDto> expectedList = mock(List.class);
        when(mockCampusClient.getAllCampus()).thenReturn(mockCampusList);
        assertEquals(expectedList, sut.getAllCampuses());
    }

    @org.junit.Test
    public void testGetCampusDtoById(){
        CampusDto mockCampusDto = mock(CampusDto.class);
        Campus campus = new Campus();
        List<Building> buildingList = new ArrayList<>();
        when(mockCampusClient.getCampusById("1")).thenReturn(campus);
        when(sut.getCampusObjects(campus)).thenReturn(mockCampusDto);
        sut.getCampusDtoById("1");
    }

    @org.junit.Test
    public void testGetBuildingDtoById(){
        Building building = new Building();
        when(mockCampusClient.getBuildingById("1")).thenReturn(building);
        sut.getBuildingDtoById("1");
    }

    @org.junit.Test
    public void testGetAllEmployee(){
        List<Employee> employeeList = new ArrayList<>();
        when(mockEmployeeClient.getAllEmployee()).thenReturn(employeeList);
        sut.getAllEmployees();
    }

//    @Test
//    public void testGetRoomDtoById(){
//        RoomDto roomDto = mock(RoomDto.class);
//        Campus campus = new Campus();
//        when(mockCampusClient.getRoomById("1")).thenReturn(roomDto);
//        sut.getRoomDtoById("1");
//    }
//
//
//    public void testGetListOfBuildingData(){
//        List<BuildingDto> buildingDtoList = new ArrayList<>();
//
//    }

}

