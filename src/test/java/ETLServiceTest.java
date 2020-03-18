
import com.revature.rms.search.clients.CampusClient;
import com.revature.rms.search.clients.EmployeeClient;

import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.campus.*;
import com.revature.rms.search.entites.employee.Department;
import com.revature.rms.search.entites.employee.Employee;
import com.revature.rms.search.entites.workorder.WorkOrder;
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

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ETLServiceTest {

//  @InjectMocks ETLService sut;
//  @Mock CampusClient mockCampusClient;
//  @Mock EmployeeClient mockEmployeeClient;
//
//  @Before
//  public void setup() {
//    sut = new ETLService(mockEmployeeClient, mockCampusClient);
//  }
//
//  @After
//  public void tearDown() {
//    sut = null;
//  }
//
//  @Test
//  public void testGetCampusDtoByIdWithValidInput() {
//    List<Integer> employees = Arrays.asList(1);
//    Address address = new Address("123 Bruce B Downs Blvd", "Tampa", "FL", "33612", "US");
//    ResourceMetadata resourceMetadata = new ResourceMetadata(1, "1/1/20", 1, "1/1/20", 1);
//    List<Amenity> amenities = Arrays.asList(new Amenity(AmenityType.COFFEE, AmenityStatus.LOW));
//    List<String> workOrders = Arrays.asList("1");
//    List<RoomStatus> roomStatuses =
//        Arrays.asList(new RoomStatus("1", true, true, true, "1/1/19", 1, "Good"));
//    List<Room> rooms =
//        Arrays.asList(
//            new Room("1", "123", 25, true, roomStatuses, 1, workOrders, resourceMetadata));
//    List<Building> buildings =
//        Arrays.asList(
//            new Building("1", "Muma", "BSN", address, 1, amenities, rooms, resourceMetadata));
//    Campus campus =
//        new Campus("1", "USF", "USF", address, 1, 1, 1, buildings, employees, resourceMetadata);
//    EmployeeDto empDto =
//        new EmployeeDto(
//            1,
//            "Blake",
//            "Dunn",
//            "Email@email.com",
//            "Baller",
//            Department.TRAINING,
//            new ResourceMetadataDto());
//    ResourceMetadataDto rMDto = new ResourceMetadataDto(empDto, "1/1/20", empDto, "1/1/20", empDto);
//    List<RoomStatusDto> roomStatusDto =
//        Arrays.asList(new RoomStatusDto("1", true, true, true, "1/1/19", empDto, "Good"));
//    List<RoomDto> roomDto = Arrays.asList(new RoomDto("1", "123", 25, true, roomStatusDto, rMDto));
//    List<BuildingDto> buildingDto =
//        Arrays.asList(
//            new BuildingDto("1", "Muma", "BSN", address, empDto, amenities, roomDto, rMDto));
//    CampusDto mockCampusDto =
//        new CampusDto(
//            "1",
//            "USF",
//            "USF",
//            address,
//            empDto,
//            empDto,
//            empDto,
//            buildingDto,
//            Arrays.asList(empDto),
//            rMDto);
//    when(mockCampusClient.getCampusById("1")).thenReturn(campus);
//    CampusDto expectedResult =
//        (CampusDto) when(sut.getCampusObjects(campus)).thenReturn(mockCampusDto);
//    assertEquals(expectedResult, sut.getCampusDtoById("1"));
//  }

  //  @Test
  //  public void testGetBuildingDtoByIdWithValidInput() {
  //    Address address = mock(Address.class);
  //    List<Amenity> amenities = mock(List.class);
  //    List<Room> rooms = mock(List.class);
  //    Building building =
  //        new Building("1", "Muma", "BSN", address, 1, amenities, rooms, new ResourceMetadata());
  //    when(mockCampusClient.getBuildingById("1")).thenReturn(building);
  //    sut.getBuildingDtoById("1");
  //  }
  //
  //  @Test
  //  public void testGetAllEmployeeWithValidInput() {
  //    List<Employee> employeeList = new ArrayList<>();
  //    when(mockEmployeeClient.getAllEmployee()).thenReturn(employeeList);
  //    sut.getAllEmployees();
  //  }

  //  @Test
  //  public void testGetAllCampusesWithValidInput() {
  //      List<Building> buildings = mock(List.class);
  //      List<Integer> employees = mock(List.class);
  //    Campus testCampus =
  //        new Campus(
  //            "32",
  //            "University of South Florida",
  //            "USF",
  //            new Address(),
  //            2,
  //            3,
  //            4,
  //            buildings,
  //            employees,
  //            new ResourceMetadata());
  //    List<BuildingDto> buildingDtos = mock(List.class);
  //    List<EmployeeDto> employeeDtos = mock(List.class);
  //    ResourceMetadataDto rMDDto = mock(ResourceMetadataDto.class);
  //    CampusDto dto =
  //        new CampusDto(
  //            "32",
  //            "University of South Florida",
  //            "USF",
  //            new Address(),
  //            new EmployeeDto(),
  //            new EmployeeDto(),
  //            new EmployeeDto(),
  //            buildingDtos,
  //            employeeDtos,
  //            new ResourceMetadataDto());
  //    List<Campus> mockCampusList = Arrays.asList(testCampus);
  //    List<CampusDto> expectedList = Arrays.asList(dto);
  //    when(mockCampusClient.getAllCampus()).thenReturn(mockCampusList);
  //    when(sut.getAllCampuses()).thenReturn(expectedList);
  //    assertEquals(expectedList, sut.getAllCampuses());
  //  }

  //      @Test
  //      public void testGetRoomDtoById(){
  //          RoomDto roomDto = mock(RoomDto.class);
  //          Campus campus = new Campus();
  //          when(mockCampusClient.getRoomById("1")).thenReturn(roomDto);
  //          sut.getRoomDtoById("1");
  //      }

//  @org.junit.Test
//  public void testGetAllCampuses() {
//    List<Campus> mockCampusList = mock(List.class);
//    List<CampusDto> expectedList = mock(List.class);
//    when(mockCampusClient.getAllCampus()).thenReturn(mockCampusList);
//    assertEquals(expectedList, sut.getAllCampuses());
//  }
//
//  @org.junit.Test
//  public void testGetCampusDtoById() {
//    CampusDto mockCampusDto = mock(CampusDto.class);
//    Campus campus = new Campus();
//    List<Building> buildingList = new ArrayList<>();
//    when(mockCampusClient.getCampusById("1")).thenReturn(campus);
//    when(sut.getCampusObjects(campus)).thenReturn(mockCampusDto);
//    sut.getCampusDtoById("1");
//  }
//
//  @org.junit.Test
//  public void testGetBuildingDtoById() {
//    Building building = new Building();
//    when(mockCampusClient.getBuildingById("1")).thenReturn(building);
//    sut.getBuildingDtoById("1");
//  }
//
//  @org.junit.Test
//  public void testGetAllEmployee() {
//    List<Employee> employeeList = new ArrayList<>();
//    when(mockEmployeeClient.getAllEmployee()).thenReturn(employeeList);
//    sut.getAllEmployees();
//  }

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
