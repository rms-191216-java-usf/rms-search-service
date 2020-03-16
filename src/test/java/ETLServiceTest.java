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
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ETLServiceTest {

    ETLService sut = mock(ETLService.class);
    CampusClient mockCampusClient = mock(CampusClient.class);
    EmployeeClient mockEmployeeClient = mock(EmployeeClient.class);


    @Test
    public void testGetAllCampuses(){
        List<Campus> mockCampusList = new ArrayList<>();
        when(mockCampusClient.getAllCampus()).thenReturn(mockCampusList);
        sut.getAllCampuses();
    }

    @Test
    public void testGetCampusDtoById(){
        CampusDto mockCampusDto = mock(CampusDto.class);
        Campus campus = new Campus();
        List<Building> buildingList = new ArrayList<>();
        when(mockCampusClient.getCampusById("1")).thenReturn(campus);
        when(sut.getCampusObjects(campus)).thenReturn(mockCampusDto);
        sut.getCampusDtoById("1");
    }

    @Test
    public void testGetBuildingDtoById(){
        Building building = new Building();
        when(mockCampusClient.getBuildingById("1")).thenReturn(building);
        sut.getBuildingDtoById("1");
    }

    @Test
    public void testGetAllEmployee(){
        List<Employee> employeeList = new ArrayList<>();
        when(mockEmployeeClient.getAllEmployee()).thenReturn(employeeList);
        sut.getAllEmployees();
    }

    @Test
    public void testGetRoomDtoById(){
        RoomDto roomDto = mock(RoomDto.class);
        Campus campus = new Campus();
        when(mockCampusClient.getRoomById("1")).thenReturn(roomDto);
        sut.getRoomDtoById("1");
    }

    


}

