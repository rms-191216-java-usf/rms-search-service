package com.revature.rms.search;

import com.revature.rms.search.clients.EmployeeClient;
import com.revature.rms.search.dtos.*;
import com.revature.rms.search.entites.campus.*;
import com.revature.rms.search.entites.employee.Department;
import com.revature.rms.search.entites.employee.Employee;
import com.revature.rms.search.entites.employee.ResourceMetadata;
import com.revature.rms.search.services.BuildingService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SearchServiceApplicationTests {

	@InjectMocks
	BuildingService sut;
	private EmployeeClient employeeClient;
	private static List<BuildingDTO> buildingDTOS = new ArrayList<>();

	@Before
	public void setUp(){
//		ResourceMetadataDto resourceMetadata1 = new ResourceMetadataDto();
//		AddressDto addressDto = mock(AddressDto.class);
//		AddressDto address1 = new AddressDto("3220 Banyan Circle", "Tampa", "FL", "33613", "USA");
//		EmployeeDto employee1 = new EmployeeDto(1, "Andrew", "Spiteri", "spiteria@gmail.com", "Associate", "Training", resourceMetadata1);
//		ResourceMetadataDto resourceMetadata2 = new ResourceMetadataDto(employee1, new DateTime().toString(), employee1, new DateTime().toString(), employee1);
//		EmployeeDto employee2 = new EmployeeDto(2, "Mitchell", "Goshorn", "mitch@revature.com", "Training Lead", "Trainer", resourceMetadata2);
//		List<AmenityDto> amenitiesNEC = new ArrayList<>();
//		AmenityDto coffee = new AmenityDto(AmenityType.COFFEE, AmenityStatus.LOW);
//		AmenityDto creamer = new AmenityDto(AmenityType.COFFEE_CREAMER, AmenityStatus.OK);
//		amenitiesNEC.add(coffee);
//		amenitiesNEC.add(creamer);
//		List<RoomDto> roomsNEC = new ArrayList<>();
//		RoomStatus roomStatus = new RoomStatus(1, true, true, true, new DateTime().toString(), 2, "Why do you want more from me!");
//		List<Integer> workOrders = new ArrayList<>();
//		workOrders.add(1); workOrders.add(2);
//		RoomDto room1 = new RoomDto(1, "107", 24, true, roomStatus, 1, workOrders, resourceMetadata2);
//		roomsNEC.add(room1);
//		BuildingDTO bDTO = new BuildingDTO(1, "Northwest Education Complex", "NEC", address1, employee2, amenitiesNEC, roomsNEC, resourceMetadata2);
//		AddressDto address2 = new AddressDto("12212 Genshaft Dr", "Tampa", "FL", "33620", "USA");
//		EmployeeDto employee3 = new EmployeeDto(3, "Wezley", "Singleton", "wezley@revature.com", "Trainer", "Training", resourceMetadata2);
//		BuildingDTO bDTO1 = new BuildingDTO(2, "Business Administration Building", "BSN", address2, employee3, amenitiesNEC, roomsNEC, resourceMetadata2);
//		buildingDTOS.add(bDTO);
//		buildingDTOS.add(bDTO1);

		employeeClient = mock(EmployeeClient.class);
	}

	@Test
	public void getEmployeeByIdTest(){
		EmployeeDto employeeDto1 = mock(EmployeeDto.class);
		Employee employee1 = mock(Employee.class);
		when(employeeClient.getEmployeeById(anyInt())).thenReturn(employee1);
		when(employee1.extractEmployeeDto()).thenReturn(employeeDto1);
		assertEquals(sut.getEmployeeById(anyInt()), employeeDto1);
	}

	@Test
	void contextLoads() {
	}
}
